package si.um.feri.mag.polanec.experiment;

import si.um.feri.mag.polanec.data.FileUtils;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Utils;

import java.util.*;

class ThreadManager implements IRunnableFinishedListener {
    private List<Experiment.ClassifierRunnable> runnables;
    private HashMap<Experiment.ClassifierRunnable, Thread> remainingThreads;
    private HashMap<Experiment.ClassifierRunnable, Thread> runningThreads;

    private HashMap<Classifier, Integer> classifierTypeCounters;
    private int maxThreads;
    private int totalThreads;
    private int doneThreads;

    ThreadManager(List<Experiment.ClassifierRunnable> runnables, int maxThreads) {
        if(maxThreads == -1) {
            this.maxThreads = Runtime.getRuntime().availableProcessors() / 2;
        }else {
            this.maxThreads = maxThreads;
        }
//        this.maxThreads = Runtime.getRuntime().availableProcessors() * 2;
        this.runnables = runnables;
        this.runningThreads = new HashMap<>();
        this.totalThreads = runnables.size();
        this.classifierTypeCounters = new HashMap<>();
        for (Classifier c : Classifiers.getClassifiers()) {
            this.classifierTypeCounters.put(c, 0);
        }
    }

    void runThreads() {
        // create actual threads
        this.prepareThreads();

        FileUtils.writeToLog("Total number of threads: " + this.totalThreads);

        // start first maxThreads
        Iterator<Map.Entry<Experiment.ClassifierRunnable, Thread>> iterator = this.remainingThreads.entrySet().iterator();
        List<Map.Entry<Experiment.ClassifierRunnable, Thread>> entries = new ArrayList<>(maxThreads);
        Map.Entry<Experiment.ClassifierRunnable, Thread> entry = null;
        for (int i = 0; i < maxThreads && iterator.hasNext(); i++, entry = iterator.next()) {
            if (entry != null) {
                entries.add(entry);
            }
        }
        // invoke thread start
        for (Map.Entry<Experiment.ClassifierRunnable, Thread> e : entries) {
            startThread(e.getKey(), e.getValue());
        }
    }

    private synchronized void startThread(Experiment.ClassifierRunnable cr, Thread thread) {
        this.runningThreads.put(cr, thread);
        this.remainingThreads.remove(cr, thread);
        // increment counter
        this.classifierTypeCounters.put(cr.classifier, this.classifierTypeCounters.get(cr.classifier) + 1);
        thread.start();
    }

    /**
     * Once the thread has done it's job remove the thread from ative thread list.
     *
     * @param runnable which runnable was executed in thread
     */
    private synchronized void removeThread(Experiment.ClassifierRunnable runnable) {
        ++doneThreads;
        this.classifierTypeCounters.put(runnable.classifier, this.classifierTypeCounters.get(runnable.classifier) - 1);
        this.runningThreads.remove(runnable);
        // pick next thread if possible
        selectNextThread();
    }

    private synchronized void selectNextThread() {
        if (this.runningThreads.size() >= maxThreads) {
            // do not start (don't even search) for new, suitable, thread if max-number of threads is running
            return;
        }
        int activeMLPCount = countMLP();

        // pick first suitable runnable
        for (Map.Entry<Experiment.ClassifierRunnable, Thread> e : remainingThreads.entrySet()) {
            if (e.getKey().classifier.getClass().equals(MultilayerPerceptron.class)) {
                if ((activeMLPCount < 3 && areOtherTypesAvailable()) || !areOtherTypesAvailable()) {
                    startThread(e.getKey(), e.getValue());
                    break;
                }
            } else {
                startThread(e.getKey(), e.getValue());
                break;
            }
        }
    }

    private synchronized boolean areOtherTypesAvailable() {
        return this.remainingThreads.entrySet().stream().anyMatch(entry -> !entry.getKey().classifier.getClass().equals(MultilayerPerceptron.class));
    }

    private int countMLP() {
        return countForAlgorithm(MultilayerPerceptron.class);
    }

    private int countForAlgorithm(Class<?> destClass) {
        // find in active thread
        int count = 0;
        for (Map.Entry<Classifier, Integer> e : classifierTypeCounters.entrySet()) {
            if (e.getKey().getClass().equals(destClass)) {
                return e.getValue();
            }
        }
        return count;
    }


    private void prepareThreads() {
        remainingThreads = new HashMap<>();
        int i = 1;
        for (Experiment.ClassifierRunnable r : runnables) {
            r.setOnRunnableFinishedListener(this);
            Thread t = new Thread(r);
            t.setName(r.classifier.getClass().getName() + " " + Utils.joinOptions(((AbstractClassifier) r.classifier).getOptions()) + r.runNumber);
            remainingThreads.put(r, t);
            r.setDescription("#" + i + " - " + t.getName());
            ++i;
        }
    }

    synchronized boolean allThreadsDone() {
        return this.remainingThreads.size() == 0 && this.runningThreads.size() == 0;
    }

    @Override
    public synchronized void onRunnableFinished(Experiment.ClassifierRunnable runnable, boolean success) {
        removeThread(runnable);
        FileUtils.writeToLog(String.format("Thread %s has %s finished it's execution.", runnable.getDescription(), success ? "successfully" : "unsuccessfully"));
        FileUtils.writeToLog(String.format("Progress: %d", (int) ((((double) doneThreads) / ((double) totalThreads)) * 100.0))+"%");
    }
}

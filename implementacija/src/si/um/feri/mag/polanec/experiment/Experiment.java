package si.um.feri.mag.polanec.experiment;

import si.um.feri.mag.polanec.data.FileUtils;
import si.um.feri.mag.polanec.data.IDataFile;
import si.um.feri.mag.polanec.enums.MetricsType;
import si.um.feri.mag.polanec.enums.VersionType;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Base experiment class.
 */
public abstract class Experiment {

    protected int numSamples;
    protected int baseSeed;
    protected int nfolds;
    protected int nruns;
    protected int maxThreads;
    protected PrintWriter writerObj;

    protected Experiment(String[] args) {
        baseSeed = 4;
        nfolds = 10;
        nruns = 30;
        numSamples = 1000;
        maxThreads = -1;
        parseOptions(args);
    }

    /**
     * Set options from command line arguments.
     * Syntax: -argName0 argValue0 -argName1 argValue1 ...
     * <p>
     * Valid options: TODO
     *
     * @param args arguments for application
     */
    private void parseOptions(String[] args) {
        if (args == null || args.length < 2) {
            return;
        }
        HashMap<String, String> argMap = createArgMap(args);

        if(argMap.size() == 0){
            return;
        }

        Class<?> c = getClass();
        while(c != null){
            Field[] fields = c.getDeclaredFields();

            for(Field f : fields){
                if(argMap.containsKey(f.getName())){
                    try {
                        f.setInt(this, Integer.parseInt(argMap.get(f.getName())));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            c = c.getSuperclass();
        }
    }

    protected HashMap<String, String> createArgMap(String[] args) {
        HashMap<String, String> argMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if(!args[i].startsWith("-")){
                continue;
            }
            String k = args[i].replace("-", "");
            String v = null;
            if(i+1 == args.length){
                break;
            }
            v = args[++i];
            argMap.put(k, v);
        }
        return argMap;
    }

    /**
     * Run the experiment.
     */
    public void run() throws Exception {

        FileUtils.writeToLog();
        FileUtils.writeToLog("EXPERIMENT: " + this.getExperimentName() + " - START");
        writerObj = FileUtils.getCSVFileWriterObject(this.getLogFileName());
        FileUtils.writeToFile(writerObj, this.getFileHeader());
        FileUtils.createLogFileInstance();
        long start = System.nanoTime();

        List<ClassifierRunnable> runnableList = new ArrayList<>();

        run_impl(runnableList);

        ThreadManager threadManager = new ThreadManager(runnableList, this.maxThreads);
        threadManager.runThreads();

        // wait for experiment to end
        while (!threadManager.allThreadsDone()) ;
        // write experiment time to log
        // convert nanoseconds to seconds
        double time = ((double) (System.nanoTime() - start)) / 1000000000.0;
        FileUtils.writeToLog("EXPERIMENT: " + this.getExperimentName() + " - END. Experiment took: " + time + "s.");

        FileUtils.closeConnection(writerObj);
    }

    /**
     * @param runnableList list of threads to fill
     * @throws Exception /
     */
    protected void run_impl(List<ClassifierRunnable> runnableList) throws Exception {
        Instances data = FileUtils.takeRandomSamples(ConverterUtils.DataSource.read(getExperimentDataFile().getPath()), numSamples, new Random(baseSeed));
        data.setClassIndex(data.numAttributes() - 1);

        int currentSeed = baseSeed;
        //   perform cross-validation
        for (int i = 0; i < nruns; i++) {
            // randomize data
            currentSeed = calculateNextSeed(i, baseSeed, currentSeed);
            Random rand = new Random(currentSeed);
            Instances randData = new Instances(data);
            randData.randomize(rand);
            randData.stratify(nfolds);

            // create threads
            for (Classifier cls : Classifiers.getClassifiers()) {
                runnableList.add(new ClassifierRunnable(cls, randData, i, currentSeed));
            }
        }
    }

    /**
     * @return name of .csv log file.
     */
    protected abstract String getLogFileName();

    protected abstract String[] getFileHeader();

    protected abstract IDataFile getExperimentDataFile();

    public abstract String getExperimentName();

    protected abstract MetricsType getMetricsType();

    protected abstract VersionType getVersionType();

    /**
     * Calculate seed for next round.
     *
     * @param runIndex    round index
     * @param baseSeed    seed at start of the program
     * @param currentSeed seed currently in use
     * @return seed for next round
     */
    protected int calculateNextSeed(int runIndex, int baseSeed, int currentSeed) {
        return currentSeed + 1;
    }

    public int getBaseSeed() {
        return baseSeed;
    }

    public void setBaseSeed(int baseSeed) {
        this.baseSeed = baseSeed;
    }

    public int getNfolds() {
        return nfolds;
    }

    public void setNfolds(int nfolds) {
        this.nfolds = nfolds;
    }

    public int getNruns() {
        return nruns;
    }

    public void setNumSamples(int numSamples) {
        this.numSamples = numSamples;
    }

    public int getNumSamples() {
        return numSamples;
    }

    public void setNruns(int nruns) {
        this.nruns = nruns;
    }

    private Thread prepareClassifierForThreadExecution(Classifier classifier, Instances instances, int i, int seed) {
        Thread thread = new Thread();
        thread.setName(classifier.getClass().getName() + " " + Utils.joinOptions(((AbstractClassifier) classifier).getOptions()));
        return thread;
    }

    /**
     * Method for evaluation of single classifier.
     * Access level is protected so we can override this method in subclass.
     *
     * @param cls         instance of classifier
     * @param trainData   data to check
     * @param runNumber   current run number
     * @param currentSeed seed used for randomization
     * @throws Exception when deep copy of {@code cls} fails
     */
    private void classifierEvaluation(Classifier cls, Instances trainData, int runNumber, int currentSeed) throws Exception {
        long startTime = System.nanoTime();
        Evaluation eval = new Evaluation(trainData);
        for (int n = 0; n < nfolds; n++) {
            Instances train = trainData.trainCV(nfolds, n);
            Instances test = trainData.testCV(nfolds, n);

            // build and evaluate classifier
            Classifier clsCopy = AbstractClassifier.makeCopy(cls);
            clsCopy.buildClassifier(train);
            eval.evaluateModel(clsCopy, test);
        }
        // write results for this run to file
        long deltaT = System.nanoTime() - startTime;
        FileUtils.writeToFile(writerObj,
                cls.getClass().getName() + " " + Utils.joinOptions(((AbstractClassifier) cls).getOptions()),
                Integer.toString(runNumber),
                Integer.toString(currentSeed),
                Double.toString(eval.confusionMatrix()[1][1]).replace(".", ","),
                Double.toString(eval.confusionMatrix()[0][1]).replace(".", ","),
                Double.toString(eval.confusionMatrix()[0][0]).replace(".", ","),
                Double.toString(eval.confusionMatrix()[1][0]).replace(".", ","),
                Double.toString(eval.correct()).replace(".", ","),
                Double.toString(eval.incorrect()).replace(".", ","),
                Double.toString(eval.correct() / (eval.incorrect() + eval.correct())).replace(".", ","),
//                        eval.areaUnderROC(0) + "",
//                        eval.areaUnderROC(1) + "",
                Double.toString(((double) deltaT) / 1000000000.0).replace(".", ",")
        );
    }

    protected void classifierEvaluation(ClassifierRunnable runnable) throws Exception {
        classifierEvaluation(runnable.classifier, runnable.data, runnable.runNumber, runnable.randomSeed);
    }

    class ClassifierRunnable implements Runnable {

        /**
         * Classifier to invoke.
         */
        Classifier classifier;

        /**
         * Data on which we will classify.
         */
        Instances data;
        int randomSeed;
        int runNumber;
        private IRunnableFinishedListener listener;
        private String description;

        ClassifierRunnable(Classifier classifier, Instances data, int runIndex, int randomSeed) {
            this.classifier = classifier;
            this.data = new Instances(data);
            this.runNumber = runIndex + 1;
            this.randomSeed = randomSeed;
        }

        @Override
        public void run() {
            try {
                classifierEvaluation(this);
                fireUpdate(true);
            } catch (Exception e) {
                FileUtils.writeToLog(String.format("Error in classifier evaluation. Classifier: '%s', Run number: %d, Random Seed: %d",
                        classifier.getClass().getName() + " " + Utils.joinOptions(((AbstractClassifier) classifier).getOptions()),
                        runNumber,
                        randomSeed
                ));
                e.printStackTrace();
                fireUpdate(false);
            }
        }

        /**
         * Notify listener if runnable was successfully executed.
         *
         * @param success was runnable successfully executed.
         */
        private void fireUpdate(boolean success) {
            if (this.listener != null) {
                this.listener.onRunnableFinished(this, success);
            }
        }

        public void setOnRunnableFinishedListener(IRunnableFinishedListener listener) {
            this.listener = listener;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }
}

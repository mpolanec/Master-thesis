package si.um.feri.mag.polanec.experiment;

public interface IRunnableFinishedListener {
    void onRunnableFinished(Experiment.ClassifierRunnable runnable, boolean success);
}

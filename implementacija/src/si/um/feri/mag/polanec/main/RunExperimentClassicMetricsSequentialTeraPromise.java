package si.um.feri.mag.polanec.main;

import si.um.feri.mag.polanec.data.FileUtils;
import si.um.feri.mag.polanec.experiment.ExperimentClassicMetricsAllTeraPromise;
import si.um.feri.mag.polanec.experiment.ExperimentClassicMetricsSequentialTeraPromise;

public class RunExperimentClassicMetricsSequentialTeraPromise {
    /**
     * Application entry point.
     *
     * @param args arguments for this application
     */
    public static void main(String[] args) {

        ExperimentClassicMetricsSequentialTeraPromise exp = new ExperimentClassicMetricsSequentialTeraPromise(args);
        try {
//            exp.setNruns(1);
            exp.run();
        } catch (Exception e) {
            e.printStackTrace();
            FileUtils.writeToLog("Error in experiment: '" + exp.getExperimentName() + "'");
            FileUtils.writeToLog(e.getMessage());
        } finally {
            FileUtils.closeLog();
        }
    }
}

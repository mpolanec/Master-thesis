package si.um.feri.mag.polanec.main;

import si.um.feri.mag.polanec.data.FileUtils;
import si.um.feri.mag.polanec.experiment.ExperimentClassicMetricsAllBugInf;
import si.um.feri.mag.polanec.experiment.ExperimentClassicMetricsAllTeraPromise;

public class RunExperimentClassicMetricsAllBugInf {
    /**
     * Application entry point.
     *
     * @param args arguments for this application
     */
    public static void main(String[] args) {

        ExperimentClassicMetricsAllBugInf experimentClassicMetricsAll = new ExperimentClassicMetricsAllBugInf(args);
        try {
//            experimentClassicMetricsAll.setNruns(1);
            experimentClassicMetricsAll.run();
        } catch (Exception e) {
            e.printStackTrace();
            FileUtils.writeToLog("Error in experiment: '" + experimentClassicMetricsAll.getExperimentName() + "'");
            FileUtils.writeToLog(e.getMessage());
        } finally {
            FileUtils.closeLog();
        }
    }
}

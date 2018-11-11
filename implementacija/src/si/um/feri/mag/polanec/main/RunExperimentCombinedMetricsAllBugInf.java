package si.um.feri.mag.polanec.main;

import si.um.feri.mag.polanec.data.FileUtils;
import si.um.feri.mag.polanec.experiment.ExperimentChangeMetricsAllBugInf;
import si.um.feri.mag.polanec.experiment.ExperimentCombinedMetricsAllBugInf;

public class RunExperimentCombinedMetricsAllBugInf {
    /**
     * Application entry point.
     *
     * @param args arguments for this application
     */
    public static void main(String[] args) {

        ExperimentCombinedMetricsAllBugInf experimentCombinedMetricsAll = new ExperimentCombinedMetricsAllBugInf(args);
        try {
//            experimentCombinedMetricsAll.setNruns(1);
            experimentCombinedMetricsAll.run();
        } catch (Exception e) {
            e.printStackTrace();
            FileUtils.writeToLog("Error in experiment: '" + experimentCombinedMetricsAll.getExperimentName() + "'");
            FileUtils.writeToLog(e.getMessage());
        } finally {
            FileUtils.closeLog();
        }
    }
}

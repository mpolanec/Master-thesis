package si.um.feri.mag.polanec.main;

import si.um.feri.mag.polanec.data.FileUtils;
import si.um.feri.mag.polanec.experiment.ExperimentChangeMetricsAllBugInf;

public class RunExperimentChangeMetricsAllBugInf {
    /**
     * Application entry point.
     *
     * @param args arguments for this application
     */
    public static void main(String[] args) {

        ExperimentChangeMetricsAllBugInf experimentChangeMetricsAll = new ExperimentChangeMetricsAllBugInf(args);
        try {
//            experimentChangeMetricsAll.setNruns(3);
            experimentChangeMetricsAll.run();
        } catch (Exception e) {
            e.printStackTrace();
            FileUtils.writeToLog("Error in experiment: '" + experimentChangeMetricsAll.getExperimentName() + "'");
            FileUtils.writeToLog(e.getMessage());
        } finally {
            FileUtils.closeLog();
        }
    }
}

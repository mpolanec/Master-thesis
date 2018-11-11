package si.um.feri.mag.polanec.experiment;

import si.um.feri.mag.polanec.data.DataFiles;
import si.um.feri.mag.polanec.data.IDataFile;
import si.um.feri.mag.polanec.enums.MetricsType;
import si.um.feri.mag.polanec.enums.VersionType;

public class ExperimentCombinedMetricsAllBugInf extends Experiment {

    public ExperimentCombinedMetricsAllBugInf(String[] args) {
        super(args);
    }

    @Override
    protected String getLogFileName() {
        return "combined_metrics_all_bug_inf.csv";
    }

    @Override
    protected String[] getFileHeader() {
        return new String[]{
                "Classifier", "Measurement num.", "Seed", "Num. TP", "Num. FP", "Num. TN", "Num. FN", "Correctly classified", "Incorrectly classified", "Precision", /*"Roc[0]", "Roc[1]",*/ "Execution time (s)"
        };
    }

    @Override
    protected IDataFile getExperimentDataFile() {
        return DataFiles.getFile("all_combined.arff");
    }

    @Override
    public String getExperimentName() {
        return "Combined metrics all - BugInf";
    }

    @Override
    protected MetricsType getMetricsType() {
        return MetricsType.COMBINED_METRICS;
    }

    @Override
    protected VersionType getVersionType() {
        return VersionType.ALL_VERSIONS;
    }
}

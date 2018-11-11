package si.um.feri.mag.polanec.experiment;

import si.um.feri.mag.polanec.data.DataFiles;
import si.um.feri.mag.polanec.data.IDataFile;
import si.um.feri.mag.polanec.enums.MetricsType;
import si.um.feri.mag.polanec.enums.VersionType;

public class ExperimentClassicMetricsAllTeraPromise extends Experiment {

    public ExperimentClassicMetricsAllTeraPromise(String[] args) {
        super(args);
    }

    @Override
    protected String getLogFileName() {
        return "classic_metrics_all_tera_promise.csv";
    }

    @Override
    protected String[] getFileHeader() {
        return new String[]{
                "Classifier", "Measurement num.", "Seed", "Num. TP", "Num. FP", "Num. TN", "Num. FN", "Correctly classified", "Incorrectly classified", "Precision", /*"Roc[0]", "Roc[1]",*/ "Execution time (s)"
        };
    }

    @Override
    protected IDataFile getExperimentDataFile() {
        return DataFiles.getFile("all.arff");
    }

    @Override
    public String getExperimentName() {
        return "Classic metrics all - teraPROMISE";
    }

    @Override
    protected MetricsType getMetricsType() {
        return MetricsType.CLASSIC_METRICS;
    }

    @Override
    protected VersionType getVersionType() {
        return VersionType.ALL_VERSIONS;
    }
}

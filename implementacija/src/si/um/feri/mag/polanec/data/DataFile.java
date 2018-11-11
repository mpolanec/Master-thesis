package si.um.feri.mag.polanec.data;

import si.um.feri.mag.polanec.enums.DataSource;
import si.um.feri.mag.polanec.enums.MetricsType;

/**
 * Class that describe the data file.
 */
class DataFile implements IDataFile {

    private String path;
    private String projectName;
    private double version;
    private DataSource dataSource;
    private MetricsType metricsType;
    private String name;

    DataFile(String name, String projectName, MetricsType metricsType) {
        this(name, projectName, metricsType, 1.0);
    }

    DataFile(String name, String projectName, MetricsType metricsType, double version) {
        this(name, projectName, metricsType, version, DataSource.UNKNOWNN);
    }

    DataFile(String name, String projectName, MetricsType metricsType, double version, DataSource dataSource) {
        if (this.dataSource != DataSource.UNKNOWNN) {
            this.path = dataSource.getDirName() + projectName + "/" + name;
        } else {
            this.path = name;
        }
        this.name = name;
        this.projectName = projectName;
        this.metricsType = metricsType;
        this.version = version;
        this.dataSource = dataSource;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getProjectName() {
        return this.projectName;
    }

    @Override
    public double getVersion() {
        return this.version;
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public MetricsType getMetricsType() {
        return this.metricsType;
    }

    @Override
    public String getName() {
        return this.name;
    }
}

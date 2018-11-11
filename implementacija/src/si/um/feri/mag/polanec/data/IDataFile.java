package si.um.feri.mag.polanec.data;

import si.um.feri.mag.polanec.enums.DataSource;
import si.um.feri.mag.polanec.enums.MetricsType;

/**
 * Interface describing data file.
 */
public interface IDataFile {

    /**
     * @return path to file
     */
    String getPath();

    /**
     * @return name of the project
     */
    String getProjectName();

    /**
     * @return version of the project
     */
    double getVersion();

    /**
     * @return data source of the file
     */
    DataSource getDataSource();

    /**
     * @return type of metrics available in this file
     */
    MetricsType getMetricsType();

    /**
     * @return name of the file
     */
    String getName();
}

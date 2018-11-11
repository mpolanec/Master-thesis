package si.um.feri.mag.polanec.enums;

/**
 * Interface for directory name of the datasource.
 */
public interface DataSourceDirName {
    /**
     * Base path for data.
     */
    String BASE_PATH = "./../data_raw/";

    String getDirName();
}
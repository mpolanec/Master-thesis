package si.um.feri.mag.polanec.enums;

/**
 * From which data source is the file.
 */
public enum DataSource implements DataSourceDirName {
    /**
     * From <a href='http://openscience.us/repo/defect/ck/'>tera-PROMISE</a>)
     */
    PROMISE(){
        @Override
        public String getDirName() {
            return BASE_PATH + "promise/";
        }
    },

    /**
     * From <a href='http://bug.inf.usi.ch/download.php'>Bug prediction dataset</a>
     */
    BUG_INF(){
        @Override
        public String getDirName() {
            return BASE_PATH + "bug-inf-usi/";
        }
    },

    /**
     * From a custom source, our own data,...
     */
    UNKNOWNN(){
        @Override
        public String getDirName() {
            return null;
        }
    };
}



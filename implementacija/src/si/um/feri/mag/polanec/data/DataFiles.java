package si.um.feri.mag.polanec.data;

import si.um.feri.mag.polanec.enums.DataSource;
import si.um.feri.mag.polanec.enums.MetricsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Paths to known data files. Data-sets contains data from open-source projects.
 */
public class DataFiles {

    private static final ArrayList<IDataFile> files = new ArrayList<IDataFile>() {{
        // data_raw/run_combiner.ps1 <- convert data from csv to arff
        // use:
        // data_raw/datafiles_helper.ps1 <- links to files (without correct versions).
        // teraPROMISE repo
        // combined data
        add(new DataFile("all.arff", "All", MetricsType.CLASSIC_METRICS, 1.0, DataSource.PROMISE));

        // single data
        add(new DataFile("ant-1.3_prepared.arff", "ant", MetricsType.CLASSIC_METRICS, 1.3, DataSource.PROMISE));
        add(new DataFile("ant-1.4_prepared.arff", "ant", MetricsType.CLASSIC_METRICS, 1.4, DataSource.PROMISE));
        add(new DataFile("ant-1.5_prepared.arff", "ant", MetricsType.CLASSIC_METRICS, 1.5, DataSource.PROMISE));
        add(new DataFile("ant-1.6_prepared.arff", "ant", MetricsType.CLASSIC_METRICS, 1.6, DataSource.PROMISE));
        add(new DataFile("ant-1.7_prepared.arff", "ant", MetricsType.CLASSIC_METRICS, 1.7, DataSource.PROMISE));
        add(new DataFile("camel-1.0_prepared.arff", "camel", MetricsType.CLASSIC_METRICS, 1.0, DataSource.PROMISE));
        add(new DataFile("camel-1.2_prepared.arff", "camel", MetricsType.CLASSIC_METRICS, 1.2, DataSource.PROMISE));
        add(new DataFile("camel-1.4_prepared.arff", "camel", MetricsType.CLASSIC_METRICS, 1.4, DataSource.PROMISE));
        add(new DataFile("camel-1.6_prepared.arff", "camel", MetricsType.CLASSIC_METRICS, 1.6, DataSource.PROMISE));
        add(new DataFile("forrest-0.6_prepared.arff", "forrest", MetricsType.CLASSIC_METRICS, 0.6, DataSource.PROMISE));
        add(new DataFile("forrest-0.7_prepared.arff", "forrest", MetricsType.CLASSIC_METRICS, 0.7, DataSource.PROMISE));
        add(new DataFile("forrest-0.8_prepared.arff", "forrest", MetricsType.CLASSIC_METRICS, 0.8, DataSource.PROMISE));
        add(new DataFile("ivy-1.1_prepared.arff", "ivy", MetricsType.CLASSIC_METRICS, 1.1, DataSource.PROMISE));
        add(new DataFile("ivy-1.4_prepared.arff", "ivy", MetricsType.CLASSIC_METRICS, 1.4, DataSource.PROMISE));
        add(new DataFile("ivy-2.0_prepared.arff", "ivy", MetricsType.CLASSIC_METRICS, 2.0, DataSource.PROMISE));
        add(new DataFile("jedit-3.2_prepared.arff", "jedit", MetricsType.CLASSIC_METRICS, 3.2, DataSource.PROMISE));
        add(new DataFile("jedit-4.0_prepared.arff", "jedit", MetricsType.CLASSIC_METRICS, 4.0, DataSource.PROMISE));
        add(new DataFile("jedit-4.1_prepared.arff", "jedit", MetricsType.CLASSIC_METRICS, 4.1, DataSource.PROMISE));
        add(new DataFile("jedit-4.2_prepared.arff", "jedit", MetricsType.CLASSIC_METRICS, 4.2, DataSource.PROMISE));
        add(new DataFile("jedit-4.3_prepared.arff", "jedit", MetricsType.CLASSIC_METRICS, 4.3, DataSource.PROMISE));
        add(new DataFile("log4j-1.0_prepared.arff", "log4j", MetricsType.CLASSIC_METRICS, 1.0, DataSource.PROMISE));
        add(new DataFile("log4j-1.1_prepared.arff", "log4j", MetricsType.CLASSIC_METRICS, 1.1, DataSource.PROMISE));
        add(new DataFile("log4j-1.2_prepared.arff", "log4j", MetricsType.CLASSIC_METRICS, 1.2, DataSource.PROMISE));
        add(new DataFile("lucene-2.0_prepared.arff", "lucene", MetricsType.CLASSIC_METRICS, 2.0, DataSource.PROMISE));
        add(new DataFile("lucene-2.2_prepared.arff", "lucene", MetricsType.CLASSIC_METRICS, 2.2, DataSource.PROMISE));
        add(new DataFile("lucene-2.4_prepared.arff", "lucene", MetricsType.CLASSIC_METRICS, 2.4, DataSource.PROMISE));
        add(new DataFile("poi-1.5_prepared.arff", "poi", MetricsType.CLASSIC_METRICS, 1.5, DataSource.PROMISE));
        add(new DataFile("poi-2.0_prepared.arff", "poi", MetricsType.CLASSIC_METRICS, 2.0, DataSource.PROMISE));
        add(new DataFile("poi-2.5_prepared.arff", "poi", MetricsType.CLASSIC_METRICS, 2.5, DataSource.PROMISE));
        add(new DataFile("poi-3.0_prepared.arff", "poi", MetricsType.CLASSIC_METRICS, 3.0, DataSource.PROMISE));
        add(new DataFile("synapse-1.0_prepared.arff", "synapse", MetricsType.CLASSIC_METRICS, 1.0, DataSource.PROMISE));
        add(new DataFile("synapse-1.1_prepared.arff", "synapse", MetricsType.CLASSIC_METRICS, 1.1, DataSource.PROMISE));
        add(new DataFile("synapse-1.2_prepared.arff", "synapse", MetricsType.CLASSIC_METRICS, 1.2, DataSource.PROMISE));
        add(new DataFile("tomcat_prepared.arff", "tomcat", MetricsType.CLASSIC_METRICS, 1.0, DataSource.PROMISE));
        add(new DataFile("velocity-1.4_prepared.arff", "velocity", MetricsType.CLASSIC_METRICS, 1.4, DataSource.PROMISE));
        add(new DataFile("velocity-1.5_prepared.arff", "velocity", MetricsType.CLASSIC_METRICS, 1.5, DataSource.PROMISE));
        add(new DataFile("velocity-1.6_prepared.arff", "velocity", MetricsType.CLASSIC_METRICS, 1.6, DataSource.PROMISE));
        add(new DataFile("xalan-2.5_prepared.arff", "xalan", MetricsType.CLASSIC_METRICS, 2.5, DataSource.PROMISE));
        add(new DataFile("xalan-2.6_prepared.arff", "xalan", MetricsType.CLASSIC_METRICS, 2.6, DataSource.PROMISE));
        add(new DataFile("xalan-2.4_prepared.arff", "xalan", MetricsType.CLASSIC_METRICS, 2.4, DataSource.PROMISE));
        add(new DataFile("xalan-2.7_prepared.arff", "xalan", MetricsType.CLASSIC_METRICS, 2.7, DataSource.PROMISE));
        add(new DataFile("xerces-1.2_prepared.arff", "xerces", MetricsType.CLASSIC_METRICS, 1.2, DataSource.PROMISE));
        add(new DataFile("xerces-1.3_prepared.arff", "xerces", MetricsType.CLASSIC_METRICS, 1.3, DataSource.PROMISE));
        add(new DataFile("xerces-1.4_prepared.arff", "xerces", MetricsType.CLASSIC_METRICS, 1.4, DataSource.PROMISE));
        add(new DataFile("xerces-init_prepared.arff", "xerces", MetricsType.CLASSIC_METRICS, 1.0, DataSource.PROMISE));

        // bug prediction dataset
        // all data combined
        add(new DataFile("allchange-metrics.arff", "All", MetricsType.CHANGE_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("all_combined.arff", "All", MetricsType.COMBINED_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("allsingle-version-ck-oo.arff", "All", MetricsType.CLASSIC_METRICS, 1.0, DataSource.BUG_INF));

        // single files
        add(new DataFile("change-metrics_prepared.arff", "eclipse-jdt", MetricsType.CHANGE_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("single-version-ck-oo_prepared.arff", "eclipse-jdt", MetricsType.CLASSIC_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("change-metrics_prepared.arff", "eclipse-pde", MetricsType.CHANGE_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("single-version-ck-oo_prepared.arff", "eclipse-pde", MetricsType.CLASSIC_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("change-metrics_prepared.arff", "equinox-framework", MetricsType.CHANGE_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("single-version-ck-oo_prepared.arff", "equinox-framework", MetricsType.CLASSIC_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("change-metrics_prepared.arff", "lucene", MetricsType.CHANGE_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("single-version-ck-oo_prepared.arff", "lucene", MetricsType.CLASSIC_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("change-metrics_prepared.arff", "mylyn", MetricsType.CHANGE_METRICS, 1.0, DataSource.BUG_INF));
        add(new DataFile("single-version-ck-oo_prepared.arff", "mylyn", MetricsType.CLASSIC_METRICS, 1.0, DataSource.BUG_INF));
    }};

    /**
     * @param name the file we want
     * @return the file object
     */
    public static IDataFile getFile(String name) {
        return findFileByCondition(file -> file.getName().equals(name));
    }

    public static HashMap<String, List<IDataFile>> getProjects() throws Exception {
        List<String> projectNames = files.stream()
                .filter(iDataFile -> iDataFile.getDataSource() == DataSource.PROMISE && !iDataFile.getProjectName().equals("All"))
                .map(IDataFile::getProjectName).collect(Collectors.toList());
        if (projectNames == null || projectNames.size() == 0) {
            throw new Exception("No projects found");
        }
        final HashMap<String, List<IDataFile>> projects = new HashMap<>();

        projectNames.forEach(s -> {
            List<IDataFile> projectFiles = files.stream().filter(f -> f.getDataSource() == DataSource.PROMISE && f.getProjectName().equals(s)).collect(Collectors.toList());
            if (projectFiles.size() > 1) {
                projects.put(s, projectFiles);
            }
        });

        return projects;
    }

    /**
     * Find a file by a certain criteria.
     *
     * @param condition criteria to be met
     * @return file object or {@code null}
     */
    private static IDataFile findFileByCondition(ICondition condition) {
        for (IDataFile f : files) {
            if (condition.callback(f)) {
                return f;
            }
        }
        return null;
    }

    public interface ICondition {
        boolean callback(IDataFile file);
    }

    public static ArrayList<IDataFile> getFiles() {
        return files;
    }
    //    public static void main(String[] args) {
//        // test file paths
//        for (IDataFile file : files) {
//            System.out.println(FileUtils.checkIfFileExists(file.getPath()) + " <= " + file.getPath());
//        }
//    }
}

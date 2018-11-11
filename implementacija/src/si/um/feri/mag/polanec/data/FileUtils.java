package si.um.feri.mag.polanec.data;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemoveDuplicates;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Util class for file related operations.
 */
public class FileUtils {

    private static final String CSV_DELIMITER = ";";
    private static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:S");
    private static PrintWriter currentLog;

    private static final boolean WRITE_TO_CONSOLE = true;

    public static boolean checkIfFileExists(String path) {
        return new File(path).exists();
    }

    /**
     * @param fileName path to csv file
     * @return object to write results to
     */
    public static PrintWriter getCSVFileWriterObject(String fileName) throws IOException {
        if (!fileName.endsWith(".csv")) {
            fileName = fileName + ".csv";
        }
        checkAndCreateLogDir();

        String fn = addDatePrefixToFilename(fileName);

        createFileWithException(fn);

        return new PrintWriter(new FileOutputStream(fn, true));
    }

    private static void createFileWithException(String fn) throws IOException {
        if (!checkIfFileExists(fn)) {
            if (!new File(fn).createNewFile()) {
                throw new FileNotFoundException("Could not create file.");
            }
        }
    }

    private static String addDatePrefixToFilename(String fileName) {
        return addDatePrefixToFilename(fileName, "yyyy_MM_dd_HH_mm");
    }

    private static String addDatePrefixToFilename(String fileName, String dateFormat) {
        Date d = new Date();
        DateFormat df = new SimpleDateFormat(dateFormat);
        return "logs/" + df.format(d) + "_" + fileName;
    }

    private static void checkAndCreateLogDir() throws FileNotFoundException {
        File f = new File("logs");
        if (!f.exists() || !f.isDirectory()) {
            if (!f.mkdir()) {
                throw new FileNotFoundException("Could not create directory 'logs'.");
            }
        }
    }

    public static void createLogFileInstance() throws IOException {
        checkAndCreateLogDir();
        String fn = addDatePrefixToFilename("log.log", "yyyy_MM_dd");
        createFileWithException(fn);
        currentLog = new PrintWriter(new FileOutputStream(fn, true));
    }

    public synchronized static void writeToLog(String msg) {
        if (currentLog == null) {
            try {
                createLogFileInstance();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        String output = getTimeStamp() + ": " + msg;
        if (WRITE_TO_CONSOLE) {
            System.out.println(output);
        }
        currentLog.println(output);
        currentLog.flush();
    }

    private static String getTimeStamp() {
        return LOG_DATE_FORMAT.format(new Date());
    }

    public synchronized static void writeToLog() {
        writeToLog("");
    }

    public static void closeLog() {
        closeConnection(currentLog);
    }

    public static synchronized void writeToFile(PrintWriter fileObj, String... params) {
        if (fileObj == null) {
            throw new NullPointerException("File obj is null.");
        }
        fileObj.println(String.join(CSV_DELIMITER, params));
        fileObj.flush();
    }

    public static void closeConnection(PrintWriter fileObj) {
        if (fileObj == null) {
            throw new NullPointerException("File obj is null.");
        }
        fileObj.close();
    }

    /**
     * Take random {@code numSamples} samples from {@code allSamples}.
     *
     * @param allSamples samples from arff file
     * @param numSamples number of samples to take from {@code allSamples}
     * @param random     random object with custom seed
     * @return smaller data set.
     * @throws IllegalArgumentException if {@code numSamples} is less than 1
     */
    public static Instances takeRandomSamples(Instances allSamples, int numSamples, Random random) throws Exception {
        RemoveDuplicates rm = new RemoveDuplicates();
        rm.setInputFormat(allSamples);

        allSamples = Filter.useFilter(allSamples, rm);
        if (numSamples == -1) {
            return allSamples;
        }
        Instances reduced = new Instances(allSamples, numSamples);

        double negSamplesPercent = calcSamplesRatio(allSamples, 0);
        copyRandomSamples(reduced, allSamples, random, (int) (((double) numSamples) * negSamplesPercent), 0);
        copyRandomSamples(reduced, allSamples, random, numSamples - reduced.numInstances(), 1);

        return reduced;
    }

    public static double calcSamplesRatio(Instances allSamples, int decision) {
        return getNumOfSamplesWithDecision(allSamples, decision) / ((double) allSamples.numInstances());
    }

    public static double getNumOfSamplesWithDecision(Instances allSamples, int decision) {
        int sum = 0;
        for (Instance sample : allSamples) {
            if (((int) sample.value(sample.numAttributes() - 1)) == decision) {
                sum++;
            }
        }
        return (double) sum;
    }

    /**
     * Copy {@code n} random samples with decision = {@code decision} from {@code source} to {@code destination}.
     *
     * @param destination copy to
     * @param source      copy from
     * @param random      random object with custom seed
     * @param n           items to copy
     * @param decision    decision to copy (class index).
     */
    private static void copyRandomSamples(Instances destination, Instances source, Random random, int n, int decision) {
        Instances tmpSource = new Instances(source);
        for (int i = 0; i < n; i++) {
            // 1. pick random sample
            Instance instance = tmpSource.get(random.nextInt(tmpSource.numInstances()));
            // 2. check if you can add instance
            if (((int) instance.value(instance.numAttributes() - 1)) != decision) {
                --i;
                continue;
            }
            // 3. add to destination
            destination.add(instance);
            // 4. remove instance from tmpSource
            tmpSource.remove(instance);
        }
    }

    /**
     * Combine samples from first {@code versionIndex} project files.
     *
     * @param projectFiles  all files for project
     * @param versionIndex  current version index
     * @param trainVersions fill versions for logging
     * @return combined instances
     */
    public static Instances combineSamples(List<IDataFile> projectFiles, int versionIndex, List<Double> trainVersions) throws Exception {
        // there will always be at least one file
        Instances instances = ConverterUtils.DataSource.read(projectFiles.get(0).getPath());
        trainVersions.add(projectFiles.get(0).getVersion());

        for (int i = 1; i <= versionIndex; i++) {
            instances.addAll(ConverterUtils.DataSource.read(projectFiles.get(i).getPath()));
            trainVersions.add(projectFiles.get(i).getVersion());
        }

        instances.setClassIndex(instances.numAttributes() - 1);

        RemoveDuplicates rm = new RemoveDuplicates();
        rm.setInputFormat(instances);

        return Filter.useFilter(instances, rm);
    }
}

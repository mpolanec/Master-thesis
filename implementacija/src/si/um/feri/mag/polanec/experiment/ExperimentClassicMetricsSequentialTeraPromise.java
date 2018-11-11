package si.um.feri.mag.polanec.experiment;

import si.um.feri.mag.polanec.data.DataFiles;
import si.um.feri.mag.polanec.data.FileUtils;
import si.um.feri.mag.polanec.data.IDataFile;
import si.um.feri.mag.polanec.enums.MetricsType;
import si.um.feri.mag.polanec.enums.VersionType;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ExperimentClassicMetricsSequentialTeraPromise extends Experiment {

    public ExperimentClassicMetricsSequentialTeraPromise(String[] args) {
        super(args);
    }

    @Override
    protected String getLogFileName() {
        return "classic_metrics_sequential_versions.csv";
    }

    @Override
    protected String[] getFileHeader() {
        return new String[]{
                "Project", "Classifier", "Measurement num.", "Seed", "Learning on version(s)", "Testing on version", "Num. TP", "Num. FP", "Num. TN", "Num. FN", "Correctly classified", "Incorrectly classified", "Precision", "Execution time (s)"
        };
    }

    @Override
    protected IDataFile getExperimentDataFile() {
        return DataFiles.getFile("all.arff");
    }

    @Override
    public String getExperimentName() {
        return "Classic metrics - sequential versions";
    }

    @Override
    protected MetricsType getMetricsType() {
        return MetricsType.CLASSIC_METRICS;
    }

    @Override
    protected VersionType getVersionType() {
        return VersionType.SEQUENTIAL_VERSIONS;
    }

    @Override
    protected void run_impl(List<ClassifierRunnable> runnables) throws Exception {

        HashMap<String, List<IDataFile>> projects = DataFiles.getProjects();

        for (Map.Entry<String, List<IDataFile>> entry : projects.entrySet()) {
            String project = entry.getKey();
            List<IDataFile> projectFiles = entry.getValue();

            // sort project files by version
            projectFiles.sort(Comparator.comparingDouble(IDataFile::getVersion));
            // each project has data about at least 2 versions
            for (int f = 0; f < projectFiles.size() - 1; f++) {
                // combine files to i, train on i + 1
                List<Double> trainVersions = new ArrayList<>();
                Instances trainingDataBase = FileUtils.combineSamples(projectFiles, f, trainVersions);
                Instances testDataBase = ConverterUtils.DataSource.read(projectFiles.get(f + 1).getPath());
                testDataBase.setClassIndex(testDataBase.numAttributes() - 1);
                double testVersion = projectFiles.get(f + 1).getVersion();

                for (Classifier c : Classifiers.getClassifiers()) {
                    // repeat nruns times
                    int currentSeed = baseSeed;

                    for (int i = 0; i < nruns; i++) {
                        currentSeed = calculateNextSeed(i, baseSeed, currentSeed);

                        Instances train = new Instances(trainingDataBase);
                        Instances test = new Instances(testDataBase);

                        Random rand = new Random(currentSeed);
                        // randomize training and test set
                        train.randomize(rand);
                        test.randomize(rand);

                        runnables.add(new SeqRunnable(c, train, test, i, currentSeed, project, trainVersions, testVersion));
                    }
                }
            }
        }
    }

    @Override
    protected void classifierEvaluation(ClassifierRunnable runnable) throws Exception {
        if (!(runnable instanceof SeqRunnable)) {
            throw new IllegalArgumentException("runnable must be an instance of SeqRunnable.");
        }
        SeqRunnable r = ((SeqRunnable) runnable);
        classifierEvaluation(r.classifier, r.data, r.testData, r.runNumber, r.randomSeed, r.projectName, r.trainingVersions, r.testVersion);
    }

    /**
     * @param cls              classifier
     * @param trainData        data to train
     * @param testData         data to test precision of classifier
     * @param runNumber        run number
     * @param currentSeed      current seed
     * @param projectName      name of the project
     * @param trainingVersions version of train data
     * @param testVersion      version of data beeing tested
     * @throws Exception /
     */
    private void classifierEvaluation(Classifier cls, Instances trainData, Instances testData, int runNumber, int currentSeed, String projectName, List<Double> trainingVersions, double testVersion) throws Exception {
        long startTime = System.nanoTime();
        Evaluation eval = new Evaluation(testData);

        Classifier clsCopy = AbstractClassifier.makeCopy(cls);
        clsCopy.buildClassifier(trainData);

        eval.evaluateModel(clsCopy, testData);

        // write results for this run to file
        long deltaT = System.nanoTime() - startTime;
        FileUtils.writeToFile(writerObj,
                projectName,
                cls.getClass().getName() + " " + Utils.joinOptions(((AbstractClassifier) cls).getOptions()),
                Integer.toString(runNumber),
                Integer.toString(currentSeed),
                String.join(", ", trainingVersions.stream().map(d -> Double.toString(d)).collect(Collectors.toList())),
                Double.toString(testVersion),
                Double.toString(eval.confusionMatrix()[1][1]).replace(".", ","),
                Double.toString(eval.confusionMatrix()[0][1]).replace(".", ","),
                Double.toString(eval.confusionMatrix()[0][0]).replace(".", ","),
                Double.toString(eval.confusionMatrix()[1][0]).replace(".", ","),
                Double.toString(eval.correct()).replace(".", ","),
                Double.toString(eval.incorrect()).replace(".", ","),
                Double.toString(eval.correct() / (eval.incorrect()+eval.correct())).replace(".", ","),
//                        eval.areaUnderROC(0) + "",
//                        eval.areaUnderROC(1) + "",
                Double.toString(((double) deltaT) / 1000000000.0).replace(".", ",")
        );
    }

    private class SeqRunnable extends Experiment.ClassifierRunnable {
        private Instances testData;
        private String projectName;
        private List<Double> trainingVersions;
        private double testVersion;

        SeqRunnable(Classifier classifier, Instances data, Instances testData, int runIndex, int randomSeed, String projectName, List<Double> trainingVersions, double testVersion) {
            super(classifier, data, runIndex, randomSeed);
            this.testData = new Instances(testData);
            this.projectName = projectName;
            this.trainingVersions = new ArrayList<>(trainingVersions);
            this.testVersion = testVersion;
        }
    }
}

package si.um.feri.mag.polanec.main;

import si.um.feri.mag.polanec.data.FileUtils;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Sprocesira rezultate zaporednih verzij.
 * <p>
 * Podatki so načeloma urejeni tako:
 * <p>
 * Projekt
 * |-> učna verzija
 * |-|-> klasifikator
 * |-|-|-> instanca rešitve
 */
public class MagSeqVersionResultProcessor {
    private static HashMap<String, String> classifierNameTransMap;

    static {
        classifierNameTransMap = new HashMap<>();
        classifierNameTransMap.put("weka.classifiers.functions.MultilayerPerceptron -L 0.05 -M 0.2 -N 1000 -V 0 -S 0 -E 20 -H \"10, 2\"", "MLP2");
        classifierNameTransMap.put("weka.classifiers.functions.MultilayerPerceptron -L 0.1 -M 0.2 -N 3000 -V 0 -S 0 -E 20 -H \"20, 15, 10, 5\"", "MLP3");
        classifierNameTransMap.put("weka.classifiers.functions.MultilayerPerceptron -L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H a", "MLP1");
        classifierNameTransMap.put("weka.classifiers.functions.SMO -C 1.0 -L 0.001 -P 1.0E-12 -N 0 -V -1 -W 1 -K \"weka.classifiers.functions.supportVector.PolyKernel -E 1.0 -C 250007\" -calibrator \"weka.classifiers.functions.Logistic -R 1.0E-8 -M -1 -num-decimal-places 4\"", "SMO");
        classifierNameTransMap.put("weka.classifiers.trees.J48 -C 0.25 -M 2", "C4.5-1");
        classifierNameTransMap.put("weka.classifiers.trees.J48 -U -M 2", "C4.5-2");
        classifierNameTransMap.put("weka.classifiers.trees.RandomForest -P 100 -I 100 -num-slots 4 -K 0 -M 1.0 -V 0.001 -S 1", "RF");
    }

    public static void main(String[] args) {
        File resFile = new File("./logs/2018_08_07_16_18_classic_metrics_sequential_versions.csv");
        try {
            HashMap<String, Project> projects = new HashMap<>();

            List<String> lines = Files.readAllLines(Paths.get(resFile.toURI()));

            // process data
            for (int i = 1; i < lines.size(); i++) {

                String[] data = lines.get(i).split(";");

                Project project = null;
                if (projects.containsKey(data[0])) {
                    project = projects.get(data[0]);
                } else {
                    project = new Project();
                    project.name = data[0];
                    projects.put(data[0], project);
                }
                project.addToVersion(data);
            }
            //  generate files
            for (Map.Entry<String, Project> entry : projects.entrySet()) {
                final PrintWriter pw = FileUtils.getCSVFileWriterObject(entry.getKey() + "_result.csv");

                Project p = entry.getValue();
                // write header
                pw.print("Klasifikator;");
                pw.println(String.join(";", p.versions.entrySet().stream().map(Map.Entry::getKey).sorted(String::compareTo).collect(toList())));

                // pridobi podatke za verzijo
                List<List<String>> data = new ArrayList<>();

                List<Version> sortedVersions = p.versions.entrySet().stream().map(Map.Entry::getValue).sorted(Comparator.comparing(o -> o.testVersion)).collect(toList());

                List<String> classifierNames = sortedVersions.get(0).classifierData.entrySet().stream().map(Map.Entry::getKey).sorted(String::compareTo).collect(toList());
                data.add(classifierNames);

                for (Version version : sortedVersions) {
                    ArrayList<String> columnData = new ArrayList<>();
                    data.add(columnData);
                    List<ClassifierData> sortedClassifiers = version.classifierData.entrySet().stream().map(Map.Entry::getValue).sorted(Comparator.comparing(o -> o.name)).collect(toList());
                    for (ClassifierData c : sortedClassifiers) {
                        columnData.add(Double.toString(c.getAveragePrecision()).replace(".", ","));
                    }
                }

                data = rotateData(data);

                // po vseh stolpcih
                for (int r = 0; r < data.size(); r++) {
                    for (int c = 0; c < data.get(0).size(); c++) {
                        pw.print(data.get(r).get(c) + ";");
                    }
                    pw.println();
                }

                pw.flush();
                pw.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<List<String>> rotateData(List<List<String>> input) {
        List<List<String>> output = new ArrayList<>();

        int rows = input.get(0).size();
        int cols = input.size();

        // pripravi vrstice
        for (int r = 0; r < rows; r++) {
            output.add(new ArrayList<>(cols));
        }

        int nx = 0;
        for (int r = 0; r < cols; r++) {
            for (int c = 0; c < rows; c++) {
                output.get(nx++).add(input.get(r).get(c));
            }
            nx = 0;
        }

        return output;
    }

    static class Project {
        String name;
        // ključ je verzija na keri testiramo
        HashMap<String, Version> versions = new HashMap<>();

        public void addToVersion(String[] data) {
            ResInstance instance = ResInstance.fromLine(data);

            if (versions.containsKey(instance.testVersion)) {
                Version version = versions.get(instance.testVersion);
                version.update(instance);
            } else {
                versions.put(instance.testVersion, Version.create(instance));
            }
        }

        public double getAveragePrecision() {
            return versions.entrySet().stream().map(Map.Entry::getValue).mapToDouble(Version::getAveragePrecision).average().orElse(0);
        }
    }

    static class Version {
        String trainVersions;
        String testVersion;

        HashMap<String, ClassifierData> classifierData = new HashMap<>();

        static Version create(ResInstance instance) {
            Version v = new Version();
            v.trainVersions = instance.trainVersions;
            v.testVersion = instance.testVersion;

            updateClassifierData(instance, v);

            return v;
        }

        private static void updateClassifierData(ResInstance instance, Version v) {
            if (v.classifierData.containsKey(instance.classifierName)) {
                v.classifierData.get(instance.classifierName).instances.add(instance);
            } else {
                ClassifierData c = new ClassifierData();
                c.name = instance.classifierName;
                c.instances.add(instance);
                v.classifierData.put(instance.classifierName, c);
            }
        }

        void update(ResInstance instance) {
            // verzije se ne spreminjajo
            updateClassifierData(instance, this);
        }

        public double getAveragePrecision() {
            return classifierData.entrySet().stream().map(Map.Entry::getValue).mapToDouble(ClassifierData::getAveragePrecision).average().orElse(0.0);
        }
    }

    static class ClassifierData {
        // ime klasifikatorja
        String name;

        // vsi primerki za en klasifikator
        ArrayList<ResInstance> instances = new ArrayList<>();

        public double getAveragePrecision() {
            return instances.stream().mapToDouble(value -> value.prec).average().orElse(0);
        }

        public double getAverageTP() {
            return instances.stream().mapToDouble(value -> value.TP).average().orElse(0);
        }

        public double getAverageTN() {
            return instances.stream().mapToDouble(value -> value.TN).average().orElse(0);
        }

        public double getAverageFP() {
            return instances.stream().mapToDouble(value -> value.FP).average().orElse(0);
        }

        public double getAverageFN() {
            return instances.stream().mapToDouble(value -> value.FN).average().orElse(0);
        }

        public double getAverageTime() {
            return instances.stream().mapToDouble(value -> value.time).average().orElse(0);
        }
    }

    static class ResInstance {
        String trainVersions;
        String testVersion;
        String classifierName;
        double TP, TN, FP, FN, prec, time;

        static ResInstance fromLine(String[] data) {
            ResInstance resInstance = new ResInstance();

            resInstance.classifierName = classifierNameTransMap.get(data[1]);
            resInstance.trainVersions = data[4];
            resInstance.testVersion = data[5];
            resInstance.TP = parseDouble(data[6]);
            resInstance.FP = parseDouble(data[7]);
            resInstance.TN = parseDouble(data[8]);
            resInstance.FN = parseDouble(data[9]);
            resInstance.prec = parseDouble(data[12]);
            resInstance.time = parseDouble(data[13]);

            return resInstance;
        }

        static double parseDouble(String value) {
            return Double.parseDouble(value.replace(",", "."));
        }
    }

}

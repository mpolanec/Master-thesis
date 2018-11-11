package si.um.feri.mag.polanec.main;

import si.um.feri.mag.polanec.data.DataFiles;
import si.um.feri.mag.polanec.data.FileUtils;
import si.um.feri.mag.polanec.data.IDataFile;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class MagSeqVersionResultHelper {

    public static void main(String[] args) {
        try {
            PrintWriter writer = FileUtils.getCSVFileWriterObject("seq_versions.csv");
            HashMap<String, List<IDataFile>> projects = DataFiles.getProjects();

            List<Map.Entry<String, List<IDataFile>>> sortedProjects = projects
                    .entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).collect(Collectors.toList());

            for (Map.Entry<String, List<IDataFile>> entry : sortedProjects) {
                String project = entry.getKey();
                List<IDataFile> projectFiles = entry.getValue();

                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                System.out.println("\t"+ project);
                System.out.println("-------------------------------------------------------------------------------------------------------------------");

                writer.println(project);
                writer.println();
                writer.println("Ucenje na;Testiranje na;Brez napak; Z napako; Brez napak(%); Z napako(%)");

                setw("train on", 25);
                System.out.print(" | ");
                setw("test on");
                System.out.print(" | ");
                setw("no bug", 16);
                System.out.print(" | ");
                setw("bug", 16);
                System.out.print(" | ");
                setw("no bug (%)", 16);
                System.out.print(" | ");
                setw("bug (%)", 16);
                System.out.print(" |");
                System.out.println();

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

                    setw(String.join(", ", trainVersions.stream().map(s->Double.toString(s)).collect(Collectors.toList())), 25);
                    writer.print("\"=\"\""+String.join(", ", trainVersions.stream().map(s->Double.toString(s)).collect(Collectors.toList()))+"\"\"\";");
                    System.out.print(" | ");
                    //"=""2008-10-03"=""
                    writer.print("\"=\"\""+testVersion+"\"\"\";");
                    setw(testVersion+"");
                    System.out.print(" | ");
                    setw(FileUtils.getNumOfSamplesWithDecision(trainingDataBase, 0) + "", 16);
                    writer.print(Double.toString(FileUtils.getNumOfSamplesWithDecision(trainingDataBase, 0)).replace(".", ",") + ";");
                    System.out.print(" | ");
                    setw(FileUtils.getNumOfSamplesWithDecision(trainingDataBase, 1) + "", 16);
                    writer.print(Double.toString(FileUtils.getNumOfSamplesWithDecision(trainingDataBase, 1)).replace(".", ",") + ";");
                    System.out.print(" | ");
                    setw(String.format("%.2f", (FileUtils.calcSamplesRatio(trainingDataBase, 0) * 100)) + "%", 16);
                    writer.print(String.format("%.2f", (FileUtils.calcSamplesRatio(trainingDataBase, 0) * 100)) + "%;");
                    System.out.print(" | ");
                    setw(String.format("%.2f", (FileUtils.calcSamplesRatio(trainingDataBase, 1) * 100)), 16);
                    writer.println(String.format("%.2f", (FileUtils.calcSamplesRatio(trainingDataBase, 1) * 100))+"%");
                    System.out.print(" |");
                    System.out.println();
                }
                writer.println();
                writer.println();
                System.out.println();
                System.out.println();
            }
            writer.flush();
            writer.close();
        } catch (Exception ignored) {

        }
    }
    public static void setw(String str){
        setw(str, 10);
    }
    public static void setw(String str, int width){
        setw(str, width, ' ');
    }
    public static void setw(String str, int width, char fill)
    {
        for (int x = str.length(); x < width; ++x)
        {
            System.out.print(fill);
        }
        System.out.print(str);
    }

}

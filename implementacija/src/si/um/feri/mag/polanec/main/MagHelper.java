package si.um.feri.mag.polanec.main;

import si.um.feri.mag.polanec.data.DataFiles;
import si.um.feri.mag.polanec.data.FileUtils;
import si.um.feri.mag.polanec.data.IDataFile;
import si.um.feri.mag.polanec.enums.DataSource;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class MagHelper {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.##");
        List<IDataFile> files = DataFiles
                .getFiles()
                .stream()
                .filter(iDataFile -> iDataFile.getDataSource() == DataSource.BUG_INF && !iDataFile.getProjectName().equals("All"))
                .collect(Collectors.toList());
        // project name
        System.out.println("Projekt;Verzija;Število razredov;Število razredov z napako;Št razredov z napako (%)");

        int sumRaz = 0;
        int sumRazNap = 0;
        double sumNapProc = 0;

        for (IDataFile p : files) {
            String projekt = p.getProjectName();
            double verzija = p.getVersion();
            int stRaz = calcStRaz(p);
            int stZNapako = 0;
            double stZNapakoProcent = 0;
            try {
                stZNapako = (int) FileUtils.getNumOfSamplesWithDecision(getInstances(p), 1);
                stZNapakoProcent = FileUtils.calcSamplesRatio(getInstances(p), 1) * 100.0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            sumRaz += stRaz;
            sumRazNap += stZNapako;
            sumNapProc += stZNapakoProcent;
            System.out.println(projekt + ";" + verzija + ";" + stRaz + ";" + stZNapako + ";" + df.format(stZNapakoProcent));
        }

        System.out.println("Skupaj razredov: " + sumRaz);
        System.out.println("Skupaj razredov z napako: " + sumRazNap);
        System.out.println("Skupaj napak: " + (df.format((double)sumRazNap/(double)sumRaz*100.0)));
    }

    private static int calcStRaz(IDataFile file) {
        try {
            Instances instances = getInstances(file);
            return instances.numInstances();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static Instances getInstances(IDataFile file) throws Exception {
        return ConverterUtils.DataSource.read(file.getPath());
    }
}

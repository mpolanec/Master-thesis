package si.um.feri.mag.polanec.experiment;

import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

import java.util.ArrayList;

/**
 * List of classifiers from Weka library, that will be used in our experiment.
 */
public class Classifiers {
    private static ArrayList<Classifier> classifiers;

    static {
        classifiers = new ArrayList<>();

        // DEFAULT C4.5
        J48 defaultC45 = new J48();

        J48 unPrunedC45 = new J48();
        unPrunedC45.setUnpruned(true);

        SMO smo = new SMO();
//        Logistic smoCalibarator = new Logistic();
//        smoCalibarator.setMaxIts(500);
//        smo.setCalibrator(smoCalibarator);

        // default multi layer perceptron
        MultilayerPerceptron mp1 = new MultilayerPerceptron();

        MultilayerPerceptron mp2 = new MultilayerPerceptron();
        mp2.setLearningRate(0.05);
        mp2.setHiddenLayers("10,2");
        mp2.setTrainingTime(1000);

        MultilayerPerceptron mp3 = new MultilayerPerceptron();
        mp3.setLearningRate(0.1);
        mp3.setHiddenLayers("20,15,10,5");
        mp3.setTrainingTime(3000);

        RandomForest forest = new RandomForest();
        // we are already in multi-thread mode
//        forest.setNumExecutionSlots(4);

        classifiers.add(defaultC45);
        classifiers.add(unPrunedC45);

        classifiers.add(smo);

        classifiers.add(mp1);
        classifiers.add(mp2);
        classifiers.add(mp3);

        classifiers.add(forest);

    }

    public static ArrayList<Classifier> getClassifiers() {
        return classifiers;
    }
}

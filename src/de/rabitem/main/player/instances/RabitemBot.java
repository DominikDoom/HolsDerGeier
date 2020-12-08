package de.rabitem.main.player.instances;

import de.rabitem.main.Main;
import de.rabitem.main.card.instances.PlayerCard;
import de.rabitem.main.player.Player;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class RabitemBot extends Player {
    /**
     * Constructor of Player
     *
     * @param name String name
     */
    public RabitemBot(String name) {
        super(name);
    }

    @Override
    public PlayerCard getNextCardFromPlayer(int pointCardValue) {
        return null;
    }

    // important note: weka library required
    public void setupMachineLearningModel(){
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(String.valueOf(Main.class.getResource("/resources/data.arff").toURI()));
            Instances train = source.getDataSet();
            train.setClassIndex(3);  // target attribute: win

            //build model
            NaiveBayes model = new NaiveBayes();
            try {
                model.buildClassifier(train);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //use
            Instances test = train;
            Evaluation eval = new Evaluation(test);
            eval.evaluateModel(model, test);
            int k = 0;
            for (Instance instance : test) {
                double actual = instance.classValue();
                double prediction = 0;
                prediction = eval.evaluateModelOnce(model, instance);
                System.out.printf("%2d.%4.0f%4.0f", ++k, actual, prediction);
                System.out.println(prediction != actual ? "wrong" : "right");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}

package containers.classes;

import sender.Sender;
import utils.ConsoleColors;
import utils.Constants;
import utils.Evaluators;

import java.util.ArrayList;

public class ExplosivesContainer extends HeavyContainer{
    public final int riskLevel;
    public final int maxTemp;

    public ExplosivesContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int armorThickness,
            String containerMaterial,
            int riskLevel,
            int maxTemp,
            Sender sender
    ){
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial, sender);
        this.riskLevel = evalRisk(riskLevel);
        this.maxTemp = maxTemp;
    }

    public ExplosivesContainer(){
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo Weight"),
                Evaluators.getArrayListFromInput("safety measures"),
                Evaluators.getArrayListFromInput("certificates"),
                Evaluators.getIntFromInput("Armor thickness in millimeters"),
                Evaluators.getStringFromInput("StandardContainer material")
        );
        this.riskLevel = evalRisk(Evaluators.getIntFromInput("Risk level 0-5"));
        this.maxTemp = Evaluators.getIntFromInput("Max Temperature");
        ConsoleColors.printGreen("Successfully created container!");
    }

    private int evalRisk(int value){
        if(Constants.MIN_RISK_VALUE < value && value < Constants.MAX_RISK_VALUE){
            return riskLevel;
        }
        else if(value < Constants.MIN_RISK_VALUE){
            ConsoleColors.printRed("Invalid value: Risk level too low, setting to 0");
            return 0;
        }
        ConsoleColors.printRed("Invalid value: Risk level too high, setting to 5");
        return 5;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", risk level: " + riskLevel +
                ", maximum temp: " + maxTemp;
    }

    @Override
    public String toSaveString(){
        return super.toSaveString() +
                ", \n\triskLevel: " + riskLevel +
                ", \n\tmaxTemp: " + maxTemp;
    }
}

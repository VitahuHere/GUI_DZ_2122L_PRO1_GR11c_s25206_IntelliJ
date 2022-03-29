package containers.classes;

import containers.abstracts.ToxicAbstract;
import containers.interfaces.LiquidInterface;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;

public class ToxicLiquidContainer extends ToxicAbstract implements LiquidInterface {

    public final boolean acidSafe;

    public ToxicLiquidContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int armorThickness,
            String containerMaterial,
            boolean acidSafe
    ){
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial);
        this.acidSafe = acidSafe;
    }

    public ToxicLiquidContainer(){
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo Weight"),
                Evaluators.getArrayListFromInput("safety measures"),
                Evaluators.getArrayListFromInput("certificates"),
                Evaluators.getIntFromInput("Armor thickness"),
                Evaluators.getStringFromInput("Container material")
        );
        this.acidSafe = Evaluators.getBooleanFromInput("Acid safe? [y/N]");
        ConsoleColors.printGreen("Successfully created container!");
        ConsoleColors.printYellow(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", \nacid safe: " + this.acidSafe;
    }
}

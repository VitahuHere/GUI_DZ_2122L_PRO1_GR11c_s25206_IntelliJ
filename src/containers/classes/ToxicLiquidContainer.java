package containers.classes;

import containers.abstracts.ToxicAbstract;
import containers.interfaces.LiquidInterface;
import sender.Sender;
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
            boolean acidSafe,
            Sender sender
    ){
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial, sender);
        this.acidSafe = acidSafe;
    }

    public ToxicLiquidContainer(){
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo Weight"),
                Evaluators.getArrayListFromInput("Safety measures"),
                Evaluators.getArrayListFromInput("Certificates"),
                Evaluators.getIntFromInput("Armor thickness in millimeters"),
                Evaluators.getStringFromInput("Container material")
        );
        this.acidSafe = Evaluators.getBooleanFromInput("Acid safe? [y/N]");
        ConsoleColors.printGreen("Successfully created container!");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", acid safe: " + this.acidSafe +
                ", valve diameter: " + valveDiameter;
    }

    @Override
    public String toSaveString(){
        return super.toSaveString() +
                ", \n\tacidSafe: " + this.acidSafe +
                ", \n\tvalveDiameter: " + valveDiameter;
    }
}

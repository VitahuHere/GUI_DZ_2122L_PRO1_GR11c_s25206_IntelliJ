package containers.classes;

import containers.abstracts.ToxicAbstract;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;

public class ToxicLooseMaterialContainer extends ToxicAbstract {

    public final boolean waterproof;

    public ToxicLooseMaterialContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int armorThickness,
            String containerMaterial,
            boolean waterproof
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial);
        this.waterproof = waterproof;
    }

    public ToxicLooseMaterialContainer(){
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo Weight"),
                Evaluators.getArrayListFromInput("safety measures"),
                Evaluators.getArrayListFromInput("certificates"),
                Evaluators.getIntFromInput("Armor thickness in millimeters"),
                Evaluators.getStringFromInput("Container material")
        );
        this.waterproof = Evaluators.getBooleanFromInput("Is it waterproof?");
        ConsoleColors.printGreen("Successfully created container!");
        ConsoleColors.printYellow(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", \nwaterproof: " + waterproof;
    }
}

package containers.abstracts;

import containers.classes.HeavyContainer;

import java.util.ArrayList;

public abstract class ToxicAbstract extends HeavyContainer {
    protected ToxicAbstract(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int armorThickness,
            String containerMaterial
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial);
    }
}

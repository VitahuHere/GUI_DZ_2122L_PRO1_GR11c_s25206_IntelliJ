package containers.abstracts;

import containers.classes.HeavyContainer;

public abstract class ToxicAbstract extends HeavyContainer {
    public ToxicAbstract(
            int tare,
            int size,
            int cargoWeight,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial);
    }
}

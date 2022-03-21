package containers.classes;

import containers.interfaces.ElectricInterface;

public class ChillerContainer extends HeavyContainer implements ElectricInterface {
    public ChillerContainer(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial,
            int powerDraw
    ) {
        super(tare, size, safetyMeasures, certificates, armorThickness, containerMaterial);
    }
}

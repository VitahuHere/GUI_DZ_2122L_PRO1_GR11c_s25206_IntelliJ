package containers.classes;

public class ToxicSolid extends HeavyContainer {
    public ToxicSolid(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial
    ) {
        super(tare, size, safetyMeasures, certificates, armorThickness, containerMaterial);
    }
}

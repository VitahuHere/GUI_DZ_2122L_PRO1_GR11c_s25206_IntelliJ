package containers.classes;

public class HeavyContainer extends Container {
    public final int armorThickness;
    public final String containerMaterial;

    public HeavyContainer(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial
    ) {
        super(tare, size, safetyMeasures, certificates);
        this.armorThickness = armorThickness;
        this.containerMaterial = containerMaterial;
    }
}

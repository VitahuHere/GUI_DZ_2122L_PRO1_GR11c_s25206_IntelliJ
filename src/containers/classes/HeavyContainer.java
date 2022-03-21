package containers.classes;

public class HeavyContainer extends Container {
    public final int armorThickness;
    public final String containerMaterial;

    /**
     * id -> identifier for container. Unique at creation.
     * @param tare identifier for container. Unique at creation.
     * @param size weight of container just by itself.
     * @param safetyMeasures example: 20, 40, 45.
     * @param certificates determines what is it allowed to carry.
     * @param armorThickness determines how thick in millimeters is the outside shell.
     * @param containerMaterial determines what material has been used to make this container.
     */

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

    @Override
    public String toString() {
        return super.toString() +
                ", armorThickness: " + this.armorThickness +
                ", containerMaterial: " + this.containerMaterial;
    }
}

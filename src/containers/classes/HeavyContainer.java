package containers.classes;

import utils.Statics;

public class HeavyContainer extends Container {
    public final int armorThickness;
    public final String containerMaterial;

    /**
     * id {@value Statics#containerIndex} int, identifier for container. Unique at creation. Increments with each new one.
     * @param tare int, weight of container just by itself.
     * @param size int, example: 20, 40, 45.
     * @param safetyMeasures String[], what kind of locks or bar were used to secure container.
     * @param certificates String[], determines what is it allowed to carry.
     * @param armorThickness int, determines how thick in millimeters is the outside shell.
     * @param containerMaterial String, determines what material has been used to make this container.
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

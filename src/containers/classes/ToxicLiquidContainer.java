package containers.classes;

import containers.interfaces.LiquidInterface;
import utils.Statics;

public class ToxicLiquidContainer extends HeavyContainer implements LiquidInterface {

    public final boolean acidSafe;

    /**
     * id {@value Statics#containerIndex} int, identifier for container. Unique at creation. Increments with each new one.
     * @param tare int, weight of container just by itself.
     * @param size int, example: 20, 40, 45.
     * @param safetyMeasures String[], what kind of locks or bar were used to secure container.
     * @param certificates String[], determines what is it allowed to carry.
     * @param armorThickness int, determines how thick in millimeters is the outside shell.
     * @param containerMaterial String, determines what material has been used to make this container.
     * @param acidSafe boolean, determines whether container can carry acids.
     */
    public ToxicLiquidContainer(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial,
            boolean acidSafe
    ){
        super(tare, size, safetyMeasures, certificates, armorThickness, containerMaterial);
        this.acidSafe = acidSafe;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", acidSafe: " + this.acidSafe;
    }
}

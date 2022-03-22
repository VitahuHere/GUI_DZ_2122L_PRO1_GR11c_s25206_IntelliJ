package containers.classes;

import utils.Statics;

public class LiquidsContainer extends Container {
    public final int maxCapacity;
    public final int valveDiameter;

    /**
     * id {@value Statics#containerIndex} int, identifier for container. Unique at creation. Increments with each new one.
     * @param tare int, weight of container just by itself.
     * @param size int, example: 20, 40, 45.
     * @param cargoWeight int, net weight of cargo inside container.
     * totalWeight - int, total weight of container and cargo inside.
     * @param safetyMeasures String[], what kind of locks or bar were used to secure container.
     * @param certificates String[], determines what is it allowed to carry.
     * @param maxCapacity int, determines how much liquid can be stored safely.
     * @param valveDiameter int, specifies diameter of the valve to use while draining and filling.
     */

    public LiquidsContainer(
            int tare,
            int size,
            int cargoWeight,
            String[] safetyMeasures,
            String[] certificates,
            int maxCapacity,
            int valveDiameter
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates);
        this.maxCapacity = maxCapacity;
        this.valveDiameter = valveDiameter;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", maxCapacity=" + maxCapacity +
                ", valveDiameter=" + valveDiameter;
    }
}

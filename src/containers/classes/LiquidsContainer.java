package containers.classes;

public class LiquidsContainer extends Container {
    public final int maxCapacity;
    public final int valveDiameter;

    /**
     * id -> identifier for container. Unique at creation.
     * @param tare identifier for container. Unique at creation.
     * @param size weight of container just by itself.
     * @param safetyMeasures example: 20, 40, 45.
     * @param certificates determines what is it allowed to carry.
     * @param maxCapacity determines how much liquid can be stored safely.
     * @param valveDiameter specifies diameter of the valve to use while draining and filling.
     */

    public LiquidsContainer(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates,
            int maxCapacity,
            int valveDiameter
    ) {
        super(tare, size, safetyMeasures, certificates);
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

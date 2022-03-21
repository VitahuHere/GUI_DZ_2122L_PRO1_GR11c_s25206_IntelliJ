package containers.classes;

public class LiquidsContainer extends Container {
    public final int maxCapacity;
    public final int valveDiameter;

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

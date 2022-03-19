package containers;

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
}

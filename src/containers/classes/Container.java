package containers.classes;

import main.Statics;

import java.util.Arrays;

public class Container {
    public final int id;
    public final int tare;
    public final int size;

    public String[] safetyMeasures;
    public String[] certificates;

    /**
     * id -> identifier for container. Unique at creation.
     * @param tare identifier for container. Unique at creation.
     * @param size weight of container just by itself.
     * @param safetyMeasures example: 20, 40, 45.
     * @param certificates determines what is it allowed to carry.
     */
    public Container(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates
    ) {
        this.id = Statics.containerIndex++;
        this.tare = tare;
        this.size = size;
        this.safetyMeasures = safetyMeasures;
        this.certificates = certificates;
    }

    @Override
    public String toString() {
        return "Container type: " +
                this.getClass().getSimpleName() +
                ", id: " + id +
                ", tare: " + tare +
                ", size: " + size +
                ", safetyMeasures: " + Arrays.toString(safetyMeasures) +
                ", certificates: " + Arrays.toString(certificates);
    }
}

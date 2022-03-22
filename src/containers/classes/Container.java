package containers.classes;

import utils.Statics;

import java.util.Arrays;

public class Container {
    public final int id;
    public final int tare;
    public final int size;

    public String[] safetyMeasures;
    public String[] certificates;

    /**
     * id {@value Statics#containerIndex} int, identifier for container. Unique at creation. Increments with each new one.
     * @param tare int, weight of container just by itself.
     * @param size int, example: 20, 40, 45.
     * @param safetyMeasures String[], what kind of locks or bar were used to secure container.
     * @param certificates String[], determines what is it allowed to carry.
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

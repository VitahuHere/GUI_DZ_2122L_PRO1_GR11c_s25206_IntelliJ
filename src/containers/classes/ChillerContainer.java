package containers.classes;

import containers.interfaces.ElectricInterface;
import utils.Statics;

public class ChillerContainer extends HeavyContainer implements ElectricInterface {

    // Measured in Watts
    public final int maximumWattPowerDraw;

    /**
     * id {@value Statics#containerIndex} int, identifier for container. Unique at creation. Increments with each new one.
     * @param tare int, weight of container just by itself.
     * @param size int, example: 20, 40, 45.
     * @param safetyMeasures String[], what kind of locks or bar were used to secure container.
     * @param certificates String[], determines what is it allowed to carry.
     * @param armorThickness int, determines how thick in millimeters is the outside shell.
     * @param containerMaterial String, determines what material has been used to make this container.
     * @param maximumWattPowerDraw int, determines maximum power possible power draw of this container.
     */
    public ChillerContainer(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial,
            int maximumWattPowerDraw
    ) {
        super(tare, size, safetyMeasures, certificates, armorThickness, containerMaterial);
        this.maximumWattPowerDraw = maximumWattPowerDraw;
    }

    public String toString(){
        return super.toString() +
                ", maximumWattPowerDraw: " + this.maximumWattPowerDraw;
    }
}

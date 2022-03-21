package containers.classes;

import containers.interfaces.ElectricInterface;

public class ChillerContainer extends HeavyContainer implements ElectricInterface {

    // Measured in Watts
    public final int maximumWattPowerDraw;

    /**
     * id -> identifier for container. Unique at creation.
     * @param tare identifier for container. Unique at creation.
     * @param size weight of container just by itself.
     * @param safetyMeasures example: 20, 40, 45.
     * @param certificates determines what is it allowed to carry.
     * @param armorThickness determines how thick in millimeters is the outside shell.
     * @param containerMaterial determines what material has been used to make this container.
     * @param maximumWattPowerDraw determines maximum power possible power draw of this container.
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

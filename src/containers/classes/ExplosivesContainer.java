package containers.classes;

public class ExplosivesContainer extends HeavyContainer{
    public final int riskLevel;
    public final int maxTemp;

    /**
     * id -> identifier for container. Unique at creation.
     * @param tare identifier for container. Unique at creation.
     * @param size weight of container just by itself.
     * @param safetyMeasures example: 20, 40, 45.
     * @param certificates determines what is it allowed to carry.
     * @param armorThickness determines how thick in millimeters is the outside shell.
     * @param containerMaterial determines what material has been used to make the container.
     * @param riskLevel determines how dangerous is material stored inside the container.
     * @param maxTemp determines maximum safe temperature for material stored inside container.
     */

    public ExplosivesContainer(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial,
            int riskLevel,
            int maxTemp
    ){
        super(tare, size, safetyMeasures, certificates, armorThickness, containerMaterial);
        this.riskLevel = riskLevel;
        this.maxTemp = maxTemp;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", riskLevel: " + riskLevel +
                ", maxTemp: " + maxTemp;
    }
}

package containers.classes;

public class ExplosivesContainer extends HeavyContainer{
    public final int riskLevel;
    public final int maxTemp;

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
                ", riskLevel=" + riskLevel +
                ", maxTemp=" + maxTemp;
    }
}

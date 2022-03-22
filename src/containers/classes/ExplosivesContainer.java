package containers.classes;

import utils.ConsoleColors;
import utils.Statics;

public class ExplosivesContainer extends HeavyContainer{
    public final int riskLevel;
    public final int maxTemp;

    /**
     * id {@value Statics#containerIndex} int, identifier for container. Unique at creation. Increments with each new one.
     * @param tare int, weight of container just by itself.
     * @param size int, example: 20, 40, 45.
     * @param cargoWeight int, net weight of cargo inside container.
     * totalWeight - int, total weight of container and cargo inside.
     * @param safetyMeasures String[], what kind of locks or bar were used to secure container.
     * @param certificates String[], determines what is it allowed to carry.
     * @param armorThickness int, determines how thick in millimeters is the outside shell.
     * @param containerMaterial String, determines what material has been used to make this container.
     * @param riskLevel int, determines how dangerous material stored inside the container is on the scale from 0-5.
     * @param maxTemp int, determines maximum safe temperature for material stored inside container.
     */

    public ExplosivesContainer(
            int tare,
            int size,
            int cargoWeight,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial,
            int riskLevel,
            int maxTemp
    ){
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial);
        this.riskLevel = evalRisk(riskLevel);
        this.maxTemp = maxTemp;
    }

    private int evalRisk(int value){
        if(Statics.minRiskValue < value && value < Statics.maxRiskValue){
            return riskLevel;
        }
        else if(value < Statics.minRiskValue){
            ConsoleColors.printRed("Invalid value: Risk level too low, setting to 0 [y]");
        }
        return 5;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", riskLevel: " + riskLevel +
                ", maxTemp: " + maxTemp;
    }
}

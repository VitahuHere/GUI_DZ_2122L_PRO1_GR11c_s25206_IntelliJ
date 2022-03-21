package containers.classes;

import containers.interfaces.LiquidInterface;

public class ToxicLiquid extends HeavyContainer implements LiquidInterface {
    public ToxicLiquid(
            int tare,
            int size,
            String[] safetyMeasures,
            String[] certificates,
            int armorThickness,
            String containerMaterial
    ){
        super(tare, size, safetyMeasures, certificates, armorThickness, containerMaterial);
    }
}

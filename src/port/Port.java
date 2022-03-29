package port;

import containers.classes.StandardContainer;
import ship.Ship;

import java.util.ArrayList;

public class Port {
    public final String name;

    private final Warehouse warehouse;

    private ArrayList<Ship> ships;

    public Port(String name, int warehouseCapacity) {
        this.name = name;
        this.warehouse = new Warehouse(warehouseCapacity);
    }

    public void addContainer(StandardContainer container) {
        this.warehouse.addContainer(container);
    }
}

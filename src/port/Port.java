package port;

import containers.classes.StandardContainer;
import ship.Ship;

import java.util.ArrayList;

public class Port {
    public static ArrayList<Ship> ships = new ArrayList<>();
    public static Warehouse warehouse;

    public Port(int capacity) {
        warehouse = new Warehouse(capacity);
    }

    public static void dockShip(Ship ship) {
        ships.add(ship);
    }

    public static void unDockShip(Ship ship) {
        ships.remove(ship);
    }

    public static void unloadContainer(Ship ship, StandardContainer container) {
        ship.offloadContainerToWarehouse(container);
        warehouse.addContainer(container);
    }
}

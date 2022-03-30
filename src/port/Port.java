package port;

import ship.Ship;

import java.util.ArrayList;

public class Port {
    public static String name;
    public static ArrayList<Ship> ships;
    public static Warehouse warehouse;

    public Port(String portName, int warehouseCapacity) {
        name = portName;
        ships = new ArrayList<>();
        warehouse = new Warehouse(warehouseCapacity);
    }
}

package port;

import ship.Ship;
import utils.Constants;

import java.util.ArrayList;

public class Port {
    // middle class for connecting all important classes
    public static String name = Constants.PORT_NAME;
    public static ArrayList<Ship> ships = new ArrayList<>();
    public static Train train = new Train();
    public static Warehouse warehouse = new Warehouse(Constants.MAX_WAREHOUSE_CAPACITY);
}

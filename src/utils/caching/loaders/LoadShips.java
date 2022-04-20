package utils.caching.loaders;

import ship.Ship;
import utils.Constants;
import utils.caching.Parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class LoadShips {
    public static final ArrayList<HashMap<String, String>> shipMap = new ArrayList<>();
    public static ArrayList<Ship> allShips = new ArrayList<>();
    public static void loadShips() {
        String[] places = {Constants.PORT_SHIPS, Constants.APP_SHIPS};
        for (String place : places) {
            ArrayList<HashMap<String, String>> x = Parser.parse(place);
            for(HashMap<String, String> ship : x) {
                containerIds(ship);
                ship.put("place", (place.equals(Constants.PORT_SHIPS) ? "port" : "app"));
                shipMap.add(ship);
            }
        }
        shipMap.sort(Comparator.comparingInt(o -> Integer.parseInt(o.get("id"))));
        for(HashMap<String, String> ship : shipMap) {
            Ship s = new Ship(
                    ship.get("name"),
                    ship.get("homePort"),
                    Integer.parseInt(ship.get("maxCargoWeight")),
                    Integer.parseInt(ship.get("maxContainersCount")),
                    Integer.parseInt(ship.get("maxToxicExplosiveContainersCount")),
                    Integer.parseInt(ship.get("maxHeavyContainersCount")),
                    Integer.parseInt(ship.get("maxElectricContainersCount"))
                    );
            s.departurePort = ship.get("departurePort");
            s.arrivalPort = ship.get("arrivalPort");
            allShips.add(s);
        }
    }

    static void containerIds(HashMap<String, String> transport) {
        ArrayList<HashMap<String, String>> shipContainers = Parser.containerExtraction(transport.get("containers"));
        if(shipContainers != null && shipContainers.size() > 0) {
            for(HashMap<String, String> shipContainer : shipContainers) {
                if(transport.containsKey("ids")){
                    transport.put("ids", transport.get("ids") + "," + shipContainer.get("id"));
                }
                else {
                    transport.put("ids", shipContainer.get("id"));
                }
            }
        }
    }
}

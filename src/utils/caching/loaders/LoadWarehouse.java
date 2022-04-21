package utils.caching.loaders;

import port.Warehouse;
import utils.Constants;
import utils.caching.Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadWarehouse {
    public static Warehouse warehouse = new Warehouse(Constants.MAX_WAREHOUSE_CAPACITY);
    public static ArrayList<Integer> containerIds = new ArrayList<>();

    public static void loadWarehouse() {
        ArrayList<HashMap<String, String>> wh = Parser.parse(Constants.PORT_WAREHOUSE);
        HashMap<String, String> whDetail = wh.get(0);
        ArrayList<HashMap<String, String>> WhContainers = Parser.containerExtraction(whDetail.get("containers"));
        if (WhContainers != null && WhContainers.size() > 0) {
            for (HashMap<String, String> container : WhContainers) {
                containerIds.add(Integer.parseInt(container.get("id")));
            }
        }
    }
}

package utils.caching.loaders;

import port.Train;
import utils.Constants;
import utils.caching.Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadTrains {
    public static Train portTrain;
    public static ArrayList<Integer> containerIds = new ArrayList<>();

    public static void loadTrains() {
        ArrayList<HashMap<String, String>> x = Parser.parse(Constants.PORT_TRAIN);
        for (HashMap<String, String> train : x) {
            LoadShips.containerIds(train);
            Train t = new Train();
            t.setParams(Integer.parseInt(train.get("id")), Integer.parseInt(train.get("id")));
            portTrain = t;
            for(String s : train.get("ids").split(",")){
                containerIds.add(Integer.parseInt(s));
            }
        }
    }
}

package utils.caching.loaders;

import containers.classes.*;
import main.App;
import sender.Sender;
import utils.Constants;
import utils.caching.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static utils.Returns.findSender;

public class LoadContainers {
    public static final ArrayList<StandardContainer> allContainers = new ArrayList<>();

    private static ArrayList<HashMap<String, String>> extractContainers(String path) {
        ArrayList<HashMap<String, String>> allContainers = new ArrayList<>();
        ArrayList<HashMap<String, String>> storage = Parser.parse(path);
        for (HashMap<String, String> map : storage) {
            ArrayList<HashMap<String, String>> shipContainers = Parser.containerExtraction(map.get("containers"));
            if (shipContainers != null) {
                allContainers.addAll(shipContainers);
            }
        }
        return allContainers;
    }

    private static ArrayList<HashMap<String, String>> getContainers() {
        ArrayList<HashMap<String, String>> allContainers = new ArrayList<>(Parser.parse(Constants.APP_CONTAINERS));
        String[] places = {Constants.PORT_SHIPS, Constants.APP_SHIPS, Constants.PORT_WAREHOUSE, Constants.PORT_TRAIN};
        for (String place : places) {
            ArrayList<HashMap<String, String>> x = extractContainers(place);
            if (x.size() > 0) {
                allContainers.addAll(x);
            }
        }

        return allContainers;
    }

    public static void loadAppContainers() {
        ArrayList<HashMap<String, String>> alex = getContainers();
        alex.sort(Comparator.comparing(o -> Integer.parseInt(o.get("id"))));
        int i = 1;
        for (HashMap<String, String> map : alex) {
            if (LoadRemovedIds.removedIds.contains(i)) {
                StandardContainer container = new StandardContainer(0, 0, 0, new ArrayList<>(), new ArrayList<>(), new Sender("", "", "", ""));
                App.containers.remove(container);
            }
            StandardContainer container = null;
            switch (map.get("type")) {
                case "StandardContainer" -> container = Standard(map);
                case "HeavyContainer" -> container = Heavy(map);
                case "LiquidsContainer" -> container = Liquids(map);
                case "ExplosivesContainer" -> container = Explosive(map);
                case "ChillerContainer" -> container = Chiller(map);
                case "ToxicLiquidContainer" -> container = ToxicLiquid(map);
                case "ToxicLooseMaterialContainer" -> container = ToxicLoose(map);
            }
            if (container != null) {
                if (!map.get("arrivalDate").equals("null")) {
                    container.arrivalDate = LocalDate.parse(map.get("arrivalDate"));
                } else {
                    container.arrivalDate = null;
                }
                if (!map.get("dueDate").equals("null")) {
                    container.dueDate = LocalDate.parse(map.get("dueDate"));
                } else {
                    container.dueDate = null;
                }
            }
            allContainers.add(container);
            i++;
        }
    }

    private static StandardContainer Standard(HashMap<String, String> map) {
        return new StandardContainer(
                Integer.parseInt(map.get("tare")),
                Integer.parseInt(map.get("size")),
                Integer.parseInt(map.get("cargoWeight")),
                Parser.getParamList(map.get("safetyMeasures")),
                Parser.getParamList(map.get("certificates")),
                findSender(map.get("sender"))
        );
    }

    private static StandardContainer Heavy(HashMap<String, String> map) {
        return new HeavyContainer(
                Integer.parseInt(map.get("tare")),
                Integer.parseInt(map.get("size")),
                Integer.parseInt(map.get("cargoWeight")),
                Parser.getParamList(map.get("safetyMeasures")),
                Parser.getParamList(map.get("certificates")),
                Integer.parseInt(map.get("armorThickness")),
                map.get("containerMaterial"),
                findSender(map.get("sender"))
        );
    }

    private static StandardContainer Liquids(HashMap<String, String> map) {
        return new LiquidsContainer(
                Integer.parseInt(map.get("tare")),
                Integer.parseInt(map.get("size")),
                Integer.parseInt(map.get("cargoWeight")),
                Parser.getParamList(map.get("safetyMeasures")),
                Parser.getParamList(map.get("certificates")),
                Integer.parseInt(map.get("maxCapacity")),
                findSender(map.get("sender"))
        );
    }

    private static StandardContainer Chiller(HashMap<String, String> map) {
        return new ChillerContainer(
                Integer.parseInt(map.get("tare")),
                Integer.parseInt(map.get("size")),
                Integer.parseInt(map.get("cargoWeight")),
                Parser.getParamList(map.get("safetyMeasures")),
                Parser.getParamList(map.get("certificates")),
                Integer.parseInt(map.get("armorThickness")),
                map.get("containerMaterial"),
                Integer.parseInt(map.get("maximumWattPowerDraw")),
                findSender(map.get("sender"))
        );
    }

    private static StandardContainer Explosive(HashMap<String, String> map) {
        return new ExplosivesContainer(
                Integer.parseInt(map.get("tare")),
                Integer.parseInt(map.get("size")),
                Integer.parseInt(map.get("cargoWeight")),
                Parser.getParamList(map.get("safetyMeasures")),
                Parser.getParamList(map.get("certificates")),
                Integer.parseInt(map.get("armorThickness")),
                map.get("containerMaterial"),
                Integer.parseInt(map.get("riskLevel")),
                Integer.parseInt(map.get("maxTemp")),
                findSender(map.get("sender"))
        );
    }

    private static StandardContainer ToxicLiquid(HashMap<String, String> map) {
        return new ToxicLiquidContainer(
                Integer.parseInt(map.get("tare")),
                Integer.parseInt(map.get("size")),
                Integer.parseInt(map.get("cargoWeight")),
                Parser.getParamList(map.get("safetyMeasures")),
                Parser.getParamList(map.get("certificates")),
                Integer.parseInt(map.get("armorThickness")),
                map.get("containerMaterial"),
                Boolean.parseBoolean(map.get("acidSafe")),
                findSender(map.get("sender"))
        );
    }

    private static StandardContainer ToxicLoose(HashMap<String, String> map) {
        return new ToxicLooseMaterialContainer(
                Integer.parseInt(map.get("tare")),
                Integer.parseInt(map.get("size")),
                Integer.parseInt(map.get("cargoWeight")),
                Parser.getParamList(map.get("safetyMeasures")),
                Parser.getParamList(map.get("certificates")),
                Integer.parseInt(map.get("armorThickness")),
                map.get("containerMaterial"),
                Boolean.parseBoolean(map.get("waterproof")),
                findSender(map.get("sender"))
        );
    }
}

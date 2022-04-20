package utils.caching.loaders;

import containers.classes.*;
import utils.Constants;
import utils.caching.Parser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import static utils.Returns.*;

public class LoadContainers {
    private static final ArrayList<StandardContainer> allContainers = new ArrayList<>();

    private static ArrayList<HashMap<String, String>> extractContainers(String path){
        ArrayList<HashMap<String, String>> allContainers = new ArrayList<>();
        ArrayList<HashMap<String, String>> storage = Parser.parse(path);
        for(HashMap<String, String> map : storage){
            ArrayList<HashMap<String, String>> shipContainers = Parser.containerExtraction(map.get("containers"));
            if(shipContainers != null){
                allContainers.addAll(shipContainers);
            }
        }
        return allContainers;
    }

    private static ArrayList<HashMap<String, String>> getContainers(){
        ArrayList<HashMap<String, String>> allContainers = new ArrayList<>(Parser.parse(Constants.APP_CONTAINERS));
        String[] places = {Constants.PORT_SHIPS, Constants.APP_SHIPS, Constants.PORT_WAREHOUSE, Constants.PORT_TRAIN};
        for(String place : places){
            ArrayList<HashMap<String, String>> x = extractContainers(place);
            if(x.size() > 0){
                allContainers.addAll(x);
            }
        }

        return allContainers;
    }

    public static void loadAppContainers() {

        for (HashMap<String, String> map : getContainers()) {
            System.out.println(map);
//            switch (map.get("type")) {
//                case "StandardContainer" -> allContainers.add(Standard(map));
//                case "HeavyContainer" -> allContainers.add(Heavy(map));
//                case "LiquidsContainer" -> allContainers.add(Liquids(map));
//                case "ExplosivesContainer" -> allContainers.add(Explosive(map));
//                case "ChillerContainer" -> allContainers.add(Chiller(map));
//                case "ToxicLiquidContainer" -> allContainers.add(ToxicLiquid(map));
//                case "ToxicLooseMaterialContainer" -> allContainers.add(ToxicLoose(map));
//            }
        }
    }

//    private static StandardContainer Standard(HashMap<String, String> map) {
//        return new StandardContainer(
//                Integer.parseInt(map.get("tare")),
//                Integer.parseInt(map.get("size")),
//                Integer.parseInt(map.get("cargoWeight")),
//                parseArrays(map.get("safetyMeasures")),
//                parseArrays(map.get("certificates")),
//                findSender(map.get("sender"))
//        );
//    }
//
//    private static StandardContainer Heavy(HashMap<String, String> map) {
//        return new HeavyContainer(
//                Integer.parseInt(map.get("tare")),
//                Integer.parseInt(map.get("size")),
//                Integer.parseInt(map.get("cargoWeight")),
//                parseArrays(map.get("safetyMeasures")),
//                parseArrays(map.get("certificates")),
//                Integer.parseInt(map.get("armorThickness")),
//                map.get("containerMaterial"),
//                findSender(map.get("sender"))
//        );
//    }
//
//    private static StandardContainer Liquids(HashMap<String, String> map) {
//        return new LiquidsContainer(
//                Integer.parseInt(map.get("tare")),
//                Integer.parseInt(map.get("size")),
//                Integer.parseInt(map.get("cargoWeight")),
//                parseArrays(map.get("safetyMeasures")),
//                parseArrays(map.get("certificates")),
//                Integer.parseInt(map.get("maxCapacity")),
//                findSender(map.get("sender"))
//        );
//    }
//
//    private static StandardContainer Chiller(HashMap<String, String> map) {
//        return new ChillerContainer(
//                Integer.parseInt(map.get("tare")),
//                Integer.parseInt(map.get("size")),
//                Integer.parseInt(map.get("cargoWeight")),
//                parseArrays(map.get("safetyMeasures")),
//                parseArrays(map.get("certificates")),
//                Integer.parseInt(map.get("armorThickness")),
//                map.get("containerMaterial"),
//                Integer.parseInt(map.get("maximumWattPowerDraw")),
//                findSender(map.get("sender"))
//        );
//    }
//
//    private static StandardContainer Explosive(HashMap<String, String> map) {
//        return new ExplosivesContainer(
//                Integer.parseInt(map.get("tare")),
//                Integer.parseInt(map.get("size")),
//                Integer.parseInt(map.get("cargoWeight")),
//                parseArrays(map.get("safetyMeasures")),
//                parseArrays(map.get("certificates")),
//                Integer.parseInt(map.get("armorThickness")),
//                map.get("containerMaterial"),
//                Integer.parseInt(map.get("riskLevel")),
//                Integer.parseInt(map.get("maxTemp")),
//                findSender(map.get("sender"))
//        );
//    }
//
//    private static StandardContainer ToxicLiquid(HashMap<String, String> map) {
//        return new ToxicLiquidContainer(
//                Integer.parseInt(map.get("tare")),
//                Integer.parseInt(map.get("size")),
//                Integer.parseInt(map.get("cargoWeight")),
//                parseArrays(map.get("safetyMeasures")),
//                parseArrays(map.get("certificates")),
//                Integer.parseInt(map.get("armorThickness")),
//                map.get("containerMaterial"),
//                Boolean.parseBoolean(map.get("acidSafe")),
//                findSender(map.get("sender"))
//        );
//    }
//
//    private static StandardContainer ToxicLoose(HashMap<String, String> map) {
//        return new ToxicLooseMaterialContainer(
//                Integer.parseInt(map.get("tare")),
//                Integer.parseInt(map.get("size")),
//                Integer.parseInt(map.get("cargoWeight")),
//                parseArrays(map.get("safetyMeasures")),
//                parseArrays(map.get("certificates")),
//                Integer.parseInt(map.get("armorThickness")),
//                map.get("containerMaterial"),
//                Boolean.parseBoolean(map.get("waterproof")),
//                findSender(map.get("sender"))
//        );
//    }
}

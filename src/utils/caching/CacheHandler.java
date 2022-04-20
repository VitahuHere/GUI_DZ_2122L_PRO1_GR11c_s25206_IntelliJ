package utils.caching;

import containers.classes.StandardContainer;
import main.App;
import port.Port;
import port.Train;
import sender.Sender;
import ship.Ship;
import utils.caching.loaders.LoadContainers;
//import utils.caching.loaders.LoadContainers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;

import static utils.Constants.*;

public class CacheHandler {
    public static void saveApp(){
        try{
            Files.createDirectories(Paths.get("cache/ships"));
            Files.createDirectories(Paths.get("cache/containers"));
            Files.createDirectories(Paths.get("cache/trains"));
            Files.createDirectories(Paths.get("cache/senders"));
            Files.createDirectories(Paths.get("cache/warehouse"));
            Files.createFile(Paths.get(APP_SHIPS));
            Files.createFile(Paths.get(APP_CONTAINERS));
            Files.createFile(Paths.get(APP_TRAINS));
            Files.createFile(Paths.get(APP_SENDERS));
            Files.createFile(Paths.get(PORT_SHIPS));
            Files.createFile(Paths.get(PORT_TRAIN));
            Files.createFile(Paths.get(PORT_WAREHOUSE));
        }
        catch (IOException ignore){}
        Saver.saveAppContainers();
        Saver.saveSenders();
        Saver.saveTrains();
        Saver.saveShips();
        Saver.saveWarehouse();
    }

    public static void loadApp() {
        LoadContainers.loadAppContainers();
    }
}

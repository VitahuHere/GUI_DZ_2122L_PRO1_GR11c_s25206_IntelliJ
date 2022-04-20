package utils.caching;

import containers.classes.StandardContainer;
import main.App;
import port.Port;
import utils.caching.loaders.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        LoadSenders.loadSenders();
        LoadContainers.loadAppContainers();
        LoadTrains.loadTrains();
        LoadShips.loadShips();
        LoadWarehouse.loadWarehouse();
        connect();
    }

    private static void connect(){
        connectTrain();
        connectShips();
    }

    private static void connectTrain(){
        Port.train = LoadTrains.portTrain;
        for(int i : LoadTrains.containerIds){
            for(StandardContainer c : LoadContainers.allContainers){
                if(c.id == i){
                    Port.train.addContainer(c);
                    LoadContainers.allContainers.remove(c);
                    break;
                }
            }
        }
    }

    private static void connectShips(){
        for (int i = 0; i < LoadShips.allShips.size(); i++) {
            String containerIds = LoadShips.shipMap.get(i).get("ids");
            for (String j : containerIds.split(",")){
                for(StandardContainer container : LoadContainers.allContainers){
                    if(container.id == Integer.parseInt(j)){
                        LoadShips.allShips.get(i).addContainerOfType(container);
                        LoadContainers.allContainers.remove(container);
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < LoadShips.allShips.size(); i++) {
            if(LoadShips.shipMap.get(i).get("place").equals("port")){
                Port.ships.add(LoadShips.allShips.get(i));
            }
            else{
                App.ships.add(LoadShips.allShips.get(i));
            }
        }
    }
}

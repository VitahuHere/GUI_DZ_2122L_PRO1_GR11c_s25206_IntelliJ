package utils;

import containers.classes.StandardContainer;
import main.App;
import port.Port;
import port.Train;
import sender.Sender;
import ship.Ship;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class CacheHandler {
    private static final String APP_SHIPS = "cache/ships/app_ships.txt";
    private static final String APP_CONTAINERS = "cache/containers/app_containers.txt";
    private static final String APP_TRAINS = "cache/trains/app_trains.txt";
    private static final String APP_SENDERS = "cache/senders/app_senders.txt";

    private static final String PORT_SHIPS = "cache/ships/port_ships.txt";
    private static final String PORT_TRAIN = "cache/trains/port_trains.txt";
    private static final String PORT_WAREHOUSE = "cache/warehouse/port_warehouse.txt";

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
        saveAppContainers();
        saveShips();
        saveWarehouse();
        saveTrains();
        saveSenders();
    }

    private static void saveShips(){
        Collections.sort(App.ships);
        Collections.sort(Port.ships);
        try(FileOutputStream fos = new FileOutputStream(APP_SHIPS)) {
            for(Ship ship : App.ships){
                fos.write((ship.toSaveString() + "\n").getBytes());
            }
        }
        catch (IOException ignore){}

        try(FileOutputStream fos = new FileOutputStream(PORT_SHIPS)) {
            for(Ship ship : Port.ships){
                fos.write((ship.toSaveString() + "\n").getBytes());
            }
        } catch (IOException ignore){}
    }

    private static void saveAppContainers(){
        Collections.sort(App.containers);
        try(FileOutputStream fos = new FileOutputStream(APP_CONTAINERS)){
            for(StandardContainer container : App.containers){
                fos.write((container.toSaveString() + "\n").getBytes());
            }
        }
        catch (IOException ignore){}
    }

    private static void saveTrains(){
        try(FileOutputStream fos = new FileOutputStream(APP_TRAINS)){
            for(Train train : App.trains){
                fos.write((train.toSaveString() + "\n").getBytes());
            }
        }
        catch (IOException ignore){}

        try(FileOutputStream fos = new FileOutputStream(PORT_TRAIN)){
            fos.write((Port.train.toSaveString() + "\n").getBytes());
        } catch (IOException ignore){}
    }

    private static void saveSenders(){
        try(FileOutputStream fos = new FileOutputStream(APP_SENDERS)){
            for(Sender sender : App.senders){
                fos.write((sender.toSaveString() + "\n").getBytes());
            }
        }
        catch (IOException ignore){}
    }

    private static void saveWarehouse(){
        Collections.sort(Port.warehouse.getContainers());
        try(FileOutputStream fos = new FileOutputStream(PORT_WAREHOUSE)){
            for(StandardContainer container : Port.warehouse.getContainers()){
                fos.write((container.toSaveString() + "\n").getBytes());
            }
        }
        catch (IOException ignore){}
    }
}

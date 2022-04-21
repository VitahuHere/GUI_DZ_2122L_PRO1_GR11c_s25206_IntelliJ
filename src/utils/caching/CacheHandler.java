package utils.caching;

import containers.classes.StandardContainer;
import main.App;
import port.Port;
import utils.caching.loaders.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static utils.Constants.*;

public class CacheHandler {
    public static void saveApp() {
        try {
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
            Files.createFile(Paths.get(REMOVED_IDS));
            Files.createFile(Paths.get(CURRENT_TIME));
        } catch (IOException ignore) {
        }
        Saver.saveAppContainers();
        Saver.saveSenders();
        Saver.saveTrains();
        Saver.saveShips();
        Saver.saveWarehouse();
        Saver.saveRemovedIds();
        Saver.saveCurrentTime();
    }

    public static void loadApp() {
        LoadRemovedIds.loadRemovedIds();
        LoadSenders.loadSenders();
        LoadContainers.loadAppContainers();
        LoadTrains.loadTrains();
        LoadShips.loadShips();
        LoadWarehouse.loadWarehouse();
        LoadTime.loadTime();
        connect();
    }

    private static void connect() {
        connectTrain();
        connectShips();
        connectWarehouse();
    }

    private static void connectTrain() {
        Port.train = LoadTrains.portTrain;
        for (int i : LoadTrains.containerIds) {
            for (StandardContainer c : LoadContainers.allContainers) {
                if (c.id == i) {
                    Port.train.addContainer(c);
                    App.containers.remove(c);
                    LoadContainers.allContainers.remove(c);
                    break;
                }
            }
        }
    }

    private static void connectShips() {
        for (int i = 0; i < LoadShips.allShips.size(); i++) {
            String containerIds = LoadShips.shipMap.get(i).get("ids");
            if (containerIds != null) {
                for (String j : containerIds.split(",")) {
                    for (StandardContainer container : LoadContainers.allContainers) {
                        if (container.id == Integer.parseInt(j)) {
                            LoadShips.allShips.get(i).addContainerOfType(container);
                            LoadContainers.allContainers.remove(container);
                            App.containers.remove(container);
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < LoadShips.allShips.size(); i++) {
            if (LoadShips.shipMap.get(i).get("place").equals("port")) {
                Port.ships.add(LoadShips.allShips.get(i));
            } else {
                App.ships.add(LoadShips.allShips.get(i));
            }
        }
    }

    private static void connectWarehouse() {
        Port.warehouse = LoadWarehouse.warehouse;
        for (int i : LoadWarehouse.containerIds) {
            for (StandardContainer container : LoadContainers.allContainers) {
                if (container.id == i) {
                    Port.warehouse.addContainer(container);
                    LoadContainers.allContainers.remove(container);
                    App.containers.remove(container);
                    break;
                }
            }
        }
    }
}

package utils.caching;

import containers.classes.StandardContainer;
import main.App;
import main.TimeOperations;
import port.Port;
import port.Train;
import sender.Sender;
import ship.Ship;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static utils.Constants.*;

public class Saver {
    public static void saveShips(){
        Collections.sort(App.ships);
        Collections.sort(Port.ships);
        try(FileOutputStream fos = new FileOutputStream(APP_SHIPS)) {
            for(Ship ship : App.ships){
                fos.write(("{" + ship.toSaveString() + "\n}\n").getBytes());
            }
        }
        catch (IOException ignore){}

        try(FileOutputStream fos = new FileOutputStream(PORT_SHIPS)) {
            for(Ship ship : Port.ships){
                fos.write(("{" + ship.toSaveString() + "\n}\n").getBytes());
            }
        } catch (IOException ignore){}
    }

    public static void saveAppContainers(){
        Collections.sort(App.containers);
        try(FileOutputStream fos = new FileOutputStream(APP_CONTAINERS)){
            for(StandardContainer container : App.containers){
                fos.write(("{" + container.toSaveString() + "\n}\n").getBytes());
            }
        }
        catch (IOException ignore){}
    }

    public static void saveTrains(){
        try(FileOutputStream fos = new FileOutputStream(APP_TRAINS)){
            for(Train train : App.trains){
                fos.write(("{" + train.toSaveString() + "\n}\n").getBytes());
            }
        }
        catch (IOException ignore){}

        try(FileOutputStream fos = new FileOutputStream(PORT_TRAIN)){
            fos.write(("{" + Port.train.toSaveString() + "\n}\n").getBytes());
        } catch (IOException ignore){}
    }

    public static void saveSenders(){
        try(FileOutputStream fos = new FileOutputStream(APP_SENDERS)){
            for(Sender sender : App.senders){
                fos.write(("{" + sender.toSaveString() + "\n}\n").getBytes());
            }
        }
        catch (IOException ignore){}
    }

    public static void saveWarehouse() {
        try (FileOutputStream fos = new FileOutputStream(PORT_WAREHOUSE)) {
            fos.write(("{" + Port.warehouse.toSaveString() + "\n}\n").getBytes());
        } catch (IOException ignore) {}
    }

    public static void saveRemovedIds(){
        try (FileOutputStream fos = new FileOutputStream(REMOVED_IDS)){
            fos.write(App.removedIds.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException ignore){}
    }

    public static void saveCurrentTime(){
        try(FileOutputStream fos = new FileOutputStream(CURRENT_TIME)){
            fos.write(TimeOperations.currentDate.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException ignore){}
    }
}

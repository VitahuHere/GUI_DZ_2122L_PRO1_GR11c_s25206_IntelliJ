package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public interface Constants {
    int MAX_RISK_VALUE = 5;
    int MIN_RISK_VALUE = 0;
    int MAX_TRAIN_CAPACITY = 10;
    int MAX_WAREHOUSE_CAPACITY = 95;
    String PORT_NAME = "Amsterdam";
    int EXPLOSIVES_MAX_DAYS = 5;
    int TOXIC_LIQUID_MAX_DAYS = 10;
    int TOXIC_LOOSE_MAX_DAYS = 14;
    int TRAIN_DELAY = 30;
    LocalDate startDate = LocalDate.of(1990, 1, 1);
    String INVALID_PESEL = "Sender already exists with this PESEL";
    String LIMIT_CONTAINER_TYPE = "Reached maximum number of containers of this type. Cannot load more";
    String APP_SHIPS = "cache/ships/app_ships.txt";
    String APP_CONTAINERS = "cache/containers/app_containers.txt";
    String APP_TRAINS = "cache/trains/app_trains.txt";
    String APP_SENDERS = "cache/senders/app_senders.txt";
    String PORT_SHIPS = "cache/ships/port_ships.txt";
    String PORT_TRAIN = "cache/trains/port_trains.txt";
    String PORT_WAREHOUSE = "cache/warehouse/port_warehouse.txt";
    String REMOVED_IDS = "cache/containers/removed_ids.txt";
    String CURRENT_TIME = "cache/warehouse/current_time.txt";
    ArrayList<Character> WHITE_SPACE = new ArrayList<>(Arrays.asList('\t', '\n'));
}

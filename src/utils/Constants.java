package utils;

import java.time.LocalDate;

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
    String INVALID_PESEL = "Sender already exists with this PESEL or PESEL is invalid.";
    String LIMIT_CONTAINER_TYPE = "Reached maximum number of containers of this type. Cannot load more";
}

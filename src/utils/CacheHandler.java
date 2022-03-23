package utils;

import java.io.File;
import java.nio.file.Paths;

public class CacheHandler {
    // https://www.baeldung.com/java-current-directory 5th answer
    private final String path = Paths.get(".cache").toAbsolutePath().toString();
    private final String shipPath = Paths.get(path + "ShipCache.txt").toString();
    private final String containerPath = Paths.get(path + "ContainerCache.txt").toString();
    private final String senderPath = Paths.get(path + "senderCache.txt").toString();
    private final String warehousePath = Paths.get(path + "warehouseCache.txt").toString();

    public CacheHandler() {
        // Line copied from https://stackoverflow.com/questions/1554252/how-do-i-create-a-directory-within-the-current-working-directory-in-java Daniel Fortunov
        boolean created = (new File(".cache")).mkdir();
    }

}

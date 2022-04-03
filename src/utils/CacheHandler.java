package utils;

import java.io.File;

public class CacheHandler {
    private static final String CACHE_DIR = "cache";
    private static final String CONTAINERS_CACHE = "containers.txt";
    private static final String SHIPS_CACHE = "ships.txt";

    public CacheHandler() {
        File cacheDir = new File(CACHE_DIR);
        if (!cacheDir.exists()) {
            boolean trash = cacheDir.mkdir();
        }
    }

}

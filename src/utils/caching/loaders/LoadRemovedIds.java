package utils.caching.loaders;

import utils.Constants;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadRemovedIds {
    public static ArrayList<Integer> removedIds = new ArrayList<>();

    public static void loadRemovedIds() {
        try (FileReader reader = new FileReader(Constants.REMOVED_IDS)) {
            int c;
            StringBuilder sb = new StringBuilder();
            while ((c = reader.read()) != -1) {
                if (!Character.isWhitespace(c) && c != '[' && c != ']') {
                    sb.append((char) c);
                }
            }
            if (sb.toString().length() > 0) {
                for (String s : sb.toString().split(",")) {
                    removedIds.add(Integer.parseInt(s));
                }
            }
        } catch (IOException ignore) {
        }
    }
}

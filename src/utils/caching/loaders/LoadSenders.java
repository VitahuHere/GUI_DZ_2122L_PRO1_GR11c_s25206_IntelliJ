package utils.caching.loaders;

import sender.Sender;
import utils.Constants;
import utils.caching.Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadSenders {
    public static ArrayList<Sender> senders = new ArrayList<>();

    public static void loadSenders() {
        ArrayList<HashMap<String, String>> x = Parser.parse(Constants.APP_SENDERS);
        for(HashMap<String, String> sender : x) {
            Sender s = new Sender(sender.get("name"), sender.get("surname"), sender.get("PESEL"), sender.get("address"));
            s.strikes = Integer.parseInt(sender.get("strikes"));
            senders.add(s);
        }
    }
}

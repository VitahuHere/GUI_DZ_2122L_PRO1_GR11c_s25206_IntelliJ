package utils.caching;

import utils.Constants;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private static int indexer = 0;

    private static ArrayList<String> getObjects(FileReader reader) throws IOException {
        StringBuilder object = new StringBuilder();
        ArrayList<String> result = new ArrayList<>();
        int c;
        while ((c = reader.read()) != -1) {
            if (c == '{') {
                while ((c = reader.read()) != '}') {
                    if (!Constants.WHITE_SPACE.contains((char) c)) {
                        object.append((char) c);
                    }
                }
                result.add(object.toString());
                object.setLength(0);
            }
        }
        return result;
    }

    private static HashMap<String, String> getFields(String object) {
        HashMap<String, String> result = new HashMap<>();
        StringBuilder field = new StringBuilder();
        int c;
        for (int i = 0; i < object.length(); i++) {
            c = object.charAt(i);
            if (c == ':') {
                result.put(field.toString(), getValue(object, i));
                field.setLength(0);
                i = indexer;
            } else if (!Character.isWhitespace(c)) {
                field.append((char) c);
            }
        }
        return result;
    }

    private static String getValue(String object, int index) {
        StringBuilder value = new StringBuilder();
        int c;
        for (int i = index + 1; i < object.length(); i++) {
            c = object.charAt(i);
            if (c == ',' || c == ']') {
                indexer = i;
                return value.toString();
            } else if (c == '\'') {
                while ((c = object.charAt(++i)) != '\'') {
                    value.append((char) c);
                }
            } else if (c == '[') {
                value.append('[');
                int depth = 1;
                while ((c = object.charAt(++i)) != ']' || depth != 0) {
                    if (c == '[') {
                        depth++;
                    }
                    if (c == ']') {
                        depth--;
                        if (depth == 0) {
                            break;
                        }
                    }
                    value.append((char) c);
                }
                value.append(']');
            } else if (!Character.isWhitespace(c)) {
                value.append((char) c);
            }
        }
        indexer = object.length() - 1;
        return value.toString();
    }

    public static ArrayList<HashMap<String, String>> containerExtraction(String list) {
        if(list.length() == 2) {
            return null;
        }
        indexer = 0;
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        HashMap<String, String> object = new HashMap<>();
        StringBuilder value = new StringBuilder();
        for (int i = 1; i < list.length()-1; i++) {
            if(list.charAt(i) == ':'){
                object.put(value.toString(), getValue(list, i));
                value.setLength(0);
                i = indexer;
                if(list.charAt(i) == ']'){
                    result.add(object);
                    object = new HashMap<>();
                }
            }
            else if(!Constants.WHITE_SPACE.contains(list.charAt(i)) && list.charAt(i) != '['
                    && list.charAt(i) != ' ' && list.charAt(i) != ','){
                value.append(list.charAt(i));
            }
        }
        return result;
    }

    public static ArrayList<String> getParamList(String list){
        ArrayList<String> result = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        int c, i = 0;
        while((c = list.charAt(++i)) != ']'){
            if(c == ','){
                result.add(value.toString());
                value.setLength(0);
            }
            else {
                value.append((char) c);
            }
        }
        result.add(value.toString());
        return result;
    }

    public static ArrayList<HashMap<String, String>> parse(String path) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        try {
            ArrayList<String> s = getObjects(new FileReader(path));
            for (String o : s) {
                result.add(getFields(o));
            }
        } catch (IOException ignore) {
        }
        return result;
    }
}

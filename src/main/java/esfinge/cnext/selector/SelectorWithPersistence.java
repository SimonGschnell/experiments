package esfinge.cnext.selector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SelectorWithPersistence implements Selector {

    private final Selector selector;
    private HashMap<String, String> previousSelections;

    private String path = "persistence_selections.txt";

    public SelectorWithPersistence() {
        this.selector = new SelectorRandom();
    }

    public SelectorWithPersistence(String path) {
        this.path = path;
        this.selector = new SelectorRandom();
    }

    public SelectorWithPersistence(Selector parentSelector) {
        this.selector = parentSelector;
    }

    public SelectorWithPersistence(String path, Selector parentSelector) {
        this.path = path;
        this.selector = parentSelector;
    }

    private void readData() {
        JSONParser parser = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            StringBuilder sb = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                sb.append("{").append(line).append("},");
                sb.append(ls);
            }
            String tests = "{\"selections\":[" + sb.toString() + "]}";
            Object obj = parser.parse(tests);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("selections");
            Iterator<JSONObject> iterator = array.iterator();
            previousSelections = new HashMap<>();
            while (iterator.hasNext()) {
                JSONObject item = iterator.next();
                String key = (String) item.get("id");
                String value = (String) item.get("selected");
                previousSelections.put(key, value);

            }
        } catch (FileNotFoundException ex) {
            //nothing
        } catch (IOException | ParseException ex) {
            Logger.getLogger(SelectorWithPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Class select(Class[] implementations) {
        Class result = null;
        readData();
        String id = getIp();
        String selected = null;

        if (previousSelections != null) {
            selected = previousSelections.get(id);
            try {
                result = Class.forName(selected);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SelectorWithPersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (selected == null) {
            result = selector.select(implementations);
            selected = result.getName();

            String ls = System.getProperty("line.separator");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true), 8192 * 4)) {
                writer.write("\"id\":\"" + id + "\", \"selected\":\"" + selected + "\"" + ls);
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    private String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return "127.0.0.1"; //default
    }
}

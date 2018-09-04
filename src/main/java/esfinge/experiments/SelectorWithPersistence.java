package esfinge.experiments;

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

    private static final String PERSISTENCE_TESTS_FILE = "persistence_tests.txt";

    private final Selector parentSelector;
    private HashMap<String, String> testsByUser;

    public SelectorWithPersistence() {
        this.parentSelector = new SelectorRandom();
    }

    public SelectorWithPersistence(Selector parentSelector) {
        this.parentSelector = parentSelector;
    }

    private void readData() {
        JSONParser parser = new JSONParser();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PERSISTENCE_TESTS_FILE));
            String line;
            StringBuilder sb = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                sb.append("{").append(line).append("},");
                sb.append(ls);
            }
            String tests = "{\"tests\":[" + sb.toString() + "]}";
            Object obj = parser.parse(tests);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray array = (JSONArray) jsonObject.get("tests");
            Iterator<JSONObject> iterator = array.iterator();
            testsByUser = new HashMap<>();
            while (iterator.hasNext()) {
                JSONObject item = iterator.next();
                String key = (String) item.get("user_id");
                String value = (String) item.get("test_type");
                testsByUser.put(key, value);

            }
        } catch (FileNotFoundException ex) {
            //nothing
        } catch (IOException | ParseException ex) {
            Logger.getLogger(SelectorWithPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String select(String aTest, String bTest) {
        readData();

        String user_id = getIp();
        String testType = null;

        if (testsByUser != null) {
            testType = testsByUser.get(user_id);
        }

        if (testType == null) {
            if (parentSelector.select(aTest, bTest).equals(aTest)) {
                testType = "Atest";
            } else {
                testType = "Btest";
            }
            String ls = System.getProperty("line.separator");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PERSISTENCE_TESTS_FILE, true), 8192 * 4)) {
                writer.write("\"user_id\":\"" + user_id + "\", \"test_type\":\"" + testType + "\"" + ls);
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (testType.equals("Atest")) {
            return aTest;
        } else {
            return bTest;
        }
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

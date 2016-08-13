import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Testing {

    public static void main(final String[] args) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL("http://steamcommunity.com/market/priceoverview/?appid=730&market_hash_name=StatTrak%E2%84%A2%20M4A1-S%20|%20Hyper%20Beast%20(Minimal%20Wear)");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            System.out.println(buffer.toString());
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}
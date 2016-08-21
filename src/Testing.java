import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Testing {

	enum Games {
		DOTA2(570);

		private int value;

		private Games(int value) {
			this.value = value;
		}

		public String getValue() {
			return Integer.toString(value);
		}
	};

	public static void main(final String[] args) throws IOException {
		BufferedReader reader = null;
		try {
			URL url = new URL(
					"http://steamcommunity.com/market/priceoverview/?appid=730&market_hash_name=StatTrak%E2%84%A2%20M4A1-S%20|%20Hyper%20Beast%20(Minimal%20Wear)");
			String setName = "Platinum%20Baby%20Roshan";
			String game = Games.DOTA2.getValue();
			String name = "http://steamcommunity.com/market/priceoverview/?appid="
					+ game + "&market_hash_name=" + setName;
			URL url1 = new URL(name);
			reader = new BufferedReader(new InputStreamReader(url1.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			reader.close();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

}
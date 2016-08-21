import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Testing {
    public static void main(final String[] args) throws IOException {
        BufferedReader reader = null;
        try {
        	Request newRequest = new Request("Platinum%20Baby%20Roshan", "DOTA2");
            URL url = new URL(newRequest.getUrl());
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
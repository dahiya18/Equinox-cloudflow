import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) throws IOException {

        final String function = "TIME_SERIES_INTRADAY";
        final String symbol = "TSLA";
        final String interval = "1min";
        final String api = "A5K060RWV2X3BVP5";

        URL url = new URL("https://www.alphavantage.co/query?function=" + function +
                "&symbol=" + symbol +
                "&interval=" + interval +
                "&apikey=" + api);

        URLConnection urlConn = url.openConnection();
        InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
        BufferedReader buff = new BufferedReader(inStream);

        String line = buff.readLine();
        while(line != null) {
            System.out.println(line);
            line = buff.readLine();
        }
    }
}
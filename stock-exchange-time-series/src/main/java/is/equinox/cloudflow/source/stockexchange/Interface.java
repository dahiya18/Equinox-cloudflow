package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public class Interface {

    static String function = ReadProperties.function;
    static String symbol = ReadProperties.symbol;
    static String interval = ReadProperties.interval;
    static String api = ReadProperties.api;

    static String generateStocks() throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
                "https://www.alphavantage.co/query?function=" + Interface.function +
                        "&symbol=" + Interface.symbol +
                        "&interval=" + Interface.interval +
                        "&apikey=" + Interface.api));

        return request.execute().parseAsString();
    }

    public static void setParams(String function, String symbol, String interval, String api) {
        Interface.function = function;
        Interface.symbol = symbol;
        Interface.interval = interval;
        Interface.api = api;
    }
}


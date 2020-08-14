package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class Interface {
    // https://www.alphavantage.co/documentation/
    public static String function = "GLOBAL_QUOTE";
    public static String symbol = "TSLA";
    public static String api = "A5K060RWV2X3BVP5";

    public static double generateStocks() throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
                "https://www.alphavantage.co/query?function=" + function +
                        "&symbol=" + symbol +
                        "&apikey=" + api));

        String rawResponse = request.execute().parseAsString();
        String getPrice = StringUtils.substringBetween(rawResponse, "price\": \"", "\",");

        if(getPrice == null) {
            System.out.println("Invalid query, please check the input parameters");
            return -1;
        } else {
            return Double.parseDouble(getPrice);
        }
    }

    public static void setParams(String function, String symbol, String api) {
        Interface.function = function;
        Interface.symbol = symbol;
        Interface.api = api;
    }
}

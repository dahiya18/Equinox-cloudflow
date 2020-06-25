package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class Quote {
    public static String rawResponse;

    public static void queryStocks(String function, String symbol, String interval, String api) throws IOException {

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
                "https://www.alphavantage.co/query?function=" + function +
                        "&symbol=" + symbol +
                        "&interval=" + interval +
                        "&apikey=" + api)
        );
        rawResponse = request.execute().parseAsString();
    }

    public static String getRawResponse() {
        return rawResponse;
    }

    public static String getPrice() {
        return StringUtils.substringBetween(rawResponse, "price\": \"", "\",");
    }

    public static String getHighValue() {
        return StringUtils.substringBetween(rawResponse, "high\": \"", "\",");
    }

    public static String getLowValue() {
        return StringUtils.substringBetween(rawResponse, "low\": \"", "\",");
    }
}

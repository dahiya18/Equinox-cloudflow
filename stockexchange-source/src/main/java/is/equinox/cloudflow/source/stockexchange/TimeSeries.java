package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class TimeSeries {
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

    public static String[] getPrice() {
        String tmpRaw = rawResponse;
        int timeSeriesLength = 100;
        String[] pricesOpen = new String[timeSeriesLength];
        for(int i=0; i<=timeSeriesLength-1; i++) {
            tmpRaw = StringUtils.substringAfter(tmpRaw, "},");
            pricesOpen[i] = StringUtils.substringBetween(tmpRaw, "open\": \"", "\",");
        }
        return pricesOpen;
    }
}

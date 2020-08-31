package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class StockController {

    public double generateStocks() throws IOException {

        String func = StockReadProperties.function;
        String sym = StockReadProperties.symbol;
        String apiKey = StockReadProperties.api;

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

        try {
            HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
                    "https://www.alphavantage.co/query?function=" + func +
                            "&symbol=" + sym +
                            "&apikey=" + apiKey));

            String rawResponse = request.execute().parseAsString();
            String getPrice = StringUtils.substringBetween(rawResponse, "price\": \"", "\",");

            return Double.parseDouble(getPrice);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public double generateStocks(String function, String symbol, String api) throws IOException {

        try {
            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
            HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
                    "https://www.alphavantage.co/query?function=" + function +
                            "&symbol=" + symbol +
                            "&apikey=" + api));

            String rawResponse = request.execute().parseAsString();
            String getPrice = StringUtils.substringBetween(rawResponse, "price\": \"", "\",");

            return Double.parseDouble(getPrice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e.toString());
        }
    }
}

package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class StockController {

    DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");

    String generateStocks() throws IOException {

        ReadProperties prop = new ReadProperties();
        prop.getPropValues();

        String function = ReadProperties.function;
        String symbol = ReadProperties.symbol;
        String api = ReadProperties.api;

        String stockURL = "https://financialmodelingprep.com/api/v3/" +
                function +
                symbol +
                "apikey=" + api;

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(stockURL));
        return request.execute().parseAsString();
    }

    String generateStocks(String function, String symbol, String api) throws IOException {

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        String stockURL = "https://financialmodelingprep.com/api/v3/" +
                function +
                symbol +
                "apikey=" + api;

        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(stockURL));
        return request.execute().parseAsString();
    }

    public String[] parse(String responseBody) throws JSONException {
        df.setTimeZone(TimeZone.getTimeZone("IST"));
        if(!responseBody.startsWith("[")) {
            responseBody = responseBody.substring(responseBody.indexOf("["), responseBody.indexOf("]") + 1);
        }
        JSONArray stocks = new JSONArray(responseBody);
        String[] stockArr = new String[stocks.length()];
        if(stocks.length()==0) {
            return null;
        } else {
            for (int i = 0; i < stocks.length(); i++) {
                JSONObject stock = stocks.getJSONObject(i);
                String date = stock.getString("date");
                double close = stock.getDouble("close");

                if(date.contains(":")) {
                    Timestamp time = Timestamp.valueOf(date);
                    stockArr[i] = df.format(time) + ", " + close;
                } else {
                    Date time = Date.valueOf(date);
                    stockArr[i] = df.format(time) + ", " + close;
                }
            }
            return stockArr;
        }
    }
}

package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class SourceApplication {

    // https://www.alphavantage.co/documentation/
    public static String function = "GLOBAL_QUOTE";
    public static String symbol = "TSLA";
    public static String interval = null;
    public static String api = "A5K060RWV2X3BVP5";

    public static double getOutput() throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
                "https://www.alphavantage.co/query?function=" + function +
                        "&symbol=" + symbol +
                        "&interval=" + interval +
                        "&apikey=" + api)
        );
        String rawResponse = request.execute().parseAsString();

        String getPrice = StringUtils.substringBetween(rawResponse, "price\": \"", "\",");

        double price = Double.parseDouble(getPrice);

        System.out.println("Application value = " + price);

        return price;
    }
}

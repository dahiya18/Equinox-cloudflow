package is.equinox.cloudflow.source.stockexchange;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class Application {

    public static void queryStocks() throws IOException {
        final String function = "GLOBAL_QUOTE";
        final String symbol = "TSLA";
        final String interval = null;
        final String api = "A5K060RWV2X3BVP5";

        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(
                "https://www.alphavantage.co/query?function=" + function +
                        "&symbol=" + symbol +
                        "&interval=" + interval +
                        "&apikey=" + api)
        );
        String rawResponse = request.execute().parseAsString();
        System.out.println(rawResponse);
    }
}

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
//@EnableBinding(Source.class)
public class Application {

//    public static void main(String[] args){
//        SpringApplication.run(Application.class, args);
//    }

    public static void main(String[] args) throws IOException {
        final String function = "TIME_SERIES_INTRADAY";
        final String symbol = "TSLA";
        final String interval = "1min";
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

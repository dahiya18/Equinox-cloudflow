package is.equinox.cloudflow.source.stockexchange;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class Application {

    public static String function = "TIME_SERIES_INTRADAY";
    public static String symbol = "TSLA";
    public static String interval = "60min";
    public static String api = "A5K060RWV2X3BVP5";

    @Bean
    public static String getOutput() throws IOException {

        if(function.equals("GLOBAL_QUOTE")) {
            Quote.queryStocks(function, symbol, interval, api);
            System.out.println(Quote.getPrice());
            System.out.println(Quote.getRawResponse());
            return Quote.getPrice();
        }
        else if(function.equals("TIME_SERIES_INTRADAY") || function.equals("TIME_SERIES_DAILY")) {
            TimeSeries.queryStocks(function, symbol, interval, api);
            System.out.println(TimeSeries.getRawResponse());
            return TimeSeries.getRawResponse();
        }
        else {
            System.out.println("Incorrect function.");
            return null;
        }
    }

    public static void setParams(String function, String symbol, String interval, String api) {
        is.equinox.cloudflow.source.stockexchange.Application.function = function;
        is.equinox.cloudflow.source.stockexchange.Application.symbol = symbol;
        is.equinox.cloudflow.source.stockexchange.Application.interval = interval;
        is.equinox.cloudflow.source.stockexchange.Application.api = api;
    }
}

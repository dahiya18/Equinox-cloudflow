package is.equinox.cloudflow.source.stockexchange;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class Application {

    // https://www.alphavantage.co/documentation/
    public static String function = "GLOBAL_QUOTE";
    public static String symbol = "TSLA";
    public static String interval = null;
    private static String api = "A5K060RWV2X3BVP5";

//    @Bean
//    @InboundChannelAdapter(
//        value= Source.OUTPUT,
//        poller=@Poller(fixedDelay="10000",maxMessagesPerPoll="1")
//    )
//    public MessageSource<String[]> passOutput() {
//        return () -> {
//            try {
//                return MessageBuilder.withPayload(Objects.requireNonNull(getOutput())).build();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        };
//    }

//    @Bean
//    public static String[] getOutput() throws IOException {
//        if(function.equals("TIME_SERIES_INTRADAY") ||
//                function.equals("TIME_SERIES_DAILY") ||
//                function.equals("TIME_SERIES_DAILY_ADJUSTED") ||
//                function.equals("TIME_SERIES_WEEKLY") ||
//                function.equals("TIME_SERIES_WEEKLY_ADJUSTED") ||
//                function.equals("TIME_SERIES_MONTHLY") ||
//                function.equals("TIME_SERIES_MONTHLY_ADJUSTED")) {
//            return getSeries();
//        }
//        else if (function.equals("GLOBAL_QUOTE")) {
//            String[] price = new String[1];
//            price[0] = getQuote();
//            return price;
//        }
//        else {
//            System.out.println("Incorrect function.");
//            return null;
//        }
//    }

//    public static String getQuote() throws IOException {
//        function = "GLOBAL_QUOTE";
//        Quote.queryStocks(function, symbol, interval, api);
//        System.out.println(Quote.getPrice());
//        return Quote.getPrice();
//    }

//    @Bean
    public static double getQuote() throws IOException {
        function = "GLOBAL_QUOTE";
        Quote.queryStocks(function, symbol, interval, api);

        String getPrice = Quote.getPrice();
        System.out.println(Quote.getPrice());

        double price = Double.parseDouble(getPrice);
        System.out.println("Application value = " + price);

        return price;
    }

    public static String[] getSeries() throws IOException {
        TimeSeries.queryStocks(function, symbol, interval, api);
        System.out.println(Arrays.toString(TimeSeries.getPrice()));
        return TimeSeries.getPrice();
    }

    public static void setParams(String function, String symbol, String interval, String api) {
        Application.function = function;
        Application.symbol = symbol;
        Application.interval = interval;
        Application.api = api;
    }

    public static String getFunction() {
        return function;
    }

    public static String getSymbol() {
        return symbol;
    }

    public static String getInterval() {
        return interval;
    }

    public static String getApi() {
        return api;
    }
}

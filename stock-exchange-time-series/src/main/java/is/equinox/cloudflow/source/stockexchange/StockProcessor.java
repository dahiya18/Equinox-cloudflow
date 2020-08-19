package is.equinox.cloudflow.source.stockexchange;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class StockProcessor {

    Logger logger = LoggerFactory.getLogger(StockProcessor.class);

    public static String[] formatStocks() throws IOException {
        int timeSeriesLength = 100;
        String[] pricesOpen = new String[timeSeriesLength];
        String tmpRaw = Interface.generateStocks();

        try {
            for (int i = 0; i <= timeSeriesLength - 1; i++) {
                tmpRaw = StringUtils.substringAfter(tmpRaw, "},");
                double stockValue = Double.parseDouble(StringUtils.substringBetween(tmpRaw, "open\": \"", "\","));
                Timestamp stockTime = Timestamp.valueOf("2" + StringUtils.substringBetween(tmpRaw, "\"2", "\":"));

                pricesOpen[i] = stockTime + ", " + stockValue;
            }

            return pricesOpen;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] formatStocks(String function, String symbol, String interval, String api) throws IOException {
        int timeSeriesLength = 100;
        String[] pricesOpen = new String[timeSeriesLength];
        String tmpRaw = Interface.generateStocks(function, symbol, interval, api);

        try {
            for (int i = 0; i <= timeSeriesLength - 1; i++) {
                tmpRaw = StringUtils.substringAfter(tmpRaw, "},");
                double stockValue = Double.parseDouble(StringUtils.substringBetween(tmpRaw, "open\": \"", "\","));
                Timestamp stockTime = Timestamp.valueOf("2" + StringUtils.substringBetween(tmpRaw, "\"2", "\":"));

                pricesOpen[i] = stockTime + ", " + stockValue;
            }

            return pricesOpen;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void write (String[]x) throws IOException{
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("Output"));
        for (int i = 0; i < x.length; i++) {
            outputWriter.write(x[i]);
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}



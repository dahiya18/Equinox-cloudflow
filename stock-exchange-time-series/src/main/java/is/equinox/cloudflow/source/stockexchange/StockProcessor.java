package is.equinox.cloudflow.source.stockexchange;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class StockProcessor {

    Logger logger = LoggerFactory.getLogger(StockProcessor.class);

    public static Double[] formatStocks() throws IOException {
        int timeSeriesLength = 100;
        String[] pricesOpen = new String[timeSeriesLength];
        String tmpRaw = Interface.generateStocks();
        for(int i=0; i<=timeSeriesLength-1; i++) {
            tmpRaw = StringUtils.substringAfter(tmpRaw, "},");
            pricesOpen[i] = StringUtils.substringBetween(tmpRaw, "open\": \"", "\",");
        }

        if(pricesOpen[0] == null) {
            System.out.println("\nInvalid query, please check the input parameters\n");
            return null;
        } else {
            return Arrays.stream(pricesOpen)
                    .map(Double::valueOf)
                    .toArray(Double[]::new);
        }
    }

    public static void write (Double[]x) throws IOException{
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("Output"));
        for (int i = 0; i < x.length; i++) {
            // Maybe:
            outputWriter.write(x[i]+"");
            // Or:
            outputWriter.write(Double.toString(x[i]));
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}



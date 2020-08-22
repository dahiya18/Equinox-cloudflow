package is.equinox.cloudflow.source.stockexchange;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StockProcessor {

    Logger logger = LoggerFactory.getLogger(StockProcessor.class);
    DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");

    String[] formatStocks() throws IOException  {
        StockController controller = new StockController();
        String tmpRaw = controller.generateStocks();

        try {
            return controller.parse(tmpRaw);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    String[] formatStocks(String function, String symbol, String interval, String api) throws IOException {
        StockController controller = new StockController();
        String tmpRaw = controller.generateStocks(function, symbol, interval, api);
        try {
            return controller.parse(tmpRaw);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    void write (String[]x) throws IOException{
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter("data.csv"));
        for (String s : x) {
            outputWriter.write(s);
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();
    }
}



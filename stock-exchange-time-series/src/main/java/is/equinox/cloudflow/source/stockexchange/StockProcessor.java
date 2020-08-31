package is.equinox.cloudflow.source.stockexchange;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StockProcessor {

    Logger logger = LoggerFactory.getLogger(StockProcessor.class);

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

    String[] formatStocks(String function, String symbol, String api) throws IOException {
        StockController controller = new StockController();
        String tmpRaw = controller.generateStocks(function, symbol, api);

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



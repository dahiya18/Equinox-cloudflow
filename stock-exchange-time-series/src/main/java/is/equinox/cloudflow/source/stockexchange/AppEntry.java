package is.equinox.cloudflow.source.stockexchange;

import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan(basePackages = {"is.equinox"})
public class AppEntry {

    public static void main(String[] args) throws Exception {
        ReadProperties.getPropValues();

        Double[] output = StockProcessor.formatStocks();

        System.out.println(Arrays.toString(output));
    }
}

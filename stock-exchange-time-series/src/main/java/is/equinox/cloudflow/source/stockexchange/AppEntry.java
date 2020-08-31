package is.equinox.cloudflow.source.stockexchange;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"is.equinox"})
public class AppEntry {

    public static void main(String[] args) throws Exception {
        StockProcessor processor = new StockProcessor();

        String[] output = processor.formatStocks();

        processor.write(output);
    }
}

package is.equinox.cloudflow.source.stockexchange;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    static String function;
    static String symbol;
    static String api;

    public static void getPropValues() throws IOException {
        String propFileName = "config.properties";

        try (InputStream input = ReadProperties.class.getClassLoader().getResourceAsStream(propFileName)) {
            Properties prop = new Properties();

            prop.load(input);

            Interface.function = prop.getProperty("function");
            Interface.symbol = prop.getProperty("symbol");
            Interface.api = prop.getProperty("api");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package is.equinox.cloudflow.source.reddit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    public Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(IOException fnfe) {
            fnfe.printStackTrace();
        } finally {
            assert fis != null;
            fis.close();
        }
        return prop;
    }
}

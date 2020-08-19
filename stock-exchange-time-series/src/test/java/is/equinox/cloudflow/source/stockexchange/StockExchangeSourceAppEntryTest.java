package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StockExchangeSourceAppEntryTest {

    @Test
    public void badParameter_Test() throws IOException {
        Interface.setParams("TIME_SERIES_DAILY","TSLAr", "null", "A5K060RWV2X3BVP5");
        assertNull(StockProcessor.formatStocks());
    }

    @Test
    public void goodParameter_Test() throws IOException {
        Interface.setParams("TIME_SERIES_DAILY","TSLA", "null","A5K060RWV2X3BVP5");
        assertNotNull(StockProcessor.formatStocks());
    }

    @Test
    public void goodParameter_readTest() throws IOException {
        ReadProperties.getPropValues();
        assertNotEquals(-1, Interface.generateStocks());
    }
}


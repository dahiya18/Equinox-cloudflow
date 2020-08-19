package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StockExchangeSourceAppEntryTest {

    @Test
    public void badParameter_Test() throws IOException {
        assertNull(StockProcessor.formatStocks("TIME_SERIES_INTRADAY","TSLAr", "1min", "A5K060RWV2X3BVP5"));
    }

    @Test
    public void goodParameter_Test() throws IOException {
        assertNotNull(StockProcessor.formatStocks("TIME_SERIES_INTRADAY","TSLA", "1min","A5K060RWV2X3BVP5"));
    }

    @Test
    public void goodParameter_readTest() throws IOException {
        ReadProperties.getPropValues();
        assertNotEquals(-1, Interface.generateStocks());
    }
}


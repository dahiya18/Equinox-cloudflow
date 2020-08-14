package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StockExchangeSourceAppEntryTest {

    @Test
    public void badParameter_quoteTest() throws IOException {
        Interface.setParams("TIME_SERIES_DAILY","TSLAr","A5K060RWV2X3BVP5");
        assertNull(StockProcessor.formatStocks());
    }

    @Test
    public void goodParameter_quoteTest() throws IOException {
        Interface.setParams("TIME_SERIES_DAILY","TSLA","A5K060RWV2X3BVP5");
        assertNotNull(StockProcessor.formatStocks());
    }
}


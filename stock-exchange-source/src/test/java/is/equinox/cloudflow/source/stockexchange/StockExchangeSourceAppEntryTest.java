package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StockExchangeSourceAppEntryTest {

    @Test
    public void badParameter_quoteTest() throws IOException {
        Interface.setParams("GLOBAL_QUOTE","TSLAr","A5K060RWV2X3BVP5");
        assertEquals(-1, Interface.generateStocks());
    }

    @Test
    public void goodParameter_quoteTest() throws IOException {
        Interface.setParams("GLOBAL_QUOTE","TSLA","A5K060RWV2X3BVP5");
        assertNotEquals(-1, Interface.generateStocks());
    }
}


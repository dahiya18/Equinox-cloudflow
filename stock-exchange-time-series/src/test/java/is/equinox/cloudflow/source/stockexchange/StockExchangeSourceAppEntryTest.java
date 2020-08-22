package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StockExchangeSourceAppEntryTest {

    StockProcessor processor = new StockProcessor();

    @Test
    void badParameter_Test() throws IOException {
        assertNull(processor.formatStocks("historical-chart","TSLAr", "1min", "d516597aa6499ad22b6c24d8ae06686d"));
    }

    @Test
    void goodParameter_Test() throws IOException {
        assertNotNull(processor.formatStocks("historical-chart","TSLA", "1min","d516597aa6499ad22b6c24d8ae06686d"));
    }

    @Test
    void goodParameter_readTest() throws IOException {
        assertNotNull(processor.formatStocks());
    }
}


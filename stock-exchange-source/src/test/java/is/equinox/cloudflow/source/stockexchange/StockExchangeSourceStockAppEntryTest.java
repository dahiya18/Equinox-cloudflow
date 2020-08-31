package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockExchangeSourceStockAppEntryTest {

    private final StockController avStockController = new StockController();

    @Test
    public void badParameter_Test() {
        Exception exception = assertThrows(IOException.class, () -> {
            avStockController.generateStocks("GLOBAL_QUOTE","TSLAque","A5K060RWV2X3BVP5");
        });

        String expectedMessage = "NullPointerException";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void goodParameter_Test() throws IOException {
        assertFalse( Double.isNaN(avStockController.generateStocks("GLOBAL_QUOTE","TSLA","A5K060RWV2X3BVP5")));
    }

    @Test
    public void goodParameter_readTest() throws IOException {
        StockReadProperties.getPropValues();
        assertFalse(Double.isNaN(avStockController.generateStocks()));
    }
}


package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockExchangeSourceAppEntryTest {

    private final Interface avInterface = new Interface();

    @Test
    public void badParameter_Test() {
        Exception exception = assertThrows(IOException.class, () -> {
            avInterface.generateStocks("GLOBAL_QUOTE","TSLAque","A5K060RWV2X3BVP5");
        });

        String expectedMessage = "NullPointerException";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void goodParameter_Test() throws IOException {
        assertNotEquals(-1, avInterface.generateStocks("GLOBAL_QUOTE","TSLA","A5K060RWV2X3BVP5"));
    }

    @Test
    public void goodParameter_readTest() throws IOException {
        ReadProperties.getPropValues();
        assertNotEquals(-1, avInterface.generateStocks());
    }
}


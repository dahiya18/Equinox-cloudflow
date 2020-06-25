package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class StockExchangeSourceAppEntryTest {

	@Test
	public void badParameter_test() throws IOException, InterruptedException {
		Application.setParams("GLOBAL_QUOTE","TSLAr", null,"A5K060RWV2X3BVP5");
		Application.getOutput();
		assertNull(Application.getOutput());
	}

	@Test
	public void goodLink_test() throws IOException, InterruptedException {
		Application.setParams("GLOBAL_QUOTE","TSLA", null,"A5K060RWV2X3BVP5");
		Application.getOutput();
		assertNotNull(Application.getOutput());
	}

}


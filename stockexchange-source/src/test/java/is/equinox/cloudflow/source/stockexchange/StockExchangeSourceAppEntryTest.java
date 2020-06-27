package is.equinox.cloudflow.source.stockexchange;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class StockExchangeSourceAppEntryTest {

	@Test
	public void badParameter_quoteTest() throws IOException, InterruptedException {
		Application.setParams("GLOBAL_QUOTE","TSLAr",null,"A5K060RWV2X3BVP5");
		assertNull(Application.getQuote());
	}

	@Test
	public void goodLink_quoteTest() throws IOException, InterruptedException {
		Application.setParams("GLOBAL_QUOTE","TSLA",null,"A5K060RWV2X3BVP5");
		assertNotNull(Application.getQuote());
	}

	@Test
	public void badParameter_seriesTest() throws IOException, InterruptedException {
		Application.setParams("TIME_SERIES_INTRADAY","TSLAr","1min","A5K060RWV2X3BVP5");
		assertNull(Application.getSeries()[0]);
	}

	@Test
	public void goodLink_seriesTest() throws IOException, InterruptedException {
		Application.setParams("TIME_SERIES_INTRADAY", "TSLA","1min", "A5K060RWV2X3BVP5");
		assertNotNull(Application.getSeries());
	}

	@Test
	public void goodLink_seriesDailyTest() throws IOException, InterruptedException {
		Application.setParams("TIME_SERIES_DAILY", "TSLA",null, "A5K060RWV2X3BVP5");
		assertNotNull(Application.getSeries());
	}
}


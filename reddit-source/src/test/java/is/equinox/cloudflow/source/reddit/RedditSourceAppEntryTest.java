package is.equinox.cloudflow.source.reddit;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class RedditSourceAppEntryTest {

	@Test
    public void test() {
        RedditStream output = new RedditStream();
        String data = output.queryReddit("tesla", 1, "modelX");
	    assertNotNull(data);
    }



}

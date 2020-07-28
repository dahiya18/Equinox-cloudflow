package is.equinox.cloudflow.source.twitter;

import org.junit.jupiter.api.Test;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwitterSourceAppEntryTest {

    /**
     * Exception test for wrong authorisation keys
     */
    @Test
    public void auth_test(){
        TwitterClass tc = new TwitterClass();
        tc.setAuth("abc", "abc", "abc", "abc");
        Twitter tw = tc.makeConnection();
	    assertThrows(TwitterException.class, () -> tc.searchQuery(tw,"tesla"));
    }





}

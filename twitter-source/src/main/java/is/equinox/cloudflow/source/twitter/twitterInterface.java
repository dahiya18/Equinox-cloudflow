package is.equinox.cloudflow.source.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

public interface twitterInterface {

    /**
     * Establishes a connection using the twitter4j library
      * @return Twitter object to make future queries
     */
   public Twitter makeConnection();

    /**
     * Resets the API keys for making the twitter connection
     * @param conKey is the consumer key
     * @param conSecret is the consumer key secret
     * @param token is the access token
     * @param tokenSecret is the access token secret
     */
   public void setAuth(String conKey, String conSecret, String token, String tokenSecret);

    /**
     *
     * @return
     */
   public List<String> getResultTweets();
   public List<String> searchQuery(Twitter twitter, String queryWord) throws TwitterException;
   public List<String> getMyTimeline(Twitter twitter) throws TwitterException;


}

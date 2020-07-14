package is.equinox.cloudflow.source.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;


public class TwitterApp {


    public static List<String> getData() {

        System.out.println(".\n.\nProgram starting:\n.\n.\n...........................\n");
        TwitterClass twitterObject = new TwitterClass();
        Twitter twitter = twitterObject.makeConnection();
        List<String> tweets = null;
        try {
            tweets = twitterObject.searchQuery(twitter, "tesla");

        } catch(TwitterException te){
            System.out.println("\nException found\n");
        }
        return tweets;

    }

}

package is.equinox.cloudflow.source.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

public class twitterApp {


    public static List<String> main() {

        System.out.println(".\n.\nProgram starting:\n.\n.\n...........................\n");
        twitterClass twitterObject = new twitterClass();
        Twitter twitter = twitterObject.makeConnection();
        List<String> tweets = null;
        try {
            tweets = twitterObject.searchQuery(twitter, "tesla");
            for(String tweet:tweets){
                System.out.println("\n"+tweet+"\n");
            }
        } catch(TwitterException te){
            System.out.println("\nException found\n");
        }
        return tweets;

    }

}

package is.equinox.cloudflow.source.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class twitter4j {
    public static void main(String args[]) {

        System.out.println(".\n.\nProgram starting:\n.\n.\n...........................\n");

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("dZFcwxXoeG2WqoWV40hTAGoS0")
                .setOAuthConsumerSecret("ty871l82PhEMbh2Eynh05CRz3ntwaZuf26VlTvbvzWBGWrFR37")
                .setOAuthAccessToken("796027954560192512-IoFgrZBHXckAQUPkC6OsUqb3v8gNprJ")
                .setOAuthAccessTokenSecret("BMhqBewo2h5qw2MuQdY4nSZ7oa82VO8yCPgEYsVFuZ47K");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        try {
            List<String> timeline = twitter.getHomeTimeline().stream()
                    .map(item -> item.getText())
                    .collect(Collectors.toList());
        }
        catch(TwitterException te){
            System.out.println("\nNothing on timeline");
        }

        Query query = new Query("tesla");
        query.setSince("2020-01-26");
        query.setCount(10);
        try {
            QueryResult result = twitter.search(query);

            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {
                System.out.println("\t\nUser : @" + tweet.getUser().getScreenName());
                System.out.println("\t\nAt: " + tweet.getCreatedAt());
                System.out.println("\t\nReTweetCount: " + tweet.getRetweetCount());
                System.out.println("\t\nText: " + tweet.getText());
                System.out.println("\t\nLikes: " + tweet.getFavoriteCount());
                System.out.println("\n.........................................\n");
            }


        }
        catch(TwitterException te){
            System.out.println("Nothing found\n");
        }
    }
}

package is.equinox.cloudflow.source.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class twitterApp {
    public static void main() {

        System.out.println(".\n.\nProgram starting:\n.\n.\n...........................\n");

        String consumerKey = "dZFcwxXoeG2WqoWV40hTAGoS0";
        String consumerSecret = "ty871l82PhEMbh2Eynh05CRz3ntwaZuf26VlTvbvzWBGWrFR37";
        String accessToken = "796027954560192512-IoFgrZBHXckAQUPkC6OsUqb3v8gNprJ";
        String accessTokenSecret = "BMhqBewo2h5qw2MuQdY4nSZ7oa82VO8yCPgEYsVFuZ47K";

        Twitter twitter = makeConnection(consumerKey, consumerSecret, accessToken, accessTokenSecret);

        try {
            List<String> tweets = searchQuery(twitter, "tesla");
            for(String tweet:tweets){
                System.out.println("\n"+tweet+"\n");
            }
        } catch(TwitterException te){
            System.out.println("\nException found\n");
        }

    }

    public static List<String> searchQuery(Twitter twitter, String searchQuery) throws TwitterException{
        Query query = new Query(searchQuery);
        query.setSince("2020-01-26");
        query.setCount(3);
        QueryResult result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        return tweets.stream().map(item -> TwitterObjectFactory.getRawJSON(item))
                              .collect(Collectors.toList());

    }
    public static List<String> getMyTimeline(Twitter twitter) throws TwitterException {
        return twitter.getHomeTimeline().stream()
                      .map(item -> item.getText())
                      .collect(Collectors.toList());
    }
    public static Twitter makeConnection(String consumerKey, String consumerSecret, String accessToken, String tokenSecret){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setJSONStoreEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(tokenSecret);

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}

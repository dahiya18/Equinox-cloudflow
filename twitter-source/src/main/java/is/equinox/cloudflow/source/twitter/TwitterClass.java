package is.equinox.cloudflow.source.twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class TwitterClass implements TwitterInterface {


    private String consumerKey = "dZFcwxXoeG2WqoWV40hTAGoS0";
    private String consumerSecret = "ty871l82PhEMbh2Eynh05CRz3ntwaZuf26VlTvbvzWBGWrFR37";
    private String accessToken = "796027954560192512-IoFgrZBHXckAQUPkC6OsUqb3v8gNprJ";
    private String accessTokenSecret = "BMhqBewo2h5qw2MuQdY4nSZ7oa82VO8yCPgEYsVFuZ47K";

    public List<String> searchQuery(Twitter twitter, String searchQuery) throws TwitterException{
        Query query = new Query(searchQuery);
        query.setSince("2020-01-26");
        query.setCount(1);
        QueryResult result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        return tweets.stream().map(item -> TwitterObjectFactory.getRawJSON(item))
                .collect(Collectors.toList());

    }

    public Twitter makeConnection(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setJSONStoreEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    public void setAuth(String conKey, String conSecret, String token, String tokenSecret){
        consumerKey = conKey;
        consumerSecret = conSecret;
        accessToken = token;
        accessTokenSecret = tokenSecret;
    }





}

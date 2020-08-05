package is.equinox.cloudflow.source.twitter;


import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterClass {


    private static String consumerKey = "dZFcwxXoeG2WqoWV40hTAGoS0";
    private static String consumerSecret = "ty871l82PhEMbh2Eynh05CRz3ntwaZuf26VlTvbvzWBGWrFR37";
    private static String accessToken = "796027954560192512-IoFgrZBHXckAQUPkC6OsUqb3v8gNprJ";
    private static String accessTokenSecret = "BMhqBewo2h5qw2MuQdY4nSZ7oa82VO8yCPgEYsVFuZ47K";

    public static Twitter makeConnection(){
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

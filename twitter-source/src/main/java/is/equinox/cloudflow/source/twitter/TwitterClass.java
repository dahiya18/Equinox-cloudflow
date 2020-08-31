package is.equinox.cloudflow.source.twitter;


import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TwitterClass {


    private String consumerKey = "dZFcwxXoeG2WqoWV40hTAGoS0";
    private String consumerSecret = "ty871l82PhEMbh2Eynh05CRz3ntwaZuf26VlTvbvzWBGWrFR37";
    private String accessToken = "796027954560192512-IoFgrZBHXckAQUPkC6OsUqb3v8gNprJ";
    private String accessTokenSecret = "BMhqBewo2h5qw2MuQdY4nSZ7oa82VO8yCPgEYsVFuZ47K";

    public Twitter makeConnection(){
        properties();
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
    public void properties(){
        try (InputStream input = new FileInputStream("config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.setAuth(prop.getProperty(consumerKey), prop.getProperty(consumerSecret), prop.getProperty(accessToken), prop.getProperty(accessTokenSecret));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

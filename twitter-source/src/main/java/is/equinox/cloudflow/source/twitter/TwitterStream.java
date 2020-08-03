package is.equinox.cloudflow.source.twitter;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import twitter4j.*;

import javax.validation.constraints.Null;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TwitterStream {


    public static String getTweets(Twitter twitter, String Topic) {
        Logger logger = LoggerFactory.getLogger(TwitterStream.class);
        long startTime = System.currentTimeMillis();
        AtomicInteger commentCount = new AtomicInteger();
        String sentimentResult;
        StringBuilder Postscore = new StringBuilder();
        LocalDate Today = LocalDate.now();
        int n = 2;
        try{
            Query query = new Query(Topic);
            //query.setSince("2020-08-01");
            //query.setUntil("2020-08-03");
            query.setCount(n);
            QueryResult result;
            do{
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets)
                {
                    String txt = tweet.getText().trim()
                            .replaceAll("http.*?[\\S]+", "")
                            .replaceAll("@[\\S]+", "")
                            .replaceAll("#", "")
                            .replaceAll("[\\s]+", " ");
                    sentimentResult = new SentimentScore(tweet.getCreatedAt(),tweet.getId(), TwitterSentiment.analyseTwitter(txt),tweet.getRetweetCount()).toString();
                    commentCount.incrementAndGet();
                    if (sentimentResult != null){
                        Postscore.append(sentimentResult);
                    }
                }
            }while ((query = result.nextQuery()) != null);
        }catch (TwitterException ex)
        {
            ex.printStackTrace();
        }
        Duration processingTime = Duration.ofMillis(System.currentTimeMillis() - startTime);
        long minutes = processingTime.toMinutes();
        long seconds = processingTime.minusMinutes(minutes).getSeconds();
        logger.info("Analyzed {} comments in {} minutes and {} seconds", commentCount.get(), minutes, seconds);
        return Postscore.toString();
    }



}

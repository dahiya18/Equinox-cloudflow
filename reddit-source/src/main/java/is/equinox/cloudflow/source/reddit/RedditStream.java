package is.equinox.cloudflow.source.reddit;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.*;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.SearchPaginator;
import net.dean.jraw.references.SubmissionReference;
import net.dean.jraw.tree.CommentNode;
import com.google.common.collect.Streams;


import java.time.Duration;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RedditStream {
    static String userName = "gaganeq";
    static String passWord = "@1234@abcd@";
    static String apiKey = "lnrJRkv6cq-uzA";
    static String secret = "IY8Za1Cqj89kP7t18Yd-UNlBA-Q";

    public static UserAgent userAgent = new UserAgent("desktop", "name.Java-API", "v0.1", userName);
    public static Credentials credentials = Credentials.script(userName, passWord, apiKey, secret);
    public static NetworkAdapter networkAdapter = new OkHttpNetworkAdapter(userAgent);
    public static RedditClient redditClient = OAuthHelper.automatic(networkAdapter, credentials);


    public String queryReddit(String subR,int n,String searching) {
        //String subR = "StockMarket";
        //int n = 1;
        //String searching = "[Watchlist]";
        Logger logger = LoggerFactory.getLogger(RedditStream.class);

        StringBuilder Postscore = new StringBuilder();

        SearchPaginator search = redditClient.subreddit(subR)
                .search()
                .limit(n)
                .sorting(SearchSort.NEW)
                .query(searching)
                .timePeriod(TimePeriod.DAY)
                .build();

        RedditSentimentAnalyser analyzer = new RedditSentimentAnalyser();
        long startTime = System.currentTimeMillis();
        AtomicInteger commentCount = new AtomicInteger();
        search.forEach(listing -> listing.getChildren().parallelStream()
                .forEach(submission -> {
                    SubmissionReference ref = submission.toReference(redditClient);
                    Iterator<CommentNode<PublicContribution<?>>> commentIter = ref.comments().walkTree().iterator();
                    Stream<CommentNode<PublicContribution<?>>> comments = Streams.stream(commentIter);
                    Stream<SentimentScore> scores = analyzer.analyze(comments);

                    scores.forEach(score -> {
                        if (score != null) {
                            commentCount.incrementAndGet();
                            Postscore.append(score);
                        }
                    });
                }));

        Duration processingTime = Duration.ofMillis(System.currentTimeMillis() - startTime);
        long minutes = processingTime.toMinutes();
        long seconds = processingTime.minusMinutes(minutes).getSeconds();
        logger.info("Analyzed {} comments in {} minutes and {} seconds", commentCount.get(), minutes, seconds);
        return Postscore.toString();
    }
}






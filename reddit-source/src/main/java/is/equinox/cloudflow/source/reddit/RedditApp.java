package is.equinox.cloudflow.source.reddit;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.SubredditSort;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dean.jraw.references.SubredditReference;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})

public class RedditApp {
    static String userName = "gaganeq";
    static String passWord = "@1234@abcd@";
    static String apiKey = "lnrJRkv6cq-uzA";
    static String secret = "IY8Za1Cqj89kP7t18Yd-UNlBA-Q";

    public static UserAgent userAgent = new UserAgent("desktop","name.Java-API","v0.1",userName);
    public static Credentials credentials = Credentials.script(userName,passWord, apiKey, secret);
    public static NetworkAdapter networkAdapter = new OkHttpNetworkAdapter(userAgent);
    public static RedditClient redditClient = OAuthHelper.automatic(networkAdapter, credentials);

    public static void main(String[] args) {

        String subR="StockMarket";
        StringBuilder fivePosts = new StringBuilder();
        SubredditReference subreddit = redditClient.subreddit(subR);

        //check if subreddit is valid before moving on
        try{
            subreddit.about().getFullName();
        }catch (Exception E){
            System.out.println("Error");
        }

        //Get only n posts from the category the user input
        DefaultPaginator<Submission> paginator = subreddit.posts().limit(30).sorting(SubredditSort.NEW).build();

        Listing<Submission> firstPage = null;

        //if the subreddit doesn't exist then return error
        try {
            firstPage = paginator.next();
        }catch (NetworkException nE) {
            System.out.println("Error");
        }

        //iterate through page to get posts
        for (Submission post : firstPage) {
            System.out.println("Title:  " + post.getTitle());
            System.out.println("URL:  " + post.getUrl());
            System.out.println("Author:  " + post.getAuthor());
            System.out.println("Score:  " + post.getScore());
            System.out.println("\n");
        }
    }

}



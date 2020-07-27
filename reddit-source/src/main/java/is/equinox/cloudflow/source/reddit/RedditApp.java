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


public class RedditApp {
    static String userName = "gaganeq";
    static String passWord = "@1234@abcd@";
    static String apiKey = "lnrJRkv6cq-uzA";
    static String secret = "IY8Za1Cqj89kP7t18Yd-UNlBA-Q";

    public static UserAgent userAgent = new UserAgent("desktop","name.Java-API","v0.1",userName);
    public static Credentials credentials = Credentials.script(userName,passWord, apiKey, secret);
    public static NetworkAdapter networkAdapter = new OkHttpNetworkAdapter(userAgent);
    public static RedditClient redditClient = OAuthHelper.automatic(networkAdapter, credentials);

    public static String queryReddit() {
        String subR = "tesla";
        int n = 5;
        StringBuilder Posts = new StringBuilder();
        SubredditReference subreddit = redditClient.subreddit(subR);

        //check if subreddit is valid before moving on
        try{
            subreddit.about().getFullName();
        }catch (Exception E){
            return "Error Subreddit not found";
        }

        //Get only n posts from the category the user input
        DefaultPaginator<Submission> paginator = subreddit.posts().limit(n).sorting(SubredditSort.NEW).build();

        Listing<Submission> firstPage;

        //if the subreddit doesn't exist then return error
        try {
            firstPage = paginator.next();
        }catch (NetworkException nE) {
            return "Error";
        }

        //iterate through page to get posts
        for (Submission post : firstPage) {
            Posts.append( /*"Title:     "+*/ post.getTitle() + "\n" +
                            post.getSelfText() + "\n" );


        }


        return Posts.toString();
    }

}




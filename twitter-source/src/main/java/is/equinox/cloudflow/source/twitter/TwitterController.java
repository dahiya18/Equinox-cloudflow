package is.equinox.cloudflow.source.twitter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


@SpringBootApplication
@RestController
public class TwitterController {
    @GetMapping(path = "/twitter/{Topic}")
    public static String controller(@PathVariable String Topic) throws FileNotFoundException {
        Twitter twitter = TwitterClass.makeConnection();
        String data = TwitterStream.getTweets(twitter,Topic);
        try (PrintStream out = new PrintStream(new FileOutputStream("data.txt"))) {
            out.print(data);
        }
        return data;
    }
}





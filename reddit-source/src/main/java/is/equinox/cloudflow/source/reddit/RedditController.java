package is.equinox.cloudflow.source.reddit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RedditController {
    @GetMapping("/reddit")
    public SentimentType controller() {
        return RedditSentiment.analyseReddit(RedditApp.queryReddit());
    }
}





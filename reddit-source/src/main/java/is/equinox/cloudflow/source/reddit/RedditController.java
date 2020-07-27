package is.equinox.cloudflow.source.reddit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class RedditController {
    @GetMapping("/reddit")
    public String controller() {
        return RedditStream.queryReddit();
    }
}





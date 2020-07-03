package is.equinox.cloudflow.source.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import java.util.List;

@EnableBinding(Source.class)
@SpringBootApplication
public class TwitterSourceApp {

    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<List<String>> timeMessageSource() {
        System.out.println(".................Returning the tweet data\n");
        return () -> MessageBuilder.withPayload(TwitterApp.main()).build();
    }
    public static void main(String[] args) {
        System.out.println("................Inside twitter source app\n");
        SpringApplication.run(TwitterSourceApp.class, args);
    }

}

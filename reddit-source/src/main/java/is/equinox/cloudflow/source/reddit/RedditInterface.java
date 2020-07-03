package is.equinox.cloudflow.source.reddit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class RedditInterface {
    @Bean
    @InboundChannelAdapter(
            value = Source.OUTPUT,
            poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1")
    )
    public static MessageSource<String> redditMessageSource() throws IOException {
        //System.out.println(RedditApp.queryReddit("tesla",10));
        return () -> {
            try {
                return MessageBuilder.withPayload(RedditApp.queryReddit("tesla",10)).build();
            } catch (IOException error) {
                error.printStackTrace();
                return null;
            }
        };
    }
}

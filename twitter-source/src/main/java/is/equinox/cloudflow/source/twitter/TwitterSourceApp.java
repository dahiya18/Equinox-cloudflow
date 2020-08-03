package is.equinox.cloudflow.source.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import java.util.List;

//@SpringBootApplication
//@EnableBinding(Source.class)
//@ComponentScan(basePackages = {"is.equinox"})
public class TwitterSourceApp {

    Logger logger = LoggerFactory.getLogger(TwitterSourceApp.class);

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<String> timeMessageSource() {
        //List<String> tweet = TwitterApp.getData();
        String Twitter = "hi";

        logger.info("\n\ntweets  \n\n");

        return () -> MessageBuilder.withPayload(Twitter).build();
    }

}

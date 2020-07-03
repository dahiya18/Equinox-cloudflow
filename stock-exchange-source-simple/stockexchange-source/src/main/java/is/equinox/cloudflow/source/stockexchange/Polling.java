package is.equinox.cloudflow.source.stockexchange;

import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class Polling {

    @Bean
    @InboundChannelAdapter(
            value= Source.OUTPUT,
            poller=@Poller(fixedDelay="12000",maxMessagesPerPoll="1")
    )
    public MessageSource<Double> passOutput() {
        return () -> {
            try {
                return MessageBuilder.withPayload(SourceApplication.getOutput()).build();
            } catch (IOException e) {
                System.out.println("Exception caught in Polling.passOutput()");
                e.printStackTrace();
            }
            return null;
        };
    }
}

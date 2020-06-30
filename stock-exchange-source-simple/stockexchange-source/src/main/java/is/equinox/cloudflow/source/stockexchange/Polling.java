package is.equinox.cloudflow.source.stockexchange;

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

@EnableBinding(Source.class)
@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class Polling {

    @Bean
    @InboundChannelAdapter(
            value = Source.OUTPUT,
            poller = @Poller(fixedDelay = "12000", maxMessagesPerPoll = "1")
    )
    public MessageSource<Double> pushMessageSource() throws IOException {
        System.out.println("Poller value = " + Application.getOutput());
        return () -> {
            try {
                return MessageBuilder.withPayload(Application.getOutput()).build();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
    }
}

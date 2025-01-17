package is.equinox.cloudflow.source.stockexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;

@SpringBootApplication
@EnableBinding(Source.class)
public class StockApplication {

    Logger logger = LoggerFactory.getLogger(StockApplication.class);

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT,poller = @Poller(fixedDelay = "60000", maxMessagesPerPoll = "1"))
    public MessageSource<Double> addStocks() throws IOException {

        StockController avStockController = new StockController();
        StockReadProperties.getPropValues();
        double stocks = avStockController.generateStocks();

        logger.info("stocks : {}",stocks);
        return ()-> MessageBuilder.withPayload(stocks).build();
    }
}

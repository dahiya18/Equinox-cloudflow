package is.equinox.cloudflow.source.reddit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.stream.messaging.Source;



@EnableBinding(Source.class)
@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class RedditSourceAppEntry implements ApplicationRunner, ApplicationContextAware {

	private ConfigurableApplicationContext applicationContext;
	private static Logger logger = LoggerFactory.getLogger(is.equinox.cloudflow.source.reddit.RedditSourceAppEntry.class);


	@StreamListener(Source.OUTPUT)
	public void loggerSink(String date) {
		logger.info("Received: " + date);
	}

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		SpringApplication.run(is.equinox.cloudflow.source.reddit.RedditSourceAppEntry.class, args);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext)applicationContext;
	}

	@Override
	public void run(ApplicationArguments appArgs) throws Exception {
		SpringApplication.exit(applicationContext, () -> 0);
	}

}

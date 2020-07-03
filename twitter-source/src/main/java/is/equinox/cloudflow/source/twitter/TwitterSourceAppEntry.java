package is.equinox.cloudflow.source.twitter;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
@EnableBinding(Source.class)
@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class TwitterSourceAppEntry implements ApplicationRunner,ApplicationContextAware {

	private ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		SpringApplication.run(TwitterSourceAppEntry.class, args);
	}

	@Override
	public void run(ApplicationArguments appArgs) throws Exception {
		System.out.println("Hello World!");
		List<String> result = TwitterApp.main();
		SpringApplication.exit(applicationContext, () -> 0);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
}

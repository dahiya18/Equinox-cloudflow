package is.equinox.cloudflow.source.twitter;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
//@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})

public class TwitterSourceAppEntry implements ApplicationRunner,ApplicationContextAware {

	private ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		SpringApplication.run(TwitterSourceApp.class, args);
	}

	@Override
	public void run(ApplicationArguments appArgs) throws Exception {
		System.out.println("Hello World!");
		SpringApplication.exit(applicationContext, () -> 0);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
	
}

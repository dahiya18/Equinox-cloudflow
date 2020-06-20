package is.equinox.cloudflow.source.stockexchange;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class StockExchangeSourceAppEntry implements ApplicationRunner, ApplicationContextAware  {

	private ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		SpringApplication.run(StockExchangeSourceAppEntry.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws IOException {

		Application.queryStocks();

		SpringApplication.exit(applicationContext, () -> 0);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
}

package is.equinox.cloudflow.source.stockexchange;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"is.equinox"})
public class StockExchangeSourceAppEntry implements ApplicationRunner {

	private ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		System.setProperty("user.timezone", "UTC");
		SpringApplication.run(StockExchangeSourceAppEntry.class, args);
	}

	@Override
	public void run(ApplicationArguments appArgs) throws Exception {
		System.out.println("Hello World!");
		SpringApplication.exit(applicationContext, () -> 0);
	}	

}

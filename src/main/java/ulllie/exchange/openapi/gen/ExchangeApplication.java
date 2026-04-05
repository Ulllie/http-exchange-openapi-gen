package ulllie.exchange.openapi.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ulllie.exchange.openapi.gen.config.WeatherClientProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherClientProperties.class)
public class ExchangeApplication {

	static void main(String[] args) {
		SpringApplication.run(ExchangeApplication.class, args);
	}

}

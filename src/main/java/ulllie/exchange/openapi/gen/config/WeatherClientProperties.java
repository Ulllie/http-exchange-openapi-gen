package ulllie.exchange.openapi.gen.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "weather.client")
public record WeatherClientProperties(@NotBlank String baseUrl) {}

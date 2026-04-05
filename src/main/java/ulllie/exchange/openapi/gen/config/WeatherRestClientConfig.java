package ulllie.exchange.openapi.gen.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.HttpServiceGroupConfigurer.Groups;
import org.springframework.web.service.registry.ImportHttpServices;
import ulllie.exchange.openapi.gen.client.api.OpenMeteoApi;

/**
 * Registers the Open-Meteo HttpExchange client on top of {@link RestClient} (default mode).
 */
@Configuration
@Profile("!webclient")
@ImportHttpServices(group = "weather", types = OpenMeteoApi.class)
public class WeatherRestClientConfig implements RestClientHttpServiceGroupConfigurer {

    private final WeatherClientProperties weatherClientProperties;
    private final Optional<LocalHttp11ClientSupport> localHttp11ClientSupport;

    public WeatherRestClientConfig(
            WeatherClientProperties weatherClientProperties,
            Optional<LocalHttp11ClientSupport> localHttp11ClientSupport) {
        this.weatherClientProperties = weatherClientProperties;
        this.localHttp11ClientSupport = localHttp11ClientSupport;
    }

    @Override
    public void configureGroups(Groups<RestClient.Builder> groups) {
        groups.filterByName("weather").forEachClient(
                ($, builder) -> {
                    builder.baseUrl(weatherClientProperties.baseUrl());
                    localHttp11ClientSupport.ifPresent(
                            s -> builder.requestFactory(s.restClientRequestFactory()));
                });
    }
}

package ulllie.exchange.openapi.gen.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.HttpServiceGroupConfigurer.Groups;
import org.springframework.web.service.registry.ImportHttpServices;
import ulllie.exchange.openapi.gen.client.api.OpenMeteoApi;

/**
 * Demo: the same {@link OpenMeteoApi}, but backed by {@link WebClient}.
 * Activated with the {@code webclient} profile.
 */
@Configuration
@Profile("webclient")
@ImportHttpServices(group = "weather", types = OpenMeteoApi.class)
public class WeatherWebClientConfig implements WebClientHttpServiceGroupConfigurer {

    private final WeatherClientProperties weatherClientProperties;
    private final Optional<LocalHttp11ClientSupport> localHttp11ClientSupport;

    public WeatherWebClientConfig(
            WeatherClientProperties weatherClientProperties,
            Optional<LocalHttp11ClientSupport> localHttp11ClientSupport) {
        this.weatherClientProperties = weatherClientProperties;
        this.localHttp11ClientSupport = localHttp11ClientSupport;
    }

    @Override
    public void configureGroups(Groups<WebClient.Builder> groups) {
        groups.filterByName("weather").forEachClient(
                ($, builder) -> {
                    builder.baseUrl(weatherClientProperties.baseUrl());
                    localHttp11ClientSupport.ifPresent(
                            s -> builder.clientConnector(s.webClientConnector()));
                });
    }
}

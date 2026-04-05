package ulllie.exchange.openapi.gen.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ulllie.exchange.openapi.gen.client.api.OpenMeteoApi;
import ulllie.exchange.openapi.gen.client.model.ForecastResponse;
import ulllie.exchange.openapi.gen.support.ApiValidationHelper;

/**
 * Wrapper over the generated {@link OpenMeteoApi}: validates the response body with Bean Validation
 * right after deserialization.
 */
@Component
@RequiredArgsConstructor
public class OpenMeteoClient {

    private final OpenMeteoApi        api;
    private final ApiValidationHelper validator;

    public ForecastResponse getForecast(Double lat, Double lon, Boolean currentWeather) {
        return validator.requireValidBody(
                api.getForecast(lat, lon, currentWeather),
                "OpenMeteo"
        );
    }
}
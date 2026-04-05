package ulllie.exchange.openapi.gen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ulllie.exchange.openapi.gen.client.OpenMeteoClient;
import ulllie.exchange.openapi.gen.client.model.CurrentWeather;
import ulllie.exchange.openapi.gen.client.model.ForecastResponse;
import ulllie.exchange.openapi.gen.server.api.InternalWeatherApi;
import ulllie.exchange.openapi.gen.server.model.WeatherResponse;

@RestController
@RequiredArgsConstructor
public class WeatherController implements InternalWeatherApi {

    private final OpenMeteoClient openMeteoClient;

    @Override
    public WeatherResponse getAggregatedWeather(Double latitude, Double longitude) {
        ForecastResponse forecastResponse = openMeteoClient.getForecast(latitude, longitude, true);

        WeatherResponse response = new WeatherResponse(latitude, longitude);
        CurrentWeather cw = forecastResponse.getCurrentWeather();

        response.setTemperatureCelsius(cw.getTemperature());
        response.setWindSpeedKmh(cw.getWindspeed());
        response.setWindDirectionDeg(cw.getWinddirection());
        response.setWeatherCode(cw.getWeathercode());
        response.setObservedAt(cw.getTime());

        return response;
    }
}

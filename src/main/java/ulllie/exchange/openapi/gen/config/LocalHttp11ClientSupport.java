package ulllie.exchange.openapi.gen.config;

import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

/**
 * HTTP client settings for environments like WireMock (HTTP/1.1 only).
 * Added to the context only when the {@code local} profile is active.
 */
public record LocalHttp11ClientSupport(
        JdkClientHttpRequestFactory restClientRequestFactory,
        ReactorClientHttpConnector webClientConnector) {}

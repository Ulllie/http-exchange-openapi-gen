package ulllie.exchange.openapi.gen.config;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import reactor.netty.http.HttpProtocol;

@Configuration
@Profile("local")
public class LocalHttp11ClientSupportConfig {

    @Bean
    public LocalHttp11ClientSupport localHttp11ClientSupport() {
        HttpClient jdkClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        JdkClientHttpRequestFactory restFactory = new JdkClientHttpRequestFactory(jdkClient);

        reactor.netty.http.client.HttpClient nettyClient = reactor.netty.http.client.HttpClient.create()
                .protocol(HttpProtocol.HTTP11);
        ReactorClientHttpConnector webConnector = new ReactorClientHttpConnector(nettyClient);

        return new LocalHttp11ClientSupport(restFactory, webConnector);
    }
}

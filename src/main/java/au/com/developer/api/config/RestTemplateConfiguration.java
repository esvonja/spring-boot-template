package au.com.developer.api.config;

import au.com.auspost.aws.common.LocalDev;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static java.util.Arrays.asList;

@Configuration
@Slf4j
public class RestTemplateConfiguration {

    public static final int MAX_CONNECTIONS = 200;
//
//    @Bean
//    public RestTemplate restTemplate(HttpClient httpClient, ClientHttpRequestInterceptor clientHttpRequestInterceptor) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(
//                new HttpComponentsClientHttpRequestFactory(httpClient)
//        ));
//        restTemplate.setInterceptors(asList(clientHttpRequestInterceptor));
//        return restTemplate;
//    }
//
//    @Bean
//    public HttpClientConnectionManager connectionManager() {
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setMaxTotal(MAX_CONNECTIONS);
//        connectionManager.setDefaultMaxPerRoute(MAX_CONNECTIONS);
//        return connectionManager;
//    }
//
//    @Bean
//    public HttpClient httpClientAWS(HttpClientConnectionManager connectionManager) {
//        return org.apache.http.impl.client.HttpClientBuilder.create().setConnectionManager(connectionManager).build();
//    }
}

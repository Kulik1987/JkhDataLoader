package ru.kulikovskiy.jkh.jkhdataloader.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean(name = "jkhResttemplate")
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(10)).setReadTimeout(Duration.ofSeconds(300)).build();
    }

}

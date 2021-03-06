package ru.kulikovskiy.jkh.jkhdataloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms
@EnableCaching
@EnableScheduling
@EnableJpaRepositories
public class JkhDataLoaderService {
    public static void main(String[] args) {
        SpringApplication.run(JkhDataLoaderService.class, args);
    }
}

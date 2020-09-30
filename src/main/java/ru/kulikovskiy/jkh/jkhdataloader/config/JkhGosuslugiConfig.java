package ru.kulikovskiy.jkh.jkhdataloader.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jkh")
@Data
public class JkhGosuslugiConfig {
    private String url;
}

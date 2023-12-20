package ru.netology.akhairutdinova.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "operations.processing")
public class OperationProperties {
    private int timeout;
    private int operationsMaxCount;
}

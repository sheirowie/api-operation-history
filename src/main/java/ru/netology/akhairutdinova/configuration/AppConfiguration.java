package ru.netology.akhairutdinova.configuration;

import org.springframework.context.annotation.*;
import ru.netology.akhairutdinova.service.*;

@Configuration
public class AppConfiguration {
    @Bean
    public StatementService statementService() {
        return new StatementService();
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

    @Bean
    public AsyncInputOperationService asyncInputOperationService(StatementService statementService) {
        return new AsyncInputOperationService(statementService);
    }
}

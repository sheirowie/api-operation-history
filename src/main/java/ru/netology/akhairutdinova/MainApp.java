package ru.netology.akhairutdinova;

import org.springframework.context.*;
import org.springframework.context.annotation.*;
import ru.netology.akhairutdinova.configuration.*;
import ru.netology.akhairutdinova.service.*;
import java.util.Scanner;

public class MainApp {
    public static final Scanner scanner = new Scanner(System.in);

    //public static CustomerService customerService = new CustomerService();
    //public static AsyncInputOperationService asyncInputOperationService;
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        
        StatementService statementService = applicationContext.getBean(StatementService.class);
        CustomerService customerService = applicationContext.getBean(CustomerService.class);
        AsyncInputOperationService asyncInputOperationService = applicationContext.getBean(AsyncInputOperationService.class);

        customerService.inputCustomers(scanner);
        asyncInputOperationService.startAsyncOperationProcessing();
        statementService.inputOperations(scanner, customerService, asyncInputOperationService);
        statementService.viewCustomersOperations(scanner, customerService);
        System.out.println("End of the service.");
    }
}
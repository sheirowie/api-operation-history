package ru.netology.akhairutdinova;

import org.springframework.context.*;
import org.springframework.context.annotation.*;
import ru.netology.akhairutdinova.configuration.*;
import ru.netology.akhairutdinova.service.*;
import java.util.Scanner;

public class MainApp {
    public static final Scanner scanner = new Scanner(System.in);
    public static final StatementService statementService = new StatementService();
    public static final CustomerService customerService = new CustomerService();
    public static final AsyncInputOperationService asyncInputOperationService = new AsyncInputOperationService(statementService);
    public static void main(String[] args) {
        customerService.inputCustomers(scanner);
        asyncInputOperationService.startAsyncOperationProcessing();
        statementService.inputOperations(scanner, customerService, asyncInputOperationService);
        statementService.viewCustomersOperations(scanner, customerService);
        System.out.println("End of the service.");
    }
}
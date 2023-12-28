package ru.netology.akhairutdinova.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.domain.Customer;
import ru.netology.akhairutdinova.domain.enums.Currency;
import ru.netology.akhairutdinova.domain.operations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private StatementService statementService;
    @Test
    public void getOperationTest() {
        List<Operation> customerOperations = statementService.getOperations(1);
        assertNotNull(customerOperations);
        assertEquals(0, customerOperations.size());
    }

    @Test
    public void saveOperationTest() {
        Operation operation1 = new Operation(1000, 1000, Currency.USD,"Merch", 3);
        Operation operation2 = new Operation(900, 900, Currency.USD, "Merch", 3);
        Customer customer = new Customer(3, "Vasya");
        statementService.getCustomerService().setCustomer(customer);
        statementService.setOperation(operation1);
        statementService.setOperation(operation2);

        List<Operation> customerOperations = statementService.getOperations(3);
        List<Operation> expectedOperations = new ArrayList<Operation>();
        expectedOperations.add(new Operation(1000, 1000, Currency.USD,"Merch", 3));
        expectedOperations.add(new Operation(900, 900, Currency.USD, "Merch", 3));

        assertEquals(expectedOperations, customerOperations);
    }
}


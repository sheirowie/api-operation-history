package ru.netology.akhairutdinova.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.domain.Customer;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void addCustomerTest() {
        Customer customer1 = new Customer(1, "Spring");
        Customer customer2 = new Customer(2, "Boot");
        Customer customer3 = new Customer(3, "Cust");

        customerService.setCustomer(customer1);
        customerService.setCustomer(customer2);
        customerService.setCustomer(customer3);
        assertEquals(customer1, customerService.getCustomer(1));
        assertEquals(customer2, customerService.getCustomer(2));
        assertEquals(customer3, customerService.getCustomer(3));
    }
}

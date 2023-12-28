package ru.netology.akhairutdinova.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.controller.dto.*;
import ru.netology.akhairutdinova.domain.Customer;
import ru.netology.akhairutdinova.service.CustomerService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerController customerController;
    @Autowired
    private CustomerService customerService;

    @Test
    public void serviceControllerEqualTest() {
        for (Customer customer: customerService.getStorage()){
            CustomerDTO customerDTO = customerController.getCustomer(customer.getId());
            assertEquals(customer.getId(), customerDTO.getId());
            assertEquals(customer.getName(), customerDTO.getName());
        }
    }

    @Test
    public void setCustomerTest() {
        Customer customer = new Customer(6, "Test");
        customerController.setCustomer(customer);
        assertEquals(customer, customerService.getCustomer(6));
    }

    @Test
    public void getCustomersTest() {
        CustomersGetResponse customers = customerController.getCustomers();
        CustomerDTO customer1 = customers.getCustomers().get(0);
        CustomerDTO customer2 = customers.getCustomers().get(1);

        assertEquals(1, customer1.getId());
        assertEquals(2, customer2.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals("Boot", customer2.getName());
    }
}

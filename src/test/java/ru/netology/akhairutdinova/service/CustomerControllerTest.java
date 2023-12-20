package ru.netology.akhairutdinova.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.controller.CustomerController;
import ru.netology.akhairutdinova.controller.dto.*;
import ru.netology.akhairutdinova.domain.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerController customerController;
    @Autowired
    private CustomerService customerService;

    @Test
    public void fullGetClientTest() {
        for (Customer customer: customerService.getStorage()){
            getClientTest(customer);
        }
    }

    @Test
    public void setClientTest() {
        int savedStorageSize = customerController.getCustomers().getCustomers().size();
        customerController.setCustomer(new Customer(5, "Fifth client"));
        savedStorageSize += 1;
        assertEquals(customerController.getCustomers().getCustomers().size(), savedStorageSize);

        customerController.setCustomer(new Customer(1, "New first client"));
        assertEquals(customerController.getCustomers().getCustomers().size(), savedStorageSize);

        int index = customerService.getStorage().indexOf(new Customer(1, "New first client"));
        assertEquals(customerService.getStorage().get(index).getName(), "New first client");

        index = customerService.getStorage().indexOf(new Customer(5, "Fifth client"));
        assertEquals(customerService.getStorage().get(index).getName(), "Fifth client");
    }

    private void getClientTest(Customer customer) {
        CustomerDTO customerDTO = customerController.getCustomer(customer.getId());

        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getName(), customerDTO.getName());
    }
    @Test
    public void getClientsTest() {
        CustomersGetResponse customers = customerController.getCustomers();
        CustomerDTO customer1 = customers.getCustomers().get(0);
        CustomerDTO customer2 = customers.getCustomers().get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }
}

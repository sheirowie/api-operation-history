package ru.netology.akhairutdinova.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.domain.Customer;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void getCustomerTest(){
        Customer customer2 = customerService.getCustomer(2);
        Customer otherCustomer = customerService.getCustomer(5);

        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
        assertNull(otherCustomer);
    }

    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerService.getStorage();

        Customer customer1 = customers.get(0);
        Customer customer2 = customers.get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
    }

    @Test
    public void setCustomerTest(){
        int savedStorageSize = customerService.getStorage().size();
        customerService.setCustomer(new Customer(5, "Fifth client"));
        savedStorageSize+=1;
        assertEquals(customerService.getStorage().size(), savedStorageSize);

        customerService.setCustomer(new Customer(1, "New first client"));
        assertEquals(customerService.getStorage().size(), savedStorageSize);

        int index = customerService.getStorage().indexOf(new Customer(1, "New first client"));
        assertEquals(customerService.getStorage().get(index).getName(), "New first client");

        index = customerService.getStorage().indexOf(new Customer(5, "Fifth client"));
        assertEquals(customerService.getStorage().get(index).getName(), "Fifth client");
    }
}

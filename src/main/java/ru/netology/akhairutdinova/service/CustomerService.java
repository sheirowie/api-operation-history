package ru.netology.akhairutdinova.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.akhairutdinova.domain.Customer;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomerService {
    private final List<Customer> storage = new ArrayList<>();

    @PostConstruct
    public void initStorage() {
        storage.add(new Customer(1,"Spring"));
        storage.add(new Customer(2, "Boot"));
    }

    public List<Customer> getStorage(){
        return storage;
    }

    public Customer getCustomer(int id) {
        return storage.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst().orElse(null);
    }

    public void setCustomer(Customer cstmer) {
        Customer customer = getCustomer(cstmer.getId());
        if (customer != null) {
            int index = storage.indexOf(customer);
            storage.get(index).setName(cstmer.getName());
            return;
        }
        storage.add(cstmer);
    }
}

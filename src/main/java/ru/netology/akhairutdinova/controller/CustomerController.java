package ru.netology.akhairutdinova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.akhairutdinova.controller.dto.CustomerDTO;
import ru.netology.akhairutdinova.controller.dto.CustomersGetResponse;
import ru.netology.akhairutdinova.domain.Customer;
import ru.netology.akhairutdinova.service.CustomerService;
import ru.netology.akhairutdinova.service.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customers/")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public CustomersGetResponse getCustomers(){
        List<Customer> customers = customerService.getStorage();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("{id}")
    public CustomerDTO getCustomer(@PathVariable("id") int id){
        Customer customer = customerService.getCustomer(id);
        return new CustomerDTO(customer.getId(), customer.getName());
    }
    @PostMapping
    public void setCustomer(Customer customer){
        customerService.setCustomer(customer);
        System.out.println("Added successfully.");
    }
}

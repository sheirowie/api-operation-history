package ru.netology.akhairutdinova.service;

import ru.netology.akhairutdinova.domain.*;

import java.util.*;

public class CustomerService {
    private static final List<Customer> storage = new ArrayList<>();
    public CustomerService ( ) { }

    public void inputCustomers(Scanner scanner) {
        int customerID = 0;
        while (true) {
            System.out.println("Name: ");
            String name = scanner.nextLine();
            storage.add(new Customer(customerID, name));
            customerID++;

            System.out.println("Do you want to add another customer? Yes/No");
            String response = scanner.nextLine();
            if (response.equals("No")) {
                break;
            }
        }
    }

    public Customer getCustomer(int position) {
        try {
            Customer customer = storage.get(position);
            return customer;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addCustomer(Customer customer) {
            storage.add(customer);

    }


}

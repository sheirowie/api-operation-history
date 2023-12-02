package ru.netology.akhairutdinova.service;

import org.springframework.stereotype.Component;
import ru.netology.akhairutdinova.domain.Customer;
import ru.netology.akhairutdinova.domain.Operation;
import ru.netology.akhairutdinova.enums.Currency;

import java.util.*;

@Component
public class StatementService {
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public void saveCustomerOperation(int customerId, Operation operation) {
        if(!storage.containsKey(customerId)) {
            storage.put(customerId, new ArrayList<Operation>());
        }
        storage.get(customerId).add(operation);
    }

    public void viewCustomersOperations(Scanner scanner, CustomerService customerService) {
        while (true) {
            System.out.println("Choose number of client: ");
            int clientId = scanner.nextInt();
            scanner.nextLine();

            while (customerService.getCustomer(clientId) == null) {
                System.out.println("Client not found. Choose another client: ");
                clientId = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Operations of the " + customerService.getCustomer(clientId).toString());
            for (Operation operation : this.getCustomerOperations(clientId)) {
                System.out.print(operation + ", ");
            }
            System.out.println();
            System.out.println("Do you want to view operations of another customer? Yes/No");
            String response = scanner.nextLine();
            if (response.equals("No")) {
                break;
            }
        }
    }

    public List<Operation> getCustomerOperations(int customerId) {
        return storage.get(customerId);
    }

    private int getOperationCount(int customerId) {
        return storage.get(customerId).size();
    }

    public void inputOperations(Scanner scanner, CustomerService customerService, AsyncInputOperationService async) {
        int operationID = 0;
        while (true) {
            System.out.println("Sum: ");
            int sum = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Currency: 1. RUB 2. USD 3. EURO");
            Currency currency;
            int inputCurrency = scanner.nextInt();
            switch (inputCurrency) {
                case 2 : currency = ru.netology.akhairutdinova.enums.Currency.USD; break;
                case 3 : currency = ru.netology.akhairutdinova.enums.Currency.EURO; break;
                default : currency = Currency.RUB; break;
            }
            scanner.nextLine();
            System.out.println("Merchant: ");
            String merchant = scanner.nextLine();

            System.out.println("Client: ");
            int customerId = scanner.nextInt();
            scanner.nextLine();

            while (customerService.getCustomer(customerId) == null) {
                System.out.println("Customer not found. You can:");
                System.out.println("1. Choose another customer.");
                System.out.println("2. Create customer with this ID");
                int response = scanner.nextInt();
                scanner.nextLine();
                switch (response) {
                    case 1:
                        System.out.println("Client: ");
                        customerId = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Name: ");
                        String name = scanner.nextLine();
                        customerService.addCustomer(new Customer(customerId, name));
                        break;
                }
            }
            Operation createdOperation = new Operation(operationID, sum, currency, merchant);
            async.offerOperation(customerId, createdOperation);
            operationID++;

            System.out.println("Do you want to enter next operation? Yes/No");
            String response = scanner.nextLine();
            if (response.equals("No")) {
                break;
            }
        }
    }
}

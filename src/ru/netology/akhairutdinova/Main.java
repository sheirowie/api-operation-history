package ru.netology.akhairutdinova;

import ru.netology.akhairutdinova.domain.*;
import ru.netology.akhairutdinova.exceptions.CustomerOperationOutOfBoundException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final int MAX_OPERATIONS = 1000;
    public static final int MAX_CUSTOMERS = 100;
    public static final Operation[] operations = new Operation[MAX_OPERATIONS];
    public static final Customer[] customers = new Customer[MAX_CUSTOMERS];
    public static final int[][] statement = new int[MAX_CUSTOMERS][MAX_OPERATIONS / MAX_CUSTOMERS];
    public static final int[] customerOperations = new int[MAX_CUSTOMERS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Main.inputCustomers(scanner);
        Main.inputOperations(scanner);
        System.out.println("Customers: " + Arrays.toString(customers));
        Main.viewCustomersOperations(scanner);
        System.out.println("End of the service.");
    }

    public static Operation[] getOperations(int clientId) {
        int[] operationsId = statement[clientId];
        Operation[] clientOperations = new Operation[operationsId.length];
        for  (int i = 0; i < clientOperations.length; i++) {
            clientOperations[i] = operations[operationsId[i]];
        } //fix the 0 index mistake
        return clientOperations;
    }
    private static void viewCustomersOperations(Scanner scanner) {
        while (true) {
            System.out.println("Choose number of client: ");
            int clientId = scanner.nextInt();
            scanner.nextLine();

            while (customers[clientId] == null) {
                System.out.println("Client not found. Choose another client: ");
                clientId = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Operations of the " + customers[clientId].toString());
            System.out.println(Arrays.toString(getOperations(clientId)));
            System.out.println("Do you want to view operations of another customer? Yes/No");
            String response = scanner.nextLine();
            if (response.equals("No")) {
                break;
            }
        }

    }
    private static void inputOperations(Scanner scanner) {
        int operationID = 0;
        while (true) {
            System.out.println("Sum: ");
            int sum = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Currency: ");
            String currency = scanner.nextLine();
            System.out.println("Merchant: ");
            String merchant = scanner.nextLine();

            System.out.println("Client: ");
            int clientID = scanner.nextInt();
            scanner.nextLine();

            while (customers[clientID] == null) {
                System.out.println("Client not found. You can:");
                System.out.println("1. Choose another client.");
                System.out.println("2. Create client with this ID");
                int response = scanner.nextInt();
                scanner.nextLine();
                switch (response) {
                    case 1:
                        System.out.println("Client: ");
                        clientID = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Name: ");
                        String name = scanner.nextLine();
                        customers[clientID] = new Customer(clientID, name);
                        break;
                }
            }

            operations[operationID] = new Operation(operationID, sum, currency, merchant);
            try {
                inputStatement(clientID, operationID);
            } catch (CustomerOperationOutOfBoundException e) {
                System.out.println("Cannot save anymore operations for this customer.");
            }
            customerOperations[clientID]++;
            operationID++;

            if (operationID == MAX_OPERATIONS) {
                System.out.println("Cannot add more operations.");
                break;
            }

            System.out.println("Do you want to enter next operation? Yes/No");
            String response = scanner.nextLine();
            if (response.equals("No")) {
                break;
            }
        }
    }
    private static void inputCustomers(Scanner scanner) {
        int customerID = 0;
        while (true) {
            System.out.println("Name: ");
            String name = scanner.nextLine();

            customers[customerID] = new Customer(customerID, name);
            customerOperations[customerID] = 0;
            customerID++;

            if (customerID == MAX_CUSTOMERS) {
                System.out.println("Cannot add more customers.");
                break;
            }

            System.out.println("Do you want to add another customer? Yes/No");
            String response = scanner.nextLine();
            if (response.equals("No")) {
                break;
            }
        }
    }

    private static void inputStatement(int customerId, int operationId) {
        if (customerOperations[customerId] >= 10) {
            throw new CustomerOperationOutOfBoundException(customerId, operationId);
        }
        else {
            statement[customerId][customerOperations[customerId]] = operationId;
        }
    }
}
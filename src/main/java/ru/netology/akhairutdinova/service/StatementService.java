package ru.netology.akhairutdinova.service;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.netology.akhairutdinova.configuration.OperationProperties;
import ru.netology.akhairutdinova.domain.Customer;
import ru.netology.akhairutdinova.domain.operations.Operation;

import java.util.*;

@RequiredArgsConstructor
@Component
@Data
public class StatementService {
    private final CustomerService customerService;
    private final OperationProperties operationProperties;
    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    private int getIndex(int operationId){
        int customerId = operationId / operationProperties.getOperationsMaxCount();
        List<Operation> list = getOperations(customerId);
        return list.stream()
                .filter(operation -> operation.getId() == operationId)
                .map(operation -> operation.getId() % operationProperties.getOperationsMaxCount())
                .findFirst().orElse(-1);
    }

    public List<Operation> getOperations(int clientId){
        if (customerService.getCustomer(clientId) == null) {
            return null;
        }
        List<Operation> operations = storage.get(clientId);
        if (operations == null) {
            storage.put(clientId, new ArrayList<>());
            return storage.get(clientId);
        }
        return operations;
    }

    public void addOperation(Operation operation){
        Customer customer = customerService.getCustomer(operation.getCustomerId());
        if (customer == null) {
            return;
        }
        List<Operation> operationsOfCustomer = getOperations(operation.getCustomerId());
        operationsOfCustomer.add(operation);
        storage.put(operation.getCustomerId(), operationsOfCustomer);
    }

    public boolean deleteOperation(int operationId) {
        try {
            int customerId = operationId / operationProperties.getOperationsMaxCount();
            List<Operation> customerOperations = getOperations(customerId);
            customerOperations.remove(operationId % operationProperties.getOperationsMaxCount());
            customerOperations.stream().peek(operation -> {
                 if (operation.getId() > operationId) {
                     storage.get(customerId).get(getIndex(operation.getId())).setId(operation.getId()-1);
                 }
            });
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}

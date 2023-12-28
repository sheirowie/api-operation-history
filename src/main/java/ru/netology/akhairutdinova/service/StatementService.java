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

    public List<Operation> getOperations(int customerId){
        if (customerService.getCustomer(customerId) == null) {
            return null;
        }
        List<Operation> operations = storage.get(customerId);
        if (operations == null) {
            storage.put(customerId, new ArrayList<>());
            return storage.get(customerId);
        }
        return operations;
    }

    public void setOperation (Operation operation) {
        Customer customer = customerService.getCustomer(operation.getCustomerId());
        if (customer == null) return;

        List<Operation> customerOperations = getOperations(operation.getCustomerId());
        customerOperations.add(operation);
        storage.put(operation.getCustomerId(), customerOperations);
    }

    public boolean deleteOperation(int operationId) {
        try {
            for (int i = 0; i < storage.size(); i++) {
                for (int j = 0; j < storage.get(i).size(); j++) {
                    if (storage.get(i).get(j).getId() == operationId) {
                        storage.get(i).remove(operationId);
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

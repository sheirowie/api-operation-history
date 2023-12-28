package ru.netology.akhairutdinova.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.akhairutdinova.service.*;
import ru.netology.akhairutdinova.domain.operations.*;
import ru.netology.akhairutdinova.controller.dto.*;
import ru.netology.akhairutdinova.configuration.OperationProperties;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/operations/")
@RequiredArgsConstructor
public class OperationController {
    private final StatementService statementService;
    private final AsyncInputOperationService asyncInputOperationService;
    private final OperationProperties operationProperties;

    @GetMapping("{id}")
    public OperationsGetResponse getOperations(@PathVariable("id") int customerId) {
        if (statementService.getOperations(customerId) == null) {
            return null;
        }
        List<Operation> operations = statementService.getOperations(customerId);
        List<OperationDTO> operationDTOS = operations.stream()
                .map(operation -> new OperationDTO(operation.getId(), operation.getSum(), operation.getCurrency()))
                .collect(Collectors.toList());
        return new OperationsGetResponse(operationDTOS);
    }
    @PostMapping
    public boolean setOperation(OperationDTOInput operationInput){
        int customerId = operationInput.getCustomerId();
        if (statementService.getOperations(customerId) == null) {
            return false;
        }
        int operationId = customerId * operationProperties.getOperationsMaxCount() +
                statementService.getOperations(customerId).size();
        asyncInputOperationService.offerOperation(new Operation(operationId, operationInput.getSum(),
        operationInput.getCurrency(), operationInput.getMerchant(), customerId));
        System.out.println("Operation is added.");
        return true;
    }

    @DeleteMapping
    public boolean deleteOperation(int operationId){
        return statementService.deleteOperation(operationId);
    }
}

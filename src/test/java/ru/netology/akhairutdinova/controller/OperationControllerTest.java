package ru.netology.akhairutdinova.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.controller.OperationController;
import ru.netology.akhairutdinova.controller.dto.*;
import ru.netology.akhairutdinova.domain.Customer;
import ru.netology.akhairutdinova.domain.enums.Currency;
import ru.netology.akhairutdinova.domain.operations.Operation;
import ru.netology.akhairutdinova.service.AsyncInputOperationService;
import ru.netology.akhairutdinova.service.StatementService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OperationControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private OperationController operationController;
    @Autowired
    private StatementService statementService;
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;

    @Test
    public void getOperationsTest() {
        assertEquals(statementService.getOperations(1).size(), operationController.getOperations(1).getOperations().size());
        assertNull(operationController.getOperations(5));
    }
}

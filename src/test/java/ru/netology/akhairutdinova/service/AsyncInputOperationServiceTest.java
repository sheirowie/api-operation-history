package ru.netology.akhairutdinova.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.domain.enums.Currency;
import ru.netology.akhairutdinova.domain.operations.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    @Autowired
    private StatementService statementService;

    @Test
    public void offerTest() {
        Operation operation = new Operation(1, 123, Currency.USD, "Merch", 1);
        assertTrue(asyncInputOperationService.offerOperation(operation));
    }

}

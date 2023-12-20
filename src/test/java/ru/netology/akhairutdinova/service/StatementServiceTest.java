package ru.netology.akhairutdinova.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.domain.enums.Currency;
import ru.netology.akhairutdinova.domain.operations.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private StatementService statementService;
    private final static List<Operation> operationList = new ArrayList<>();

    @Test
    public void addOperationTest() {
        for (Operation operation : operationList) {
            statementService.addOperation(operation);
        }
    }

    @Test
    public void deleteOperationsTest() {
        addOperationTest();

        boolean bool = statementService.deleteOperation(100000);
        assertEquals(true, bool);

        bool = statementService.deleteOperation(1000);
        assertEquals(false, bool);

    }


    @Test
    public void getOperationsTest() {
        addOperationTest();
        int sizeCustomer1 = statementService.getOperations(1).size();
        int sizeCustomer2 = statementService.getOperations(2).size();

        assertEquals(2, sizeCustomer1);
        assertEquals(2, sizeCustomer2);
        assertNull(statementService.getOperations(5));
    }

    @BeforeAll
    public static void inputOperation() {
        CashbackOperation cashbackOperation =
                new CashbackOperation(100_000, 45, 4500, Currency.RUB, "merchant", 1);
        LoanOperation loanOperation =
                new LoanOperation(200_000, 200_000, 4500, Currency.USD, "merchant", 2);

        Operation operation1 = cashbackOperation;
        Operation operation2 = loanOperation;

        operationList.add(cashbackOperation);
        operationList.add(loanOperation);
        operationList.add(operation1);
        operationList.add(operation2);
    }
}


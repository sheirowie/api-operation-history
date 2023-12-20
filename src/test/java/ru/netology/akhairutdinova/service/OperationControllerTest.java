package ru.netology.akhairutdinova.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.controller.OperationController;
import ru.netology.akhairutdinova.controller.dto.*;
import ru.netology.akhairutdinova.domain.enums.Currency;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OperationControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private OperationController operationController;
    @Autowired
    private StatementService statementService;
    private final static List<OperationDTOInput> operationDTOInputs = new ArrayList<>();

    @Test
    public void getOperationsTest(){
        assertEquals(statementService.getOperations(1).size(), operationController.getOperations(1).getOperations().size());
        assertNull(operationController.getOperations(5));
    }
    @Test
    public void setOperationTest(){
        for(OperationDTOInput operation: operationDTOInputs){
            operationController.setOperation(operation);
        }
    }

    @Test
    public void deleteOperationTest(){
        setOperationTest();
        operationController.deleteOperation(100_000);
        operationController.deleteOperation(200_000);
    }

    @BeforeAll
    public static void inputOperation(){
        String type1 = "loan";
        String type2 = "cashback";
        Integer cashback = 100;

        OperationDTOInput operation =
                new OperationDTOInput(54000, Currency.USD, "merchant", 1, null, null);
        OperationDTOInput cashbackOperation =
                new OperationDTOInput(100000,Currency.RUB, "merchant", 2, cashback, type2);
        OperationDTOInput loanOperation =
                new OperationDTOInput(100000, Currency.EURO, "merchant", 2, null, type1);

        operationDTOInputs.add(cashbackOperation);
        operationDTOInputs.add(loanOperation);
        operationDTOInputs.add(operation);
    }

}

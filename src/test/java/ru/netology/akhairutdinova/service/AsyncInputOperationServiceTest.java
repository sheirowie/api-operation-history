package ru.netology.akhairutdinova.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.akhairutdinova.OperationHistoryApiApplicationTest;
import ru.netology.akhairutdinova.domain.enums.Currency;
import ru.netology.akhairutdinova.domain.operations.*;
import java.util.ArrayList;
import java.util.List;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    private final static List<Operation> operationInputs = new ArrayList<>();

    @Test
    public void startProcessingTest(){
        asyncInputOperationService.startAsyncOperationProcessing();
    }

    @Test
    public void offerOperationTest(){
        for(Operation operation: operationInputs){
            asyncInputOperationService.offerOperation(operation);
        }
    }

    @BeforeAll
    public static void inputOperation(){
        Operation operation =
                new Operation(54000,4000, Currency.RUB,"merchant",1);
        CashbackOperation cashbackOperation =
                new CashbackOperation(100000,100,10000, Currency.EURO,"merchant", 2);
        LoanOperation loanOperation =
                new LoanOperation(100000,100000,5400, Currency.USD, "merchant", 2);

        operationInputs.add(cashbackOperation);
        operationInputs.add(loanOperation);
        operationInputs.add(operation);
    }

}

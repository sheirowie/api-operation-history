package ru.netology.akhairutdinova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.netology.akhairutdinova.domain.*;

import java.util.*;

@Component
public class AsyncInputOperationService {
    private StatementService statementService;
    private Queue<Operation> operationQueue = new LinkedList<>();
    private Queue<Integer> customerIdQueue = new LinkedList<>();

    @Autowired
    public AsyncInputOperationService(StatementService statementService) { this.statementService = statementService; }

    public boolean offerOperation(int customerId, Operation operation) {
        return operationQueue.offer(operation) && customerIdQueue.offer(customerId);

    }

    public void startAsyncOperationProcessing() {
        Thread t = new Thread() {
            @Override
            public void run() {
                processQueue();
            }
        };
        t.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = operationQueue.poll();
            Integer customerId = customerIdQueue.poll();
            if (operation == null) {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation: " + operation);
                statementService.saveCustomerOperation(customerId, operation);
            }
        }
    }
}

package ru.netology.akhairutdinova.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.akhairutdinova.configuration.OperationProperties;
import ru.netology.akhairutdinova.domain.operations.Operation;
import java.util.LinkedList;
import java.util.Queue;

@RequiredArgsConstructor
@Component
public class AsyncInputOperationService {
    private final Queue<Operation> operationQueue = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProperties operationProperties;

    @PostConstruct
    public void init(){
        this.startAsyncOperationProcessing();
    }

    public boolean offerOperation(Operation operation) {
        return operationQueue.offer(operation);
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
            if (operation == null) {
                try {
                    System.out.println("No operations currently processing.");
                    Thread.sleep(operationProperties.getTimeout());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Processing operation:" + operation);
                statementService.addOperation(operation);
            }
        }
    }
}

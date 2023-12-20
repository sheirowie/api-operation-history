package ru.netology.akhairutdinova.exceptions;

public class CustomerOperationOutOfBoundException extends OperationException {
    private int customerId;
    private int operationId;
    public static final String MESSAGE = "Exception while trying to save operation %s for customer %s";

    public CustomerOperationOutOfBoundException(int customerId, int operationId) {
        super();
        this.customerId = customerId;
        this.operationId = operationId;
    }

    @Override
    public String getMessage() {
        return MESSAGE.formatted(operationId, customerId);
    }
}

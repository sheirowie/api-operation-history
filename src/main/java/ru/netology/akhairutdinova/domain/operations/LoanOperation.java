package ru.netology.akhairutdinova.domain.operations;

import lombok.*;
import ru.netology.akhairutdinova.domain.enums.Currency;
import ru.netology.akhairutdinova.domain.interfaces.ConsolePrintable;

@Getter
@Setter
public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    public LoanOperation(int ID, int LOAN_ID, int SUM, Currency CURRENCY, String MERCHANT, int CUSTOMER_ID) {
        super(ID, SUM, CURRENCY, MERCHANT, CUSTOMER_ID);
        this.loanId = LOAN_ID;
    }

    @Override
    public void printToConsole(){
        System.out.println("Operation: " + "id = " + id +
                ", sum = " + sum +
                ", currency = " + currency +
                ", merchant = " + merchant +
                ", loanId = " + loanId + ';');
    }

    @Override
    public String toString() {
        return "LoanOperation { " + "id = " + id +
                ", sum = " + sum +
                ", currency = " + currency +
                ", merchant = " + merchant +
                ", loanId = " + loanId + " }";
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return this.sum * 1337 + this.merchant.hashCode() * 1337;
    }
}

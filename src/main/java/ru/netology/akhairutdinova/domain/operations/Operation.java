package ru.netology.akhairutdinova.domain.operations;

import lombok.Data;
import ru.netology.akhairutdinova.domain.enums.Currency;
import ru.netology.akhairutdinova.domain.interfaces.ConsolePrintable;

@Data
public class Operation implements ConsolePrintable {
    protected int id;
    protected int sum;
    protected Currency currency;
    protected String merchant;
    protected int customerId;

    public Operation(int ID, int SUM, Currency CURRENCY, String MERCHANT, int CUSTOMER_ID){
        this.id = ID;
        this.sum = SUM;
        this.currency = CURRENCY;
        this.merchant = MERCHANT;
        this.customerId = CUSTOMER_ID;
    }

    public void print(){
        System.out.printf("sum: %d, currency: " + this.currency + ", merchant: " + this.merchant + "\n", this.sum);
    }

    @Override
    public void printToConsole(){
        System.out.println("Operation: " + "id = " + id +
                ", sum = " + sum +
                ", currency = " + currency +
                ", merchant = " + merchant + ';');
    }
}

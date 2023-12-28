package ru.netology.akhairutdinova.domain.operations;

import lombok.*;
import ru.netology.akhairutdinova.domain.enums.Currency;
import ru.netology.akhairutdinova.domain.interfaces.ConsolePrintable;

@Getter
@Setter
public class CashbackOperation extends Operation implements ConsolePrintable {
    private int cashbackAmount;

    public CashbackOperation(int ID, int CASHBACK_AMOUNT, int SUM, Currency CURRENCY, String MERCHANT, int CUSTOMER_ID) {
        super(ID, SUM, CURRENCY, MERCHANT, CUSTOMER_ID);
        this.cashbackAmount = CASHBACK_AMOUNT;
    }

    @Override
    public void printToConsole(){
        System.out.println("Operation: " + "id = " + id +
                ", sum = " + sum +
                ", currency = " + currency +
                ", merchant = " + merchant +
                ", cashbackAmount = " + cashbackAmount +
                ';');
    }
}


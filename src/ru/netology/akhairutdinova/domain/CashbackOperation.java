package ru.netology.akhairutdinova.domain;

import ru.netology.akhairutdinova.interfaces.ConsolePrintable;

public class CashbackOperation extends Operation implements ConsolePrintable {
    private int cashbackAmount;

    //<editor-fold desc="Constructor, getters, setters">

    public CashbackOperation(int ID, int SUM, String CURRENCY, String MERCHANT, double PERCENTAGE) {
        super(ID, SUM, CURRENCY, MERCHANT);
        cashbackAmount = (int)Math.round(getSum() * PERCENTAGE);
    }
    public int getCashbackAmount() {
        return cashbackAmount;
    }
    public void setCashbackAmount(double percentage) {
        cashbackAmount = (int)Math.round(getSum() * percentage);
    }

    //</editor-fold>

    @Override
    public void printToConsole() {
        System.out.println("Cashback operation: returned " + this.cashbackAmount + " " + this.getCurrency()
                + " from " + this.getSum() + " " + this.getCurrency() + " for " + this.getMerchant());
    }

    //<editor-fold desc="Basic overrides">

    @Override
    public String toString() {
        return "Cashback operation = {id = " + getId() + ", cashback = " + cashbackAmount + " " + getCurrency() +
                ", sum = " + getSum() + " " + getCurrency() + ", merchant = " + getMerchant() + "}";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    //</editor-fold>
}

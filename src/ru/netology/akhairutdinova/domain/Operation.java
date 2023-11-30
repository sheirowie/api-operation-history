package ru.netology.akhairutdinova.domain;

import ru.netology.akhairutdinova.interfaces.ConsolePrintable;

public class Operation implements ConsolePrintable {
    private int id;
    private int sum;
    private String currency;
    private String merchant;

    //<editor-fold desc="Constructors">

    public Operation (int ID, int SUM, String CURRENCY, String MERCHANT) {
        this.id = ID;
        this.sum = SUM;
        this.currency = CURRENCY;
        this.merchant = MERCHANT;
    }
    public Operation () {
        this.sum = 0;
        this.currency = "RUB";
        this.merchant = "REST API";
    }

    //</editor-fold>

    public void printToConsole() {
        System.out.println("Operation: " + sum + " " + currency + " for " + merchant);
    }

    //<editor-fold desc="Getters, setters, override">

    @Override
    public String toString() {
        return "Operation = {id = " + id + ", sum = " + this.sum + " " + this.currency + ", merchant = " + merchant + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Operation)) {
            return false;
        }
        else {
            return this.id == ((Operation) obj).id &&
                    this.sum == ((Operation) obj).sum &&
                    this.currency == ((Operation) obj).currency &&
                    this.merchant == ((Operation) obj).merchant;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getId() {
        return id;
    }

    public int getSum() {
        return sum;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    //</editor-fold>
}


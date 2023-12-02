package ru.netology.akhairutdinova.domain;

import ru.netology.akhairutdinova.enums.Currency;
import ru.netology.akhairutdinova.interfaces.ConsolePrintable;

public class LoanOperation extends Operation implements ConsolePrintable {
    private int loanId;

    //<editor-fold desc="Constructor, getters, setters">
    public LoanOperation(int ID, int SUM, Currency CURRENCY, String MERCHANT, double PERCENTAGE) {
        super(ID, SUM, CURRENCY, MERCHANT);
        loanId = (int)Math.round(PERCENTAGE);
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getLoanId() {
        return loanId;
    }

    //</editor-fold>

    @Override
    public void printToConsole() {
        System.out.println("Loan operation: loan by " + this.loanId + "percents per month for " +
                this.getSum() + " " + this.getCurrency() + " for " + this.getMerchant());
    }

    //<editor-fold desc="Basic overrides">

    @Override
    public String toString() {
        return "Loan operation = {id = " + getId() + ", loanId = " + loanId +
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

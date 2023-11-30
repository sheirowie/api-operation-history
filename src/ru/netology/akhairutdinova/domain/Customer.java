package ru.netology.akhairutdinova.domain;

public class Customer {
    private int id;
    private String name;
    public Customer (int id, String name) {
        this.id = id;
        this.name = name;
    }

    //<editor-fold desc="Getters, setters, override">

    @Override
    public String toString() {
        return "Client " + id + ": " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer)) {
            return false;
        }
        else {
            return this.id == ((Customer) obj).id && this.name == ((Customer) obj).name;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //</editor-fold>
}

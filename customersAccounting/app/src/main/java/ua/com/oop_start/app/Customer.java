package ua.com.oop_start.app;

import ua.com.oop_start.lib.BaseEntity;

import static java.lang.String.format;

public class Customer extends BaseEntity {
    private String name;
    private int sumofpurchase;
    private double cashback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumofpurchase() {
        return sumofpurchase;
    }

    public void setSumofpurchase(int sumofpurchase) {
        this.sumofpurchase = sumofpurchase;
        setCashback(sumofpurchase * 0.005);
    }

    public void setCashback(double cashback) {
        this.cashback = cashback;
    }

    public double getCashback() {
        return cashback;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id ='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                ", sumofpurchase=" + sumofpurchase +
                ", cashback=" + format("%.2f", cashback) +
                '}';
    }
}

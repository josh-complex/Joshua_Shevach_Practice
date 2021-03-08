package com.company.items;

import java.util.Objects;

public class IceCream {

    private double amount = 100;
    private float integrity = 100;

    public enum Flavor {
        Chocolate,
        Vanilla,
        Strawberry
    }

    public IceCream(Flavor flavor) {
    }

    public void melt(float amount){
        integrity -= amount;
    }

    public void consume(float amount){
        this.amount -= amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public float getIntegrity() {
        return integrity;
    }

    public void setIntegrity(float integrity) {
        this.integrity = integrity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IceCream iceCream = (IceCream) o;
        return Double.compare(iceCream.amount, amount) == 0 && Float.compare(iceCream.integrity, integrity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, integrity);
    }

    @Override
    public String toString() {
        return "IceCream{" +
                "amount=" + amount +
                ", integrity=" + integrity +
                '}';
    }
}

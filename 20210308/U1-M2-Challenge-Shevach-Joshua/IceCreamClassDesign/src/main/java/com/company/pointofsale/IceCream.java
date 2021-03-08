package com.company.pointofsale;

public class IceCream {

    //region class members
    private static double inventory;
    private static float price;
    private static float taxRate;
    //endregion

    //region instance members
    private int quantity;
    //endregion

    public IceCream(int quantity) {
        this.quantity = quantity;
    }

    public void sell(int quantity){
        if(isAvailable()){
            IceCream.inventory -= this.quantity;
        } else System.out.println("Not enough inventory!");
    }

    public void stock(int quantity){
        IceCream.inventory += this.quantity;
    }

    public static double getInventory() {
        return inventory;
    }

    public static float getPrice() {
        return price;
    }

    public static void setPrice(float price) {
        IceCream.price = price;
    }

    public static float getTaxRate() {
        return taxRate;
    }

    public static void setTaxRate(float taxRate) {
        IceCream.taxRate = taxRate;
    }

    public boolean isAvailable() {
        return quantity < inventory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}

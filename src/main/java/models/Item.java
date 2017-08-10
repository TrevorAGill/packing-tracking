package models;


import java.util.ArrayList;
import java.util.List;

public class Item {
    private String itemName;
    private double itemPrice;
    private double itemWeight;
    private boolean purchased = false;
    private boolean packed = false;
    private int quantity;
    private static ArrayList<Item> allItems = new ArrayList<Item>();


    //Constructor
    public Item(String itemName, double itemPrice, double itemWeight, boolean itemPurchased, boolean itemPacked) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemWeight = itemWeight;
        this.purchased = itemPurchased;
        this.packed = itemPacked;
        allItems.add(this);
    }

    public static Item createNewItem(){
        Item newItem = new Item("knife",2.50,.5,false,false);
        return newItem;
    }

    public static void clearItemList(){
        allItems.clear();
    }

    //Setters
    public void setPacked() {
        this.packed = !packed;
    }
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    //Getters
    public static ArrayList<Item> getAllItems() {
        return allItems;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public boolean isPacked() {
        return packed;
    }

    public int getQuantity() {
        return quantity;
    }
}

package models;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
    private List<Item> packed = new ArrayList<Item>();
    private float totalCost = 0;
    private float totalWeight = 0;

    //Constructor
    public Backpack() {

    }

    //Setters
    public void setPacked(Item justPacked) {
        packed.add(justPacked);
    }

    //Getters
    public float getTotalCost() {
        for (Item singleItem: packed) {
            totalCost += singleItem.getItemPrice() * singleItem.getQuantity();
        }
        //round this later on
        return totalCost;
    }

    public float getTotalWeight() {
        for (Item good: packed) {
            totalWeight += good.getItemWeight() * good.getQuantity();
        }
        //round this later on
        return totalWeight;
    }

}

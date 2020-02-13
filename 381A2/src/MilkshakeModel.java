/* CMPT 381
 ASSIGNMENT 2
 KODY MANSTYRSKI
 KOM607
 11223681
 */

import java.util.HashMap;

public class MilkshakeModel {
    private double price;
    private int toppings;
    private int ice_cream;
    private HashMap<String, Integer> items;

    public MilkshakeModel(){
        items = new HashMap<>();
        price = 0;
        toppings = 0;
        ice_cream = 0;
    }

    public void changeIceCream(String flavour, int amount){
        if(ice_cream + amount >= 0 && ice_cream + amount <= 8) {
            ice_cream += amount;
        }
        else{
            ice_cream = 0;
        }
        if(items.containsKey(flavour)){
            items.replace(flavour, amount);
        }
        else{
            items.put(flavour, amount);
        }
        price = ice_cream + (toppings * .5);
    }

    public void changeToppings(String topping, int amount){
        if(toppings + amount >= 0){
            toppings += amount;
        }
        else{
            toppings = 0;
        }

        if(items.containsKey(topping)){
            items.replace(topping, amount);
        }
        else{
            items.put(topping, amount);
        }

        price = ice_cream + (toppings * .5);
    }



}

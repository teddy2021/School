/* CMPT 381
 ASSIGNMENT 2
 KODY MANSTYRSKI
 KOM607
 11223681
 */

import java.util.HashMap;
import java.util.LinkedList;

public class MilkshakeModel {
    private double price;
    private int toppings;
    private int ice_cream;
    private HashMap<String, Integer> ic;
    private HashMap<String, Integer> tp;
    private LinkedList<ModelListener> subscribers;

    public MilkshakeModel(){
        subscribers = new LinkedList<>();
        ic = new HashMap<>();
        ic.put("Chocolate", 0);
        ic.put("Vanilla", 0);
        ic.put("Strawberry", 0);
        ic.put("Lemon", 0);
        ic.put("Coffee",0);
        ic.put("Mint",0);

        tp = new HashMap<>();
        tp.put("Sprinkles", 0);
        tp.put("Cherries", 0);
        tp.put("Chocolate Chips", 0);
        tp.put("Whipped Cream", 0);
        tp.put("Coconut", 0);
        tp.put("Marshmallows", 0);

        price = 0;
        toppings = 0;
        ice_cream = 0;
    }

    private void sumFlavours(){
        ice_cream = 0;
        for(Integer ice: ic.values()){
            ice_cream += ice;
        }
    }

    private void sumToppings(){
        toppings = 0;
        for(Integer tping: tp.values()){
            toppings += tping;
        }
    }

    public void changeIceCream(String flavour, int amount){
        int count = ic.get(flavour);
        int result = count - amount;
        if(result > 0) { // positive difference -> amount < count
            if (ice_cream - result >= 0){ // ice cream will not become a negative amount by the difference
                ic.replace(flavour, amount);
            }
            else{ // ice cream will become negative by the difference
                ic.replace(flavour, 0);
            }
        }
        else{ // negative difference -> amount > count
            int val = Math.abs(result);
            if(ice_cream + val <= 8){ // ice cream will be within bounds for the new amount
                ic.replace(flavour, amount);
            }
            else{ //
                ic.replace(flavour, count + (ice_cream - amount));
            }
        }

        sumFlavours();
        price = (double) ice_cream + ((double) toppings / 2);
        ModelChanged();
    }


    public void changeToppings(String topping, int amount){
        int count = tp.get(topping);
        int result = count - amount;
        if(result > 0) { // positive difference -> amount < count

            if (toppings - result >= 0){ // ice cream will not become a negative amount by the difference
                tp.replace(topping, amount);
            }
            else{ // ice cream will become negative by the difference
                tp.replace(topping, 0);
            }
        }
        else{ // negative difference -> amount > count

            int val = Math.abs(result);
            if(toppings + val <= 8){ // ice cream will be within bounds for the new amount
                tp.replace(topping, amount);
            }
            else{ //
                tp.replace(topping, count + (toppings - amount));
            }
        }

        sumToppings();
        price = (double) ice_cream + ((double) toppings / 2);
        ModelChanged();
    }


    public void addSubscriber(ModelListener m){
        subscribers.add(m);
    }

    public void ModelChanged(){
        for(ModelListener m: subscribers){
            m.viewNotify();
        }
    }

    public HashMap<String, Integer> getFlavours(){
        return ic;
    }

    public HashMap<String, Integer> getToppings(){
        return tp;
    }

    public double getTotal(){
        return price;
    }

    public int getIceCream(){
        return ice_cream;
    }

    public void reset(){
        ice_cream = 0;
        toppings = 0;
        for(String item: ic.keySet()){
            ic.replace(item, 0);
        }
        for(String item: tp.keySet()){
            tp.replace(item, 0);
        }
        ModelChanged();
    }

}

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
        if(!ic.containsKey(flavour)){
           ic.put(flavour, 0);
        }
        int count = ic.get(flavour);
        if(ice_cream + count + amount <= 8 && ice_cream + count + amount >= 0){
            ic.replace(flavour, count + amount);
        }
        else if(ice_cream + count + amount < 0){
            ic.replace(flavour, 0);
        }
        else if(ice_cream + count + amount > 8){
                ic.replace(flavour, ice_cream - amount);
        }
        System.out.println("b: " + ice_cream);
        sumFlavours();
        System.out.println("a: " + ice_cream);
        price = (double)ice_cream + ((double)toppings/2);
        ModelChanged();
        }


    public void changeToppings(String topping, int amount){
        if(!tp.containsKey(topping)){
            tp.put(topping, 0);
        }
        int count = tp.get(topping);

        if(toppings + count + amount <= 8 && toppings + count + amount >= 0){
            tp.replace(topping, amount);
        }
        else if(ice_cream + count + amount < 0){
                tp.replace(topping, 0);
        }
        else if(ice_cream + count + amount > 0){
                tp.replace(topping, toppings + amount);
        }
        sumToppings();
        price = (double)ice_cream + ((double)toppings/2);
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

}

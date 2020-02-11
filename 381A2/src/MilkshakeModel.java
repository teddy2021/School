/* CMPT 381
 ASSIGNMENT 2
 KODY MANSTYRSKI
 KOM607
 11223681
 */

public class MilkshakeModel {
    private double price;
    private int toppings;
    private int ice_cream;

    public MilkshakeModel(){
        price = 0;
        toppings = 0;
        ice_cream = 0;
    }

    public void changeIceCream(int amount){
        if(ice_cream + amount >= 0) {
            ice_cream += amount;
        }
        else{
            ice_cream = 0;
        }
        price = ice_cream + (toppings * .5);
    }

    public void changeToppings(int amount){
        if(toppings + amount >= 0){
            toppings += amount;
        }
        else{
            toppings = 0;
        }
        price = ice_cream + (toppings * .5);
    }



}

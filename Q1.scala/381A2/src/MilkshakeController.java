

public class MilkshakeController {

    private MilkshakeModel shake;

    public MilkshakeController(){}

    public void setShake(MilkshakeModel m){shake = m;}

    public void handleSlide(String flavour, double value){
        shake.changeIceCream(flavour, (int)Math.round(value));
    }

    public void handleClick(int pos, int count, String topping){
        if( count > 1){
            shake.changeToppings(topping, 2 * pos);
        }
        else{
            shake.changeToppings(topping,  pos);
        }
    }

    public void reset(){
        shake.reset();
    }

}

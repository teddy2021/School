import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.LinkedList;

public class SummaryView implements ModelListener{

    private MilkshakeModel model;
    private MilkshakeController ctrl;
    private VBox display;

    public SummaryView(MilkshakeModel m, MilkshakeController c){
        display = new VBox();
        model = m;
        ctrl = c;
        model.addSubscriber(this);
        viewNotify();
    }

    public VBox getDisplay(){
        return display;
    }

    @Override
    public void viewNotify() {
       HashMap<String, Integer> flavours = model.getFlavours();
       display.getChildren().clear();
       for(String item : flavours.keySet()){
           display.getChildren().add(new Label(flavours.get(item) + " x " + item));
       }
       HashMap<String, Integer> toppings = model.getToppings();
        for(String item : toppings.keySet()){
            display.getChildren().add(new Label(toppings.get(item) + " x " + item));
        }
       display.getChildren().add(new Label("Total = $" + model.getTotal()));
    }
}

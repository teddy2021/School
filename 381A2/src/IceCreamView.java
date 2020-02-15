import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.LinkedList;

public class IceCreamView extends Pane implements ModelListener{

    private GridPane space;
    private MilkshakeController controller;
    private MilkshakeModel model;
    LinkedList<IceCreamSelection> items;

    public IceCreamView(MilkshakeController ctrl, MilkshakeModel m){
        space = new GridPane();
        space.setVgap(32);
        space.setHgap(32);

        model = m;
        items = new LinkedList<>();

        controller = ctrl;

        IceCreamSelection chocolate = new IceCreamSelection("Chocolate");
        IceCreamSelection vanilla = new IceCreamSelection("Vanilla");
        IceCreamSelection strawberry = new IceCreamSelection("Strawberry");
        IceCreamSelection lemon = new IceCreamSelection("Lemon");
        IceCreamSelection coffee = new IceCreamSelection("Coffee");
        IceCreamSelection mint = new IceCreamSelection("Mint");

        items.add(chocolate);
        items.add(vanilla);
        items.add(strawberry);
        items.add(lemon);
        items.add(coffee);
        items.add(mint);

        chocolate.getSlider().valueProperty().addListener(e -> ctrl.handleSlide("Chocolate", chocolate.getSlider().getValue()));
        vanilla.getSlider().valueProperty().addListener(e -> ctrl.handleSlide("Vanilla", vanilla.getSlider().getValue()));
        strawberry.getSlider().valueProperty().addListener(e -> ctrl.handleSlide("Strawberry", strawberry.getSlider().getValue()));
        lemon.getSlider().valueProperty().addListener(e->ctrl.handleSlide("Lemon", lemon.getSlider().getValue()));
        coffee.getSlider().valueProperty().addListener(e->ctrl.handleSlide("Coffee", coffee.getSlider().getValue()));
        mint.getSlider().valueProperty().addListener(e->ctrl.handleSlide("Mint", mint.getSlider().getValue()));

        space.add(chocolate.getBox(), 0, 0);
        space.add(vanilla.getBox(), 1, 0);
        space.add(strawberry.getBox(), 0,1 );
        space.add(lemon.getBox(), 1, 1);
        space.add(coffee.getBox(), 0, 2);
        space.add(mint.getBox(), 1, 2);

        model.addSubscriber(this);

    }

    public void setController(MilkshakeController control){
        controller = control;
    }

    public GridPane getSpace() {
        return space;
    }

    public void viewNotify(){
        HashMap<String, Integer> flavours = model.getFlavours();
        for(IceCreamSelection ic: items){
            ic.getSlider().adjustValue(flavours.get(ic.getFlavour()));
        }
    }


}

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class IceCreamView {

    private IceCreamSelection[] flavours;
    private GridPane space;
    private MilkshakeController controller;

    public IceCreamView(MilkshakeController ctrl){
        space = new GridPane();

        controller = ctrl;

        IceCreamSelection chocolate = new IceCreamSelection("Chocolate");
        IceCreamSelection vanilla = new IceCreamSelection("Vanilla");
        IceCreamSelection strawberry = new IceCreamSelection("Strawberry");
        IceCreamSelection lemon = new IceCreamSelection("Lemon");
        IceCreamSelection coffee = new IceCreamSelection("Coffee");
        IceCreamSelection mint = new IceCreamSelection("Mint");

        chocolate.getSlider().setOnDragDetected(e -> ctrl.handleSlide("Chocolate", chocolate.getSlider().getValue()));
        vanilla.getSlider().setOnDragDetected(e -> ctrl.handleSlide("Vanilla", vanilla.getSlider().getValue()));
        strawberry.getSlider().setOnDragDetected(e -> ctrl.handleSlide("Strawberry", strawberry.getSlider().getValue()));
        lemon.getSlider().setOnDragDetected(e->ctrl.handleSlide("Lemon", lemon.getSlider().getValue()));
        coffee.getSlider().setOnDragDetected(e->ctrl.handleSlide("Coffee", coffee.getSlider().getValue()));
        mint.getSlider().setOnDragDetected(e->ctrl.handleSlide("Mint", mint.getSlider().getValue()));

        space.add(chocolate, 0, 0);
        space.add(vanilla, 1, 0);
        space.add(strawberry, 0,1 );
        space.add(lemon, 1, 1);
        space.add(coffee, 0, 2);
        space.add(mint, 1, 2);

    }


}

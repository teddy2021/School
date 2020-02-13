import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;



public class ToppingSelection extends Node {
    private Button add, subtract;
    private Label topping;
    private VBox container;

    public ToppingSelection(String label){
        add = new Button("⇑");
        subtract = new Button("⇓");
        topping = new Label(label);
        container = new VBox();
        container.setSpacing(75);
        container.setVisible(true);
        container.getChildren().addAll(add, topping, subtract);
    }

    public Button getAdd(){
        return add;
    }

    public Button getSubtract(){
        return subtract;
    }

    public Label getLabel(){
        return topping;
    }
}

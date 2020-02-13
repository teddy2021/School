import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class IceCreamSelection extends Node {
    private Label label;
    private Slider slider;
    private VBox box;

    public IceCreamSelection(String flavour) {
        box = new VBox(label, slider);
        try {
            label.setText(flavour);
            slider.setMax(8);
            slider.setValue(0);
            slider.setSnapToTicks(true);
        } catch (Exception E) {
            System.out.println("Error in IceCreaSelection constructor: " + E);
            E.printStackTrace();
        }
    }

    public Slider getSlider() {
        return slider;
    }

    public String getFlavour(){
        return label.getText();
    }
}



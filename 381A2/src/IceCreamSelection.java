import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class IceCreamSelection{
    private Label label;
    private Slider slider;
    private VBox box;

    public IceCreamSelection(String flavour) {
        label = new Label(flavour);
        slider = new Slider();
        slider.setMax(8);
        slider.setValue(0);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(8);
        slider.setShowTickMarks(true);
        box = new VBox(label, slider);
    }

    public Slider getSlider() {
        return slider;
    }

    public String getFlavour(){
        return label.getText();
    }

    public VBox getBox(){
        return box;
    }
}



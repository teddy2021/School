
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application{
    public void start(Stage s){

        MilkshakeModel model = new MilkshakeModel();
        MilkshakeController control = new MilkshakeController();
        control.setShake(model);

        IceCreamView ice_cream = new IceCreamView(control, model);
        ToppingsView toppings = new ToppingsView(control);

        SummaryView text = new SummaryView(model, control);
        HBox layout = new HBox();
        layout.getChildren().addAll(ice_cream.getSpace(), text.getDisplay(), toppings.getSpace());

        model.addSubscriber(ice_cream);
        model.addSubscriber(toppings);

        s.setTitle("Milkshake");
        s.setScene(new Scene(layout, 1200, 800));
        s.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}


import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
    public void start(Stage s){

        MilkshakeModel model = new MilkshakeModel();
        MilkshakeController control = new MilkshakeController();
        control.setShake(model);

    }
}


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application{
    public void start(Stage s){

        MilkshakeModel model = new MilkshakeModel();
        MilkshakeController control = new MilkshakeController();
        control.setShake(model);

        GridPane layout = new GridPane();

       BorderPane left = new BorderPane();
       BorderPane center = new BorderPane();
       BorderPane right = new BorderPane();

        Button help = new Button();
        help.setText("Help");
        help.setMinWidth(30);
        help.setMaxWidth(60);
        help.setPrefWidth(45);
        help.setMinHeight(20);
        help.setMaxHeight(40);
        help.setPrefHeight(30);

        IceCreamView ice_cream = new IceCreamView(control, model);

        Button pay = new Button();
        pay.setText("Pay");
        pay.setOnAction(e->control.reset());
        pay.setMinWidth(30);
        pay.setMaxWidth(60);
        pay.setPrefWidth(45);
        pay.setMinHeight(20);
        pay.setMaxHeight(40);
        pay.setPrefHeight(30);

        SummaryView text = new SummaryView(model, control);

        PictureView graphics = new PictureView(model, 1200/4, 800/2 );

        ToppingsView toppings = new ToppingsView(control);

        Button exit = new Button();
        exit.setText("Exit");
        exit.setOnAction(e->control.reset());
        exit.setMinWidth(30);
        exit.setMaxWidth(60);
        exit.setPrefWidth(45);
        exit.setMinHeight(20);
        exit.setMaxHeight(40);
        exit.setPrefHeight(30);

        model.addSubscriber(ice_cream);
        model.addSubscriber(toppings);

        left.setTop(help);
        left.setCenter(ice_cream);
        left.setBottom(pay);

        center.setTop(graphics);
        center.setBottom(text);

        right.setTop(toppings);
        right.setBottom(exit);

        layout.add(left, 00,0);
        layout.add(center, 1,0);
        layout.add(right, 2,0);

        layout.setPrefHeight(s.getHeight());
        layout.setMinHeight(s.getHeight());
        layout.setMaxHeight(s.getHeight());
        layout.setPrefHeight(s.getWidth());
        layout.setMinWidth(s.getWidth());
        layout.setMaxWidth(s.getWidth());

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 1200, 800, Color.NAVY);

        layout.setHgap(scene.getWidth()/10);
        s.widthProperty().addListener(e->{
         layout.setHgap(s.getWidth()/10);
         layout.setPrefWidth(s.getWidth());
         graphics.setWidth(s.getWidth()/4);
        });

        s.heightProperty().addListener(e->{
         layout.setPrefWidth(s.getHeight());
         graphics.setHeight(s.getHeight()/2);
        });
        s.setTitle("Milkshake");
        s.setScene(scene);
        s.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}

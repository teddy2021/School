package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.management.StringValueExp;
import javax.sound.midi.SysexMessage;
import java.lang.management.ClassLoadingMXBean;
import java.util.LinkedList;

public class Main extends Application {



    final int hspacing = 15;
    final int vspacing = 15;
    protected Scene primary;
    protected Stage secondary;
    protected Stage tertiary;



    public HBox addSelection(String s){


        Label amount = new Label("0");

        Button uone = new Button("+1");
        uone.setOnAction(e -> amount.setText(String.valueOf(
                Integer.parseInt(
                        amount.getText()) + 1)));

        Button ufive = new Button("+5");
        ufive.setOnAction(e -> amount.setText(String.valueOf(
                Integer.parseInt(
                        amount.getText()) + 5)));

        HBox incrimentors = new HBox(hspacing, uone, ufive);


        Button other = new Button("Other amount");
        other.setOnAction(e->showNumPad());

        HBox info = new HBox(hspacing, other, amount);

        Button done = new Button("-1");
        done.setOnAction(e -> {
            int val = Integer.parseInt(amount.getText());
            if(val - 1 >= 0) {
                val -= 1;
                amount.setText(String.valueOf(val));
            }
            else{
                amount.setText("0");
            }
        });

        Button dfive = new Button("-5");
        dfive.setOnAction(e -> {
            int val = Integer.parseInt(amount.getText());
            if(val - 5 >= 0){
                val -= 5;
                amount.setText(String.valueOf(val));
            }
            else{
                amount.setText("0");
            }
        });

        HBox decrimentors = new HBox(hspacing, done, dfive);


        VBox counter = new VBox(vspacing, incrimentors, info, decrimentors);

        Button small = new Button("Sm");
        Button medium = new Button("Md");
        Button large = new Button("Lg");

        small.setOnAction(e->{
            small.setDisable(true);
            medium.setDisable(false);
            large.setDisable(false);
        });

        medium.setOnAction(e->{
            small.setDisable(false);
            medium.setDisable(true);
            large.setDisable(false);
        });
        large.setOnAction(e->{
            small.setDisable(false);
            medium.setDisable(false);
            large.setDisable(true);
        });

        HBox sizes = new HBox(small, medium, large);

        Label type = new Label(s);

        Button customize = new Button("Customize");
        customize.setOnAction(e->showCustomize());
        VBox custom = new VBox(customize);

        HBox container = new HBox(2*hspacing, counter, sizes, type, custom);


        return container;
    }


    public void start(Stage primaryStage) throws Exception{

        HBox vanilla = addSelection("Vanilla");
        HBox chocolate = addSelection("Chocolate");
        HBox strawberry = addSelection("Strawberry");
        HBox mint = addSelection("Mint");
        VBox display = new VBox(75, vanilla, chocolate, strawberry, mint, new Button("Pay"));
        primary = new Scene(display, 1600, 900);

        secondary = new Stage();
        secondary.initModality(Modality.APPLICATION_MODAL);
        secondary.initOwner(primaryStage);

        Button seven = new Button("7");
        Button four = new Button("4");
        Button one = new Button("1");
        VBox sev_four_one = new VBox(seven, four, one);

        Button eight = new Button("8");
        Button five = new Button("5");
        Button two = new Button("2");
        Button zero = new Button("0");
        VBox eight_five_two_zero = new VBox(eight, five, two, zero);

        Button nine = new Button("9");
        Button six = new Button("6");
        Button three = new Button("3");
        Button ret = new Button("↩");
        ret.setOnAction(e->hideNumPad());
        VBox nine_six_three = new VBox(nine, six, three, ret);



        Label amount = new Label("");
        HBox columns = new HBox(sev_four_one, eight_five_two_zero, nine_six_three);

        seven.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("7");
            }
            else{
                amount.setText((amount.getText().concat("7")));
            }
        });
        four.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("4");
            }
            else{
                amount.setText((amount.getText().concat("4")));
            }
        });
        one.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("1");
            }
            else{
                amount.setText((amount.getText().concat("1")));
            }
        });
        eight.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("8");
            }
            else{
                amount.setText((amount.getText().concat("8")));
            }
        });
        five.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("5");
            }
            else{
                amount.setText((amount.getText().concat("5")));
            }
        });
        two.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("2");
            }
            else{
                amount.setText((amount.getText().concat("2")));
            }
        });
        zero.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("0");
            }
            else{
                amount.setText((amount.getText().concat("0")));
            }
        });
        nine.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("9");
            }
            else{
                amount.setText((amount.getText().concat("9")));
            }
        });
        six.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("6");
            }
            else{
                amount.setText((amount.getText().concat("6")));
            }
        });
        three.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("3");
            }
            else{
                amount.setText((amount.getText().concat("3")));
            }
        });
        one.setOnAction(e->{
            if("".compareTo(amount.getText()) == 0){
                amount.setText("1");
            }
            else{
                amount.setText((amount.getText().concat("1")));
            }
        });

        VBox customAmount = new VBox(amount, columns);

        Scene amountPopup = new Scene(customAmount, 150,120);
        secondary.setScene(amountPopup);


        Button back = new Button("←");
        back.setOnAction(e->hideCustomize());

        Label image = new Label("Milkshake picture placeholder");

        Button van = new Button("Vanilla");
        Button choc = new Button("Chocolate");
        Button straw = new Button("Strawberry");
        Button minnt = new Button("Mint");
        HBox options = new HBox(van, choc, straw, minnt);

        Button more = new Button("↑");
        Label amnt = new Label("0");
        Button less = new Button("↓");

        VBox quantity = new VBox(more, amnt, less);

        van.setOnAction(e->amnt.setText("0"));
        choc.setOnAction(e->amnt.setText("0"));
        straw.setOnAction(e->amnt.setText("0"));
        minnt.setOnAction(e->amnt.setText("0"));

        more.setOnAction(e->{
            int val = Integer.parseInt(amnt.getText());
            amnt.setText(String.valueOf(val + 1));
        });

        less.setOnAction(e->{
            int val = Integer.parseInt(amnt.getText());
            if(val - 1 <= 0){
                amnt.setText("0");
            }
            else{
                amnt.setText(String.valueOf(val - 1));
            }
        });

        VBox layout = new VBox(back, image, options, quantity);

        Scene customize = new Scene(layout, 300, 300);

        tertiary = new Stage();
        tertiary.setScene(customize);
        tertiary.initModality(Modality.APPLICATION_MODAL);
        tertiary.initOwner(primaryStage);

        primaryStage.setScene(primary);
        primaryStage.show();
    }

    private void showNumPad(){
        secondary.show();
    }

    private void hideNumPad(){
        secondary.hide();
    }

    private void showCustomize(){
        tertiary.show();
    }

    private void hideCustomize(){
        tertiary.hide();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

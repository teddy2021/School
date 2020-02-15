import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class PictureView extends Pane implements ModelListener{

    double width, height;
    MilkshakeModel model;
    Canvas display;
    GraphicsContext context;
    HashMap<String, Image> cream_images;

    public PictureView(MilkshakeModel m, int width, int height){
        model = m;
        model.addSubscriber(this);

        cream_images = new HashMap<>();
        ClassLoader cl = getClass().getClassLoader();
        try {
            cream_images.put("Vanilla",new Image("file:Images/Vanilla.jpeg"));
            cream_images.put("Chocolate", new Image("file:Images/Chocolate.jpg"));
            cream_images.put("Strawberry", new Image("file:Images/Stawberry.jpg"));
            cream_images.put("Lemon", new Image("file:Images/Lemon.jpg"));
            cream_images.put("Coffee", new Image("file:Images/Coffee.jpg"));
            cream_images.put("Mint", new Image("file:Images/Mint.jpg"));
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }


        this.width = width;
        this.height = height;

        display = new Canvas(width, height);
        context = display.getGraphicsContext2D();
        getChildren().add(display);
        draw();
    }

    public void draw(){
        double max_flavour_height = 3*height/4;
        double flavour_sizechange = height/48;
        double min_flavour_height = 3*height/8;

        double min_topping_height = max_flavour_height;
        double max_topping_height = 7*height/8;

        double min_draw_width = width/8;
        double max_draw_width = 7*width/8;


        String[] options = new String[6];

        context.clearRect(0,0,width, height);
        context.setFill(Color.WHITE);
        context.fillRect(0,0,width, height); // background
        context.setFill(Color.NAVY);


        context.fillRect(0,0, min_draw_width, height); // left
        context.fillRect(max_draw_width, 0, width, height); // right
        context.fillRect(0, min_flavour_height, width, height); // bottom
        context.fillRect(0,0, width, max_topping_height); // top

        //flavours
        HashMap<String, Integer> flavours = model.getFlavours();
        String flavour;
        options = flavours.keySet().toArray(options);
        double used_flavour_draw = min_flavour_height;
        context.setFill(Color.RED);
        for(int i = 0; i < flavours.keySet().size(); i += 1){
            System.out.println("Min height: " + used_flavour_draw);
            flavour = options[i];
            double cheight = flavour_sizechange * flavours.get(flavour);
            //context.drawImage(cream_images.get(flavour), min_draw_width, min_flavour_height + used_flavour_draw +cheight, max_draw_width, min_flavour_height + cheight);
            context.fillRect(min_draw_width,  used_flavour_draw + cheight, max_draw_width, used_flavour_draw);
            used_flavour_draw += cheight;
        }
        context.setFill(Color.NAVY);

        // toppings
        HashMap<String, Integer> toppings = model.getToppings();
        String topping;
        options = toppings.keySet().toArray(options);
        for(int i = 0; i < options.length; i += 1 ){
            topping = options[i];
        }

    }

    @Override
    public void viewNotify() {
        System.out.println("\nDing!\n");
        draw();
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
        display.setWidth(width);
        draw();
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
        display.setHeight(height);
        draw();
    }
}

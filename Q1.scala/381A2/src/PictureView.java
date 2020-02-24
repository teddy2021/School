import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class PictureView extends Pane implements ModelListener{

    double width, height;
    MilkshakeModel model;
    Canvas display;
    GraphicsContext context;
    HashMap<String, Image> cream_images;
    HashMap<String, Image> topping_images;

    public PictureView(MilkshakeModel m, int width, int height){
        model = m;
        model.addSubscriber(this);

        cream_images = new HashMap<>();
        topping_images = new HashMap<>();
        try {

            cream_images.put("Vanilla",new Image("file:Images/Vanilla.jpeg"));
            cream_images.put("Chocolate", new Image("file:Images/Chocolate.jpg"));
            cream_images.put("Strawberry", new Image("file:Images/Strawberry.jpg"));
            cream_images.put("Lemon", new Image("file:Images/Lemon.jpg"));
            cream_images.put("Coffee", new Image("file:Images/Coffee.jpg"));
            cream_images.put("Mint", new Image("file:Images/Mint.jpg"));

            topping_images.put("Cherries", new Image("file:Images/Cherries.jpg"));
            topping_images.put("Chocolate_chips", new Image("file:Images/Chocolate_chps.jpg"));
            topping_images.put("Coconut", new Image("file:Images/Coconut.jpg"));
            topping_images.put("Marshmallows", new Image("file:Images/Marshmallows.jpg"));
            topping_images.put("Whipped Cream", new Image("file:Images/WhippedCream.jpg"));
            topping_images.put("Sprinkles", new Image("file:Images/Sprinkles.jpg"));
        }
        catch (Exception e){
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
        double max_flavour_height = height/4;
        double min_flavour_height = 7*height/8;
        double flavour_sizechange = (max_flavour_height - min_flavour_height)/8;

        double max_topping_height = height/8;
        double min_topping_height = max_topping_height - (height/10);

        double min_draw_width = width/8;
        double max_draw_width = 7*width/8;

        double cup_Width = max_draw_width - min_draw_width;
        double cup_height = max_topping_height - min_flavour_height;

        double topping_size_change = cup_Width / 8;
        double topping_height = max_topping_height - min_topping_height;

        String[] options = new String[6];

        // clear canvas
        context.clearRect(0,0,width, height);
        // fill background
        context.setFill(Color.NAVY);
        context.fillRect(0,0,width, height); // background

        // setup cup shape
        context.setFill(Color.WHITE);
        context.fillRect(min_draw_width, min_flavour_height, cup_Width, cup_height);


        //flavours
        HashMap<String, Integer> flavours = model.getFlavours();
        String flavour;
        options = flavours.keySet().toArray(options);

        double used_flavour_draw = min_flavour_height;

        context.setFill(Color.RED);
        for(int i = 0; i < flavours.keySet().size(); i += 1){
            flavour = options[i];
            double cheight = flavour_sizechange * flavours.get(flavour);
            context.drawImage(cream_images.get(flavour), min_draw_width, used_flavour_draw, cup_Width, cheight);
            //context.fillRect(min_draw_width,  used_flavour_draw, recWidth, cheight);
            used_flavour_draw += cheight;
        }
        context.setFill(Color.NAVY);

        // toppings
        HashMap<String, Integer> toppings = model.getToppings();
        String topping;
        options = toppings.keySet().toArray(options);
        double used_topping_draw = min_draw_width;
        for(int i = 0; i < options.length; i += 1 ) {
            topping = options[i];
            double fwidth = topping_size_change * toppings.get(topping);
            context.drawImage(topping_images.get(topping), used_topping_draw, max_topping_height, fwidth, topping_height);
            used_topping_draw += fwidth;

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

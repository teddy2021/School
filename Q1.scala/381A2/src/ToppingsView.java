import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.util.LinkedList;

public class ToppingsView extends Pane implements ModelListener{

    private MilkshakeController ctrl;
    private GridPane space;
    MilkshakeModel shake;
    LinkedList<ToppingSelection> items;

    private void setEvents(ToppingSelection item){
        item.getAdd().setOnMouseClicked( e->ctrl.handleClick(1, e.getClickCount(), item.getLabel().getText()));
        item.getSubtract().setOnMouseClicked(e->ctrl.handleClick(-1, e.getClickCount(), item.getLabel().getText()));
    }

    public ToppingsView(MilkshakeController ctrl){
        this.ctrl = ctrl;
        space = new GridPane();
        space.setHgap(16);
        space.setVgap(32);
        items = new LinkedList<>();

        ToppingSelection sprinkles = new ToppingSelection("Sprinkles");
        ToppingSelection cherries = new ToppingSelection("Cherries");
        ToppingSelection c_chips = new ToppingSelection("Chocolate Chips");
        ToppingSelection w_cream = new ToppingSelection("Whipped Cream");
        ToppingSelection coconut = new ToppingSelection("Coconut");
        ToppingSelection marshmallows = new ToppingSelection("Marshmallows");

        items.add(sprinkles);
        items.add(cherries);
        items.add(c_chips);
        items.add(w_cream);
        items.add(coconut);
        items.add(marshmallows);

        setEvents(sprinkles);
        setEvents(cherries);
        setEvents(c_chips);
        setEvents(w_cream);
        setEvents(coconut);
        setEvents(marshmallows);

        space.add(sprinkles.getBox(), 0, 0);
        space.add(cherries.getBox(), 1, 0);
        space.add(c_chips.getBox(), 0, 1);
        space.add(w_cream.getBox(), 1, 1);
        space.add(coconut.getBox(), 0, 2);
        space.add(marshmallows.getBox(), 1, 2);

        getChildren().add(space);
    }

    public void setShakeModel(MilkshakeModel shake) {
        this.shake = shake;
    }

    public void setController(MilkshakeController ctrl){
        this.ctrl = ctrl;
    }

    public GridPane getSpace() {
        return space;
    }

    @Override
    public void viewNotify() {
    }
}

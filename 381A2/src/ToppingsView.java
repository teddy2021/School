import javafx.scene.layout.GridPane;

public class ToppingsView {

    private MilkshakeController ctrl;
    private GridPane space;

    private void setEvents(ToppingSelection item){
        item.getAdd().setOnMouseClicked( e->ctrl.handleClick(1,e.getClickCount(), item.getLabel().getText()));
        item.getAdd().setOnMouseClicked(e-> ctrl.handleClick(-1, e.getClickCount(), item.getLabel().getText()));
    }

    public ToppingsView(){
        space = new GridPane();
        ToppingSelection sprinkles = new ToppingSelection("Sprinkles");
        ToppingSelection cherries = new ToppingSelection("Cherries");
        ToppingSelection c_chips = new ToppingSelection("Chocolate Chips");
        ToppingSelection w_cream = new ToppingSelection("Whipped Cream");
        ToppingSelection coconut = new ToppingSelection("Coconut");
        ToppingSelection marshmallows = new ToppingSelection("Marshmallows");

        setEvents(sprinkles);
        setEvents(cherries);
        setEvents(c_chips);
        setEvents(w_cream);
        setEvents(coconut);
        setEvents(marshmallows);

        space.add(sprinkles, 0, 0);
        space.add(cherries, 1, 0);
        space.add(c_chips, 0, 1);
        space.add(w_cream, 1, 1);
        space.add(coconut, 0, 2);
        space.add(marshmallows, 1, 2);
    }
}

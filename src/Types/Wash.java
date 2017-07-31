package Types;

/**
 * Created by julieglasdam on 12/07/2017.
 */
public class Wash {

    private String name = "Wash";
    private int price = 0;

    public Wash() {
    }

    public Wash(int newPrice) {
        price = newPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
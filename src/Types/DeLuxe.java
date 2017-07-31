package Types;

/**
 * Created by julieglasdam on 12/07/2017.
 */

public class DeLuxe extends Wash {

    private String name = "De Luxe Vask";
    private int price = 0;

    public DeLuxe() {
    }

    public DeLuxe(int newPrice){
        price = newPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}

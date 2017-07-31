package Types;

/**
 * Created by julieglasdam on 12/07/2017.
 */

public class Economy extends Wash {

    private String name = "Economy Vask";
    private int price = 0;

    public Economy() {
    }

    public Economy(int newPrice){
        price = newPrice;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


}

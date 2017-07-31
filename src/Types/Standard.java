package Types;

/**
 * Created by julieglasdam on 12/07/2017.
 */
public class Standard extends Wash {

    private String name = "Standard Vask";
    private int price = 0;

    public Standard() {
    }

    public Standard(int newPrice){
        price = newPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setWash(int newWash) {
        price = newWash;
    }



}

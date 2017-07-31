/**
 * Created by julieglasdam on 12/07/2017.
 */
public class User {
    private int id;
    private int saldo;

    public User() {
    }

    public User(int id2, int saldo2) {
        id = id2;
        saldo = saldo2;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo (int price){
        saldo = saldo - price;
    }

}

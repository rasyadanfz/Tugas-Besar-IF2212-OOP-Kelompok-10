package src.Ingredient;
import java.util.*;

public abstract class Ingredient implements Purchaseable{
    private String nama;
    private int price;
    private int kekenyangan;

    public Ingredient(String nama, int price, int kekenyangan){
        this.nama = nama;
        this.price = price;
        this.kekenyangan = kekenyangan;
    }
    //getter
    public String getNama(){
        return nama;
    }
    public int getPrice(){
        return price;
    }
    public int getKekenyangan(){
        return kekenyangan;
    }
}

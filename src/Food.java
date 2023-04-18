package src;

import java.util.ArrayList;

public abstract class Food {
    private String nama;
    private int kekenyangan;
    private ArrayList<Ingredient> bahanMakanan;

    public Food(String nama, int kekenyangan, ArrayList<Ingredient> bahanMakanan) {
        this.nama = nama;
        this.kekenyangan = kekenyangan;
        this.bahanMakanan = bahanMakanan;
    }
}

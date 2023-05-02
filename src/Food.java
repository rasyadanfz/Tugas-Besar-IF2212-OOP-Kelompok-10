package src;

import java.util.ArrayList;

public class Food extends Item implements Eatable {
    private String nama;
    private int kekenyangan;
    private ArrayList<Ingredient> bahanMakanan;

    public Food(String nama) {
        this.nama = nama.toLowerCase();
        if (nama.toLowerCase().equals("nasi ayam")) {
            kekenyangan = 16;
            bahanMakanan.add(new Ingredient("nasi"));
            bahanMakanan.add(new Ingredient("ayam"));
        } else if (nama.toLowerCase().equals("nasi kari")) {
            kekenyangan = 30;
            bahanMakanan.add(new Ingredient("nasi"));
            bahanMakanan.add(new Ingredient("kentang"));
            bahanMakanan.add(new Ingredient("wortel"));
            bahanMakanan.add(new Ingredient("sapi"));
        } else if (nama.toLowerCase().equals("susu kacang")) {
            kekenyangan = 5;
            bahanMakanan.add(new Ingredient("susu"));
            bahanMakanan.add(new Ingredient("kacang"));
        } else if (nama.toLowerCase().equals("tumis sayur")) {
            kekenyangan = 5;
            bahanMakanan.add(new Ingredient("wortel"));
            bahanMakanan.add(new Ingredient("bayam"));
        } else if (nama.toLowerCase().equals("bistik")) {
            kekenyangan = 22;
            bahanMakanan.add(new Ingredient("kentang"));
            bahanMakanan.add(new Ingredient("sapi"));
        }
    }

    // getter
    public String getNama() {
        return nama;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public ArrayList<Ingredient> getBahanMakanan() {
        return bahanMakanan;
    }

    // dari interface
    public void eat(Sim sim) {
        sim.changeKekenyangan(getKekenyangan());
    }
}
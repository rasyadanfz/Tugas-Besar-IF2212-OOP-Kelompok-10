package com.Kelompok10;

import java.util.ArrayList;

public class Food extends Item implements Eatable {
    private String nama;
    private int kekenyangan;
    private ArrayList<Ingredient> bahanMakanan;

    public Food(String nama) {
        this.nama = nama.toLowerCase();
        bahanMakanan = new ArrayList<Ingredient>();
        if (nama.toLowerCase().equals("nasi ayam")) {
            kekenyangan = 16;
            bahanMakanan.add(new Ingredient("Nasi"));
            bahanMakanan.add(new Ingredient("Ayam"));
        } else if (nama.toLowerCase().equals("nasi kari")) {
            kekenyangan = 30;
            bahanMakanan.add(new Ingredient("Nasi"));
            bahanMakanan.add(new Ingredient("Kentang"));
            bahanMakanan.add(new Ingredient("Wortel"));
            bahanMakanan.add(new Ingredient("Sapi"));
        } else if (nama.toLowerCase().equals("susu kacang")) {
            kekenyangan = 5;
            bahanMakanan.add(new Ingredient("Susu"));
            bahanMakanan.add(new Ingredient("Kacang"));
        } else if (nama.toLowerCase().equals("tumis sayur")) {
            kekenyangan = 5;
            bahanMakanan.add(new Ingredient("Wortel"));
            bahanMakanan.add(new Ingredient("Bayam"));
        } else if (nama.toLowerCase().equals("bistik")) {
            kekenyangan = 22;
            bahanMakanan.add(new Ingredient("Kentang"));
            bahanMakanan.add(new Ingredient("Sapi"));
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
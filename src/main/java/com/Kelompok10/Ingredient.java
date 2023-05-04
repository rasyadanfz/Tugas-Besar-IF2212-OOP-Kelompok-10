package com.Kelompok10;

import java.util.*;

import com.Kelompok10.Thing.Jam;

public class Ingredient extends Item implements Purchaseable, Eatable {
    private String nama;
    private int price;
    private int kekenyangan;

    public Ingredient(String nama) {
        if (nama.toLowerCase().equals("nasi")) {
            price = 5;
            kekenyangan = 5;
        } else if (nama.toLowerCase().equals("kentang")) {
            price = 3;
            kekenyangan = 4;
        } else if (nama.toLowerCase().equals("ayam")) {
            price = 10;
            kekenyangan = 8;
        } else if (nama.toLowerCase().equals("sapi")) {
            price = 12;
            kekenyangan = 15;
        } else if (nama.toLowerCase().equals("wortel")) {
            price = 3;
            kekenyangan = 2;
        } else if (nama.toLowerCase().equals("bayam")) {
            price = 3;
            kekenyangan = 2;
        } else if (nama.toLowerCase().equals("kacang")) {
            price = 2;
            kekenyangan = 2;
        } else if (nama.toLowerCase().equals("susu")) {
            price = 2;
            kekenyangan = 1;
        }
    }

    // getter
    public String getNama() {
        return nama;
    }

    public int getPrice() {
        return price;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    // dari interface
    // masih belum dicoba
    public void buyItem() {
        Random rand = new Random();
        int int_random = rand.nextInt(4) + 1;
        int waktuPengiriman = int_random * 30 * 1000;
        Thread t = new Thread() {
            public void run() {
                boolean pengiriman = true;
                int waktuMulai = Main.getCurrentTime();
                // Jam.ambilSisaWaktuKirim(waktuMulai, waktuPengiriman);
                while (pengiriman) {
                    if (waktuMulai + waktuPengiriman >= Main.getCurrentTime()) {
                        pengiriman = false;
                    }
                }
            }
        };
        t.start();
    }

    public void eat(Sim sim) {
        sim.changeKekenyangan(getKekenyangan());
    }
}

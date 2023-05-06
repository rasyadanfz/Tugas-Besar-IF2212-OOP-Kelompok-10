package src.main.java.com.Kelompok10;

import java.util.*;

import src.main.java.com.Kelompok10.Thing.Jam;

public class Ingredient extends Item implements Purchaseable, Eatable {
    private String nama;
    private int price;
    private int kekenyangan;

    public Ingredient(String nama) {
        if (nama.toLowerCase().equals("nasi")) {
            price = 5;
            kekenyangan = 5;
            this.nama = nama;
        } else if (nama.toLowerCase().equals("kentang")) {
            price = 3;
            kekenyangan = 4;
            this.nama = nama;
        } else if (nama.toLowerCase().equals("ayam")) {
            price = 10;
            kekenyangan = 8;
            this.nama = nama;
        } else if (nama.toLowerCase().equals("sapi")) {
            price = 12;
            kekenyangan = 15;
            this.nama = nama;
        } else if (nama.toLowerCase().equals("wortel")) {
            price = 3;
            kekenyangan = 2;
            this.nama = nama;
        } else if (nama.toLowerCase().equals("bayam")) {
            price = 3;
            kekenyangan = 2;
            this.nama = nama;
        } else if (nama.toLowerCase().equals("kacang")) {
            price = 2;
            kekenyangan = 2;
            this.nama = nama;
        } else if (nama.toLowerCase().equals("susu")) {
            price = 2;
            kekenyangan = 1;
            this.nama = nama;
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
        // int waktuPengiriman = int_random * 30 * 1000;
        // TODO: Hapus 1 line di bawah, cuma buat debug
        int waktuPengiriman = Timer.getTimer().getTotalTime() + 10;
        boolean pengiriman = true;
        int waktuMulai = Timer.getTimer().getTotalTime();
        GameManager.getGameManager().getActiveSim().setItemDelivery(
                new Delivery(waktuPengiriman, waktuMulai + waktuPengiriman - Timer.getTimer().getTotalTime(), nama));
        while (pengiriman) {
            if (waktuMulai + waktuPengiriman <= Timer.getTimer().getTotalTime()) {
                pengiriman = false;
            }
            GameManager.getGameManager().getActiveSim()
                    .getItemDelivery()
                    .setRemainingDuration(waktuMulai + waktuPengiriman - Timer.getTimer().getTotalTime());
        }
    }

    public void eat(Sim sim) {
        sim.changeKekenyangan(getKekenyangan());
    }
}

package src.Thing;

import src.*;

public abstract class Kompor extends ActiveItems implements Cook {
    public Kompor(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public Kompor(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public void Cooking(Sim sim, String namaMakanan) {
        Food food = new Food(namaMakanan);
        int duration = (int) (1.5 * food.getKekenyangan());
        sim.addAction(new Action("cooking", duration, this));
        sim.setStatus("active");
        while (duration > 0) {
            sim.changeMood(10);
            duration--;
        }
    }

    public boolean checkBahanMasak(Inventory inventory) {
        boolean bisaMasak = false;
        System.out.println("Berikut adalah daftar makanan yang bisa dimasak: ");
        if (inventory.containsItem("nasi") && inventory.containsItem("ayam")) {
            System.out.println("- Nasi Ayam");
            bisaMasak = true;
        }
        if (inventory.containsItem("nasi") && inventory.containsItem("kentang") && inventory.containsItem("wortel")
                && inventory.containsItem("sapi")) {
            System.out.println("- Nasi Kari");
            bisaMasak = true;
        }
        if (inventory.containsItem("susu") && inventory.containsItem("kacang")) {
            System.out.println("- Susu Kacang");
            bisaMasak = true;
        }
        if (inventory.containsItem("wortel") && inventory.containsItem("bayam")) {
            System.out.println("- Tumis Sayur");
            bisaMasak = true;
        }
        if (inventory.containsItem("kentang") && inventory.containsItem("sapi")) {
            System.out.println("- Bistik");
            bisaMasak = true;
        }
        return bisaMasak;
    }
}
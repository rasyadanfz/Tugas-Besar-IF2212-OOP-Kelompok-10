package src.Thing;

import src.*;

public class Kompor extends ActiveItems implements Cook {
    public Kompor(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public Kompor(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public void Cooking(Sim sim, String namaMakanan) {
        Food food = new Food(namaMakanan);
        int duration = (int) (1.5 * food.getKekenyangan());
        Action actionCook = new Action("cooking", duration, this);
        sim.addAction(actionCook);
        sim.setStatus("active");
        sim.getInventory().addItem(food);
        effect(sim, actionCook);
    }

    public void effect(Sim sim, Action action) {
        while (action.getDurationLeft() > 0) {
            sim.changeMood(10);
            sim.decreaseActionDuration(action);
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

        if (!bisaMasak) {
            System.out.println("Tidak ada makanan yang bisa dimasak");
        }
        return bisaMasak;
    }
}
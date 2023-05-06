package com.Kelompok10.Thing;

import com.Kelompok10.*;

public class Kompor extends ActiveItems implements Cook {
    public Kompor(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public Kompor(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public void Cooking(Sim sim, String namaMakanan) {
        try {
            Food food = new Food(namaMakanan);
            int duration = (int) (1.5 * food.getKekenyangan());
            Action actionCook = new Action("cooking", duration, this);
            sim.addAction(actionCook);
            sim.setStatus("active");
            sim.setInActiveAction(true);
            effect(sim, actionCook);
            sim.getInventory().addItem(food);
            for (Ingredient ing : food.getBahanMakanan()) {
                sim.getInventory().removeItem(ing.getNama());
            }
            sim.incNotSleepYet(duration);
            if (sim.getHaveEat())
                sim.incNotPeeYet(duration);
            sim.getNegativeEffect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void effect(Sim sim, Action action) {
        System.out.print("Sisa durasi: ");
        while (action.getDurationLeft() > 0) {
            int printDuration = action.getDurationLeft() - 1;
            if (printDuration < 10) {
                System.out.print("00" + printDuration);
            } else if (printDuration < 100) {
                System.out.print("0" + printDuration);
            } else {
                System.out.print(printDuration);
            }
            if (printDuration != 0) {
                System.out.print("\b\b\b");
            }
            try {
                sim.decreaseActionDuration(action);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        System.out.println();
        sim.changeMood(10);
    }

    public boolean checkBahanMasak(Inventory<Item> inventory) {
        boolean bisaMasak = false;
        System.out.println("Berikut adalah daftar makanan yang bisa dimasak: ");
        if (inventory.containsItem("Nasi") && inventory.containsItem("Ayam")) {
            System.out.println("- Nasi Ayam");
            bisaMasak = true;
        }
        if (inventory.containsItem("Nasi") && inventory.containsItem("Kentang") && inventory.containsItem("Wortel")
                && inventory.containsItem("sapi")) {
            System.out.println("- Nasi Kari");
            bisaMasak = true;
        }
        if (inventory.containsItem("Susu") && inventory.containsItem("Kacang")) {
            System.out.println("- Susu Kacang");
            bisaMasak = true;
        }
        if (inventory.containsItem("Wortel") && inventory.containsItem("Bayam")) {
            System.out.println("- Tumis Sayur");
            bisaMasak = true;
        }
        if (inventory.containsItem("Kentang") && inventory.containsItem("Sapi")) {
            System.out.println("- Bistik");
            bisaMasak = true;
        }

        if (!bisaMasak) {
            System.out.println("Tidak ada makanan yang bisa dimasak");
        }
        return bisaMasak;
    }
}
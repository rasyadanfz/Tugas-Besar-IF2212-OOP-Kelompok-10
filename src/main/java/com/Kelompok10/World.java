package com.Kelompok10;

import java.util.*;

public class World {
    private Matrix map;
    private ArrayList<House> daftarRumah;
    private Timer timer;

    private static World world = new World();

    // Design Pattern Singleton : membuat konstruktor menjadi private
    private World() {
        map = new Matrix(64, 64);
        daftarRumah = new ArrayList<House>();
        timer = Timer.getTimer();
    }

    public Matrix getMap() {
        return map;
    }

    public ArrayList<House> getDaftarRumah() {
        return daftarRumah;
    }

    public static World getWorld() {
        return world;
    }

    public Timer getTimer() {
        return timer;
    }

    public void addHouse(int x, int y, String kodeRumah) throws Exception {
        if (map.getWorldItem(x, y).equals("----")) {
            House rumah = new House(kodeRumah, x, y);
            map.changeWorldItem(x, y, kodeRumah);
            daftarRumah.add(rumah);
        } else {
            throw new Exception("Gagal menambahkan rumah! Lokasi (" + x + ", " + y + ") sudah diisi oleh rumah lain!");
        }
    }

    public House getHouse(String kodeRumah) {
        Iterator<House> iterator = daftarRumah.iterator();
        House targetHouse = null;
        // Kalau ada rumah dengan kode yang ditentukan, return reference to Rumah itu
        // else, throw HouseNotFoundException
        while (iterator.hasNext()) {
            targetHouse = iterator.next();
            if (targetHouse.getKodeRumah().equals(kodeRumah)) {
                return targetHouse;
            }
        }
        return targetHouse;
    }
}
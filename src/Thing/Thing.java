package src.Thing;

import java.util.*;
import src.*;

public abstract class Thing extends Item implements Purchaseable {
    private String nama;
    private String kode;
    private int panjang;
    private int lebar;
    private int harga;

    // konstruktor
    public Thing(String nama, String kode, int panjang, int lebar, int harga) {
        this.nama = nama;
        this.kode = kode;
        this.panjang = panjang;
        this.lebar = lebar;
        this.harga = harga;
    }

    // getter
    public String getNama() {
        return nama;
    }

    public String getKode() {
        return kode;
    }

    public int getPanjang() {
        return panjang;
    }

    public int getLebar() {
        return lebar;
    }

    public int getHarga() {
        return harga;
    }

    // masih belum dicoba
    public void buyItem() {
        Random rand = new Random();
        int int_random = rand.nextInt(4) + 1;
        int waktuPengiriman = int_random * 30 * 1000;
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(waktuPengiriman);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        };
        t.start();
    }

    public void rotateItem() {
        int temp = panjang;
        panjang = lebar;
        lebar = temp;
    }
}

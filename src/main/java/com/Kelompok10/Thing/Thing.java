package com.Kelompok10.Thing;

import java.util.*;

import com.Kelompok10.*;
import com.Kelompok10.Timer;

public abstract class Thing extends Item implements Purchaseable {
    private String nama;
    private String kode;
    private int panjang;
    private int lebar;
    private int harga;
    private Point posisi;

    // konstruktor
    public Thing(String nama, String kode, int panjang, int lebar, int harga) {
        this.nama = nama;
        this.kode = kode;
        this.panjang = panjang;
        this.lebar = lebar;
        this.harga = harga;
    }

    public Thing(String nama, int panjang, int lebar, int harga) {
        this.nama = nama;
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

    public Point getPosisi() {
        return posisi;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setPosisi(int x, int y) {
        posisi = new Point(x, y);
    }

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

    public void rotateItem() {
        int temp = panjang;
        panjang = lebar;
        lebar = temp;
    }
}

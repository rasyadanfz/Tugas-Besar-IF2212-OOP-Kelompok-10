package com.Kelompok10.Thing;

public class ActiveItems extends Thing {
    public ActiveItems(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public ActiveItems(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }
}

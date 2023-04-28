package src.Thing;
import java.util.*;

public abstract class Thing implements Purchaseable {
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

    public void rotateItem() {
        int temp = panjang;
        panjang = lebar;
        lebar = temp;
    }
}

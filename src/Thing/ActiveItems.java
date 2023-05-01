package src.Thing;

import src.ActionEffect;

public abstract class ActiveItems extends Thing implements ActionEffect {
    public ActiveItems(String nama, String kodeItem, int panjang, int lebar, int harga) {
        super(nama, kodeItem, panjang, lebar, harga);
    }

    public ActiveItems(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

}

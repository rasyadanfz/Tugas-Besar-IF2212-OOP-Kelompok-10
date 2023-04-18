package src;
import java.util.*;
import java.awt.Point;

public class House {
    private String kodeRumah;
    private Matrix size;
    private ArrayList<Room> daftarRuangan;

    public House(String kodeRumah) {
        this.kodeRumah = kodeRumah;
        daftarRuangan = new ArrayList<>();
    }

    public String getKodeRumah() {
        return kodeRumah;
    }
}

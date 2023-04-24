package src;
import java.util.*;

public class House {
    private String kodeRumah;
    private ArrayList<Room> daftarRuangan;
    private Point lokasi;

    public House(String kodeRumah) {
        this.kodeRumah = kodeRumah;
        daftarRuangan = new ArrayList<>();
    }

    public String getKodeRumah() {
        return kodeRumah;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public ArrayList<Room> getSeluruhRuangan() {
        return daftarRuangan;
    }

    public void addRuangan(Room newruang){
        daftarRuangan.add(newruang);
    }
}

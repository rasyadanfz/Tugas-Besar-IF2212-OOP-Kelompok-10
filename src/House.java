package src;
import java.util.*;

public class House {
    private String kodeRumah;
    private Matrix petaRumah = new Matrix(9, 9);
    private ArrayList<Room> daftarRuangan;
    private Point lokasi;

    public House(String kodeRumah) {
        this.kodeRumah = kodeRumah;
        daftarRuangan = new ArrayList<>();

        Room firstRoom = new Room("R1", this);
        daftarRuangan.add(firstRoom);

        petaRumah.changeItem(5, 5, firstRoom.getNamaRuangan());
    }

    public String getKodeRumah() {
        return kodeRumah;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public void printPetaRumah() {
        petaRumah.printMatrix();
    }

    public ArrayList<Room> getSeluruhRuangan() {
        return daftarRuangan;
    }

    public void addRuangan(String newRoom, Room referenceRoom, String arah) {
        Room ruanganBaru = new Room(newRoom, this);
        //daftarRuangan.add(ruanganBaru);

        referenceRoom.addNewRoom(newRuang.getNamaRuangan(), arah);
        
    }

    public static void main(String[] args) {
        House rumah1 = new House("H1");
    }
}

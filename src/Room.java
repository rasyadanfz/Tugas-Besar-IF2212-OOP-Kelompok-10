package src;
import java.util.*;

public class Room {
    private String namaRuangan;
    private House rumah;
    private Matrix petaRuangan;
    private HashMap<String, String> roomSpace;
    private HashMap<String, Point> placedObject;

    public Room(String namaRuangan, House rumah) {
        this.namaRuangan = namaRuangan;
        this.rumah = rumah;
        petaRuangan = new Matrix(6, 6);
        placedObject = new HashMap<String, Point>();

        roomSpace = new HashMap<String, String>();
        roomSpace.put("kiri", "-");
        roomSpace.put("kanan", "-");
        roomSpace.put("atas", "-");
        roomSpace.put("bawah", "-");
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public House getHouse() {
        return rumah;
    }

    public void printPetaRuangan() {
        petaRuangan.printMatrix();
    }

    public boolean checkSpace(String arah) {
        boolean avail = false;
        if (roomSpace.get(arah) == "-") {
            avail = true;
        }
        return avail;
    }

    public void addRoom(Room ruangan, String arah) {
        if (!ruangan.getHouse().equals(rumah)) { // Nanti rencananya mau dibuat exception
            System.out.println("Gagal menambahkan ruangan: Ruangan tidak berada di dalam rumah yang sama!");
        } else {
            boolean available = checkSpace(arah);
            if (available) {
                roomSpace.replace(arah, "-", ruangan.getNamaRuangan());
            }
        }
    }
    
    // public static void main(String[] args) {
    //     House rumah1 = new House("H1");
    //     Room ruang1 = new Room("R1", rumah1);
    //     ruang1.printPetaRuangan();
    //     System.out.println(ruang1.roomSpace.toString());
    //     Room ruang2 = new Room("R2", rumah1);

    //     boolean available = ruang1.checkSpace("kiri");
    //     System.out.println(available);
    //     ruang1.addRoom(ruang2, "kiri");
    //     System.out.println(ruang1.roomSpace.toString());
    // }
}

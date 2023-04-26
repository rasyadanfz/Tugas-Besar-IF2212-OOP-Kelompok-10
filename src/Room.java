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

    public void setRoomSpace(Room ruanglain, String arah){
        if (checkSpace(arah)){
            roomSpace.replace(arah.toLowerCase(), "-", ruanglain.getNamaRuangan());
        }
    }

    // public String reverseArah(String arah){
    //     if (arah.equals("kiri")){
    //         return "kanan";
    //     }
    //     else if (arah.equals("kanan")){
    //         return "kiri";
    //     }
    //     else if (arah.equals("atas")){
    //         return "bawah";
    //     }
    //     else if (arah.equals("bawah")){
    //         return "atas";
    //     }
    // }

    public boolean checkSpace(String arah) {
        boolean avail = false;
        if (roomSpace.get(arah.toLowerCase()) == "-") {
            avail = true;
        }
        return avail;
    }

    public void addNewRoom(String namaRuangan, String arah) {
        Room ruanganBaru = new Room(namaRuangan, this.rumah);
        if (checkSpace(arah)) {
            setRoomSpace(ruanganBaru, arah);
            //Bingung cara insert ruang ini ke roomspacenya ruangan.
            //Idenya setRoomSpace(ruangini, reverseArah(arah))
        }
    }
    
    public static void main(String[] args) {
        House rumah1 = new House("H1");
        Room ruang1 = new Room("R1", rumah1);
        ruang1.printPetaRuangan();
        System.out.println(ruang1.roomSpace.toString());

        System.out.println(ruang1.checkSpace("KIRI"));
        ruang1.addNewRoom("R2", "kiRi");
        System.out.println(ruang1.roomSpace.toString());
    }
}
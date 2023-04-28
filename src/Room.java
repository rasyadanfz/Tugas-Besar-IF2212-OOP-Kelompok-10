package src;
import java.util.*;

public class Room {
    private String namaRuangan;
    private House rumah;
    private Matrix petaRuangan;
    private Point roomPosition;
    private HashMap<String, Point> placedObject;

    // Konstruktor dengan Point lokasi yang jelas
    public Room(String namaRuangan, House rumah, int x, int y) {
        this.namaRuangan = namaRuangan;
        this.rumah = rumah;
        placedObject = new HashMap<String, Point>();
        //Default dari game
        petaRuangan = new Matrix(6, 6);
        roomPosition = new Point(x, y);
    }

    // Konstruktor tanpa Point lokasi yang fix
    public Room(String namaRuangan, House rumah) {
        this.namaRuangan = namaRuangan;
        this.rumah = rumah;
        placedObject = new HashMap<String, Point>();
        // Set default
        petaRuangan = new Matrix(6, 6);
        roomPosition = new Point(0, 0);
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

    public Point getRoomPosition(){
        return roomPosition;
    }
    
    public void setRoomPosition(int x, int y){
        roomPosition.setX(x);
        roomPosition.setY(y);
    }
    // public void setRoomSpace(Room ruanglain, String arah){
    //     if (checkSpace(arah)){
    //         roomSpace.replace(arah, "-", ruanglain.getNamaRuangan());
    //     }
    // }

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

    // public boolean checkSpace(String arah) {
    //     boolean avail = false;
    //     if (roomSpace.get(arah) == "-") {
    //         avail = true;
    //     }
    //     return avail;
    // }

    // public void addRoom(Room ruangan, String arah) {
    //     if (!ruangan.getHouse().equals(rumah)) { // Nanti rencananya mau dibuat exception
    //         System.out.println("Gagal menambahkan ruangan: Ruangan tidak berada di dalam rumah yang sama!");
    //     } else {
    //         //boolean available = checkSpace(arah);
    //         if (checkSpace(arah) && ruangan.checkSpace(reverseArah(arah))) {
    //             setRoomSpace(ruangan, arah);
    //             //Bingung cara insert ruang ini ke roomspacenya ruangan.
    //             //Idenya setRoomSpace(ruangini, reverseArah(arah))
    //         }
    //     }
    // }
    
    // public static void main(String[] args) {
    //     House rumah1 = new House("H1");
    //     Room ruang1 = new Room("R1", rumah1);
    //     ruang1.printPetaRuangan();
    //     System.out.println(ruang1.roomSpace.toString());

    //     System.out.println(ruang1.checkSpace("KIRI"));
    //     ruang1.addNewRoom("R2", "kiRi");
    //     System.out.println(ruang1.roomSpace.toString());
    // }
}
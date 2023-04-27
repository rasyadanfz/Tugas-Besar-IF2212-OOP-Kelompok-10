package src;
import java.util.*;
import src.Thing.*;

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

    public Point getRoomPosition() {
        return roomPosition;
    }
    
    public void setRoomPosition(int x, int y) {
        roomPosition.setX(x);
        roomPosition.setY(y);
    }
    
    public void placeItem(Thing object, int x, int y) throws Exception {
        
    }

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
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

    public void printPlacedObject() {
        System.out.println("Item yang terdapat pada " + getNamaRuangan() + " dalam rumah " + getHouse().getKodeRumah() + " :");
        for (Map.Entry<String, Point> entry : placedObject.entrySet()) {
            System.out.println(entry.getKey() + " <" + entry.getValue().getX() + ", " + entry.getValue().getY() + ">");
        }
    }
    
    public void setRoomPosition(int x, int y) {
        roomPosition.setX(x);
        roomPosition.setY(y);
    }
    
    public void placeItem(Thing object, int x, int y) throws Exception {
        String kodeItem = object.getNama();
        int panjangItem = object.getPanjang();
        int lebarItem = object.getLebar();

        if (x + panjangItem >= 6 || y + lebarItem >= 6) {
            throw new Exception("Tidak bisa meletakkan benda karena melebihi batas ruangan!");
        } else {
            for (int i = x; i < x + panjangItem; i++) {
                for (int j = y; j < y + lebarItem; j++) {
                    if (petaRuangan.getItem(i, j) != "---") {
                        throw new Exception("Tidak bisa meletakkan benda karena terdapat benda lain di lokasi tersebut!");
                    }
                }
            }

            for (int i = x; i < x + panjangItem; i++) {
                for (int j = y; j < y + lebarItem; j++) {
                    petaRuangan.changeItem(i, j, kodeItem);
                }
            }
            
            placedObject.put(kodeItem, new Point(x, y));
        }
    }

    public static void main(String[] args) {
        House rumah1 = new House("H1", 0, 0);
        Room ruang1 = new Room("R1", rumah1);
        ruang1.printPetaRuangan();

        System.out.println();
        KasurSingle kasur1 = new KasurSingle();
        try {
            ruang1.placeItem(kasur1, 1, 1);
            ruang1.printPetaRuangan();
        } catch (Exception e) {
            System.out.println(e);
        }

        ruang1.printPlacedObject();
    }
}
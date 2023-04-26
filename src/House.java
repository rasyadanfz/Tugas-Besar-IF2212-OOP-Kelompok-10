package src;
import java.util.*;

public class House {
    private String kodeRumah;
    private Matrix petaRumah = new Matrix(9, 9); // Asumsi awal aja biar mudah
    private ArrayList<Room> daftarRuangan;
    private Point lokasi;
    // private Matrix houseSpace;

    public House(String kodeRumah, int x, int y) {
        this.kodeRumah = kodeRumah;
        daftarRuangan = new ArrayList<>();
        lokasi = new Point(x, y);

        //SetDefault setiap kali beli rumah 
        Room ruang1 = new Room("R1", this, 5, 5);       
        daftarRuangan.add(ruang1);
        petaRumah.changeItem(5, 5, ruang1.getNamaRuangan());
    }

    public String getKodeRumah() {
        return kodeRumah;
    }

    public Matrix getPetaRumah() {
        return petaRumah;
    }

    public ArrayList<Room> getDaftarRuangan() {
        return daftarRuangan;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public void printPetaRumah() {
        petaRumah.printMatrix();
    }

    // public Matrix getHouseSpace(){
    //     return houseSpace;
    // }

    // public void viewDenahRuang(){
    //     houseSpace.printMatrix();
    // }

    public void addNewRoom(Room referenceRoom, Room newRoom, String arah) {
        if (checkSpace(referenceRoom, arah.toLowerCase())) {
            int x = referenceRoom.getRoomPosition().getX();
            int y = referenceRoom.getRoomPosition().getY();
            // System.out.println(x);
            // System.out.println(y);

            switch (arah.toLowerCase()) {
                case ("kiri") :
                    x -= 1;
                    break;
                case ("kanan") :
                    x += 1;
                    break;
                case("atas") :
                    y += 1;
                    break;
                case("bawah") :
                    y -= 1;
                    break;
            }

            // System.out.println(x);
            // System.out.println(y);
            newRoom.setRoomPosition(x, y);
            petaRumah.changeItem(x, y, newRoom.getNamaRuangan());
        } else {
            System.out.println("Ruangan gagal ditambahkan! Terdapat ruangan lain pada space tersebut");
        }
    }

    public boolean checkSpace(Room ruang, String arah) {
        boolean avail = false;
        int x = ruang.getRoomPosition().getX();
        int y = ruang.getRoomPosition().getY();
        // System.out.println(x);
        // System.out.println(y);

        //Cek dalam empat arah
        if (arah.toLowerCase().equals("atas")) {
            //Jika ruangan tersebut mentok di atas
            if ((y != 1) && (ruang.getHouse().getPetaRumah().getItem(x, y+1).equals("-"))){
                avail = true;
            }
        }
        else if (arah.toLowerCase().equals("bawah")) {
            //Jika ruangan tersebut mentok di bawah
            if ((y != 9) && (ruang.getHouse().getPetaRumah().getItem(x, y-1).equals("-"))){
                avail = true;
            }
        }
        else if (arah.toLowerCase().equals("kanan")) {
            //Jika ruangan tersebut mentok di kanan
            if ((x != 9) && (ruang.getHouse().getPetaRumah().getItem(x+1, y).equals("-"))){
                avail = true;
            }
        }
        else if (arah.toLowerCase().equals("kiri")) {
            //Jika ruangan tersebut mentok di kiri
            if ((x != 1) && (ruang.getHouse().getPetaRumah().getItem(x-1, y).equals("-"))){
                avail = true;
            }
        }
        else {
            System.out.println("Arah tidak valid!");
        }

        return avail;
    }

    // public static void main(String[] args) {
    //     House rumah1 = new House("H1", 1, 1);
    //     rumah1.printPetaRumah();

    //     Room ruang1 = rumah1.getDaftarRuangan().get(0);
    //     System.out.println(ruang1.getRoomPosition().getX());
    //     System.out.println(ruang1.getRoomPosition().getY());
    //     System.out.println(rumah1.checkSpace(ruang1, "kiri"));

    //     Room ruang2 = new Room("R2", rumah1);
    //     rumah1.addNewRoom(ruang1, ruang2, "kanan");
    //     rumah1.printPetaRumah();

    //     System.out.println("====== BATAS SUCI ======");
    //     Room ruang3 = new Room("R3", rumah1);
    //     System.out.println(rumah1.checkSpace(ruang1, "kanan"));
    //     System.out.println(rumah1.getPetaRumah().getItem(6, 5).equals("-"));
    //     rumah1.addNewRoom(ruang1, ruang3, "kanan");
    //     rumah1.printPetaRumah();
    // }
}

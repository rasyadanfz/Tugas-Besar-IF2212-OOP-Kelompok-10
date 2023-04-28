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
        Room ruang1 = new Room("R01", this, 5, 5);       
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

    public void addNewRoom(Room referenceRoom, Room newRoom, String arah) throws Exception {
        try {
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
                throw new Exception("Ruangan gagal ditambahkan! Terdapat ruangan lain pada space tersebut");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean checkSpace(Room ruang, String arah) throws Exception {
        boolean avail = false;
        int x = ruang.getRoomPosition().getX();
        int y = ruang.getRoomPosition().getY();
        // System.out.println(x);
        // System.out.println(y);

        //Cek dalam empat arah
        if (arah.toLowerCase().equals("atas")) {
            //Jika ruangan tersebut mentok di atas
            if ((y != 1) && (ruang.getHouse().getPetaRumah().getItem(x, y+1).equals("---"))){
                avail = true;
            }
        }
        else if (arah.toLowerCase().equals("bawah")) {
            //Jika ruangan tersebut mentok di bawah
            if ((y != 9) && (ruang.getHouse().getPetaRumah().getItem(x, y-1).equals("---"))){
                avail = true;
            }
        }
        else if (arah.toLowerCase().equals("kanan")) {
            //Jika ruangan tersebut mentok di kanan
            if ((x != 9) && (ruang.getHouse().getPetaRumah().getItem(x+1, y).equals("---"))){
                avail = true;
            }
        }
        else if (arah.toLowerCase().equals("kiri")) {
            //Jika ruangan tersebut mentok di kiri
            if ((x != 1) && (ruang.getHouse().getPetaRumah().getItem(x-1, y).equals("---"))){
                avail = true;
            }
        }
        else {
            throw new Exception("Gagal memeriksa ketersediaan ruang! Arah tidak valid!");
        }

        return avail;
    }

    // public static void main(String[] args) {
    //     World world = World.getWorld();
    //     try {
    //         world.addHouse(0, 0, "H01");
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    //     world.getMap().printMatrix();
    //     House rumah1 = world.getDaftarRumah().get(0);

    //     System.out.println();
    //     rumah1.printPetaRumah();

    //     Room ruang1 = rumah1.getDaftarRuangan().get(0);
    //     System.out.println(ruang1.getRoomPosition().getX());
    //     System.out.println(ruang1.getRoomPosition().getY());
    //     try {
    //         System.out.println(rumah1.checkSpace(ruang1, "kanan"));
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }

    //     Room ruang2 = new Room("R02", rumah1);
    //     try {
    //         rumah1.addNewRoom(ruang1, ruang2, "kanan");
    //     }  catch (Exception e) {
    //         System.out.println(e);
    //     }
    //     rumah1.printPetaRumah();

    //     System.out.println("====== BATAS SUCI ======");
    //     Room ruang3 = new Room("R03", rumah1);
    //     System.out.println(rumah1.getPetaRumah().getItem(6, 5).equals("---"));

    //     try {
    //         rumah1.addNewRoom(ruang1, ruang3, "hayo");
    //         rumah1.addNewRoom(ruang1, ruang3, "kanan");
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    //     rumah1.printPetaRumah();
    // }
}

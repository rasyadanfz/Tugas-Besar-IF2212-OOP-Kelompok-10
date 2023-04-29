package src;

import java.util.*;

import src.Thing.Jam;
import src.Thing.KasurSingle;
import src.Thing.KomporGas;
import src.Thing.MejaKursi;
import src.Thing.Toilet;

public class Main {
    private static World gameWorld;
    private static GameManager game;
    private static String input;
    private static boolean inGame = false;
    private static Scanner inputScanner;
    private static boolean isActive = false;
    public static void main(String[] args) {
        // Initialize Game
        initializeGame();

        inputScanner = new Scanner(System.in);
        System.out.println("\033[1;92m================================================================================================");
        System.out.println("\033[1;92m _ _ _ _____ __    _____ _____ _____ _____    _____ _____ ");
        System.out.println("\033[1;92m| | | |   __|  |  |     |     |     |   __|  |_   _|     |");
        System.out.println("\033[1;92m| | | |   __|  |__|   --|  |  | | | |   __|    | | |  |  |");
        System.out.println("\033[1;92m|_____|_____|_____|_____|_____|_|_|_|_____|    |_| |_____|");

        System.out.println("\033[1;92m _______  ___   __   __             _______  ___      ___   _______  ___   _______  __   __ ");
        System.out.println("\033[1;92m|       ||   | |  |_|  |           |       ||   |    |   | |       ||   | |       ||  | |  |");
        System.out.println("\033[1;92m|  _____||   | |       |   ____    |    _  ||   |    |   | |       ||   | |_     _||  |_|  |");
        System.out.println("\033[1;92m| |_____ |   | |       |  |____|   |   |_| ||   |    |   | |       ||   |   |   |  |       |");
        System.out.println("\033[1;92m|_____  ||   | |       |           |    ___||   |___ |   | |      _||   |   |   |  |_     _|");
        System.out.println("\033[1;92m _____| ||   | | ||_|| |           |   |    |       ||   | |     |_ |   |   |   |    |   |  ");
        System.out.println("\033[1;92m|_______||___| |_|   |_|           |___|    |_______||___| |_______||___|   |___|    |___|  ");
        System.out.println("\033[1;92m================================================================================================\n\n");

        System.out.println("\033[1;93mSilakan pilih aksi selanjutnya :");
        input = inputScanner.nextLine();

        // Commands
        while(isActive){
            gameCommands(input);
            System.out.printf("Silakan pilih aksi selanjutnya: ");
            input = inputScanner.nextLine();
        }
        
    }

    private static void startGame(GameManager game){
        House firstHouse;

        // Create New First Sim
        System.out.println("Masukkan nama lengkap sim baru: ");
        String newSimName = inputScanner.nextLine();
        Sim newSim = new Sim(newSimName);
        game.getSimList().add(newSim);
        game.setActiveSim(newSim);

        // Masukkan Item Default ke Inventory Sim
        game.getActiveSim().getInventory().addItem(new KasurSingle("K01")); // TODO : Implementasi Kode Otomatis Setiap ada yang baru
        game.getActiveSim().getInventory().addItem(new Toilet("T01")); // TODO : Implementasi Kode Otomatis Setiap ada yang baru
        game.getActiveSim().getInventory().addItem(new KomporGas("GS01")); // TODO : Implementasi Kode Otomatis Setiap ada yang baru
        game.getActiveSim().getInventory().addItem(new MejaKursi("MK01")); // TODO : Implementasi Kode Otomatis Setiap ada yang baru
        game.getActiveSim().getInventory().addItem(new Jam("J01")); // TODO : Implementasi Kode Otomatis Setiap ada yang baru

        // Create Rumah
        try{
            gameWorld.addHouse(1, 1, "R1");
             // Generate Ruangan Pertama pada Rumah, masukkan sim pada ruangan pertama pada posisi (1,1)
            firstHouse = gameWorld.getHouse("R1");
            game.getActiveSim().changeCurrentHouse(firstHouse);
            game.getActiveSim().changeCurrentRoom(firstHouse.getDaftarRuangan().get(0));
            game.getActiveSim().changeCurrentPos(new Point(1,1));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    private static void initializeGame(){
        isActive = true;
        game = new GameManager();
        gameWorld = game.getWorld();
    }

    private static void gameCommands(String input){
        if (input.equals("START")){
            startGame(game);
            inGame = true;
        }
        else if (input.equals("HELP")){
            game.help();
        }
        else if (input.equals("EXIT")){
            game.exit();
        }
        else if (input.equals("VIEW SIM INFO")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                System.out.println("SIM INFO:");
                System.out.println("Nama Sim: " + game.getActiveSim().getNamaLengkap());
                System.out.println("Pekerjaan: " + game.getActiveSim().getPekerjaan());
                System.out.println("Kesehatan: " + game.getActiveSim().getKesehatan());
                System.out.println("Kekenyangan: " + game.getActiveSim().getKekenyangan());
                System.out.println("Mood: " + game.getActiveSim().getMood());
                System.out.println("Uang: " + game.getActiveSim().getUang());
            }
        }
        else if (input.equals("VIEW CURRENT LOCATION")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                System.out.println("Current Location : ");
                System.out.println("Rumah : " + game.getActiveSim().getCurrentHouse().getKodeRumah());
                System.out.println("Ruangan : " + game.getActiveSim().getCurrentRoom().getNamaRuangan());
                System.out.println("Posisi : " + game.getActiveSim().getCurrentPos().toString());
                System.out.println("Peta Rumah: ");
                game.getActiveSim().getCurrentHouse().printPetaRumah();
                System.out.println("Peta Ruangan: ");
                game.getActiveSim().getCurrentRoom().printPetaRuangan();
            }
        }
        else if (input.equals("VIEW INVENTORY")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                game.getActiveSim().seeInventory();;
            }
        }
        else if (input.equals("UPGRADE RUMAH")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                // Sim tidak sedang di rumah
                if (Objects.isNull(game.getActiveSim().getCurrentHouse())){
                    System.out.println("Sim tidak dalam suatu rumah");
                }
                else{
                    // TODO : Insert Method Upgrade Rumah Here
                }
            }
        }
        else if (input.equals("MOVE ROOM")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                // Sim tidak sedang di rumah
                if (Objects.isNull(game.getActiveSim().getCurrentHouse())){
                    System.out.println("Sim tidak dalam suatu rumah");
                }
                else{
                    // TODO : Insert Method Pindah Ruangan Here
                }
            }
        }
        else if (input.equals("EDIT ROOM")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                // Sim tidak sedang di rumah
                if (Objects.isNull(game.getActiveSim().getCurrentHouse())){
                    System.out.println("Sim tidak dalam suatu rumah!");
                }
                // Sim tidak di dalam ruangan
                else if (Objects.isNull(game.getActiveSim().getCurrentRoom())){
                    System.out.println("Sim tidak dalam suatu ruangan!");
                }
                else{
                    // TODO : Insert Method Edit Ruangan Here
                    // System.out.printf("Opsi Edit:\n1.Beli barang baru\n2.Pemindahan barang");
                }
            }
        }
        else if (input.equals("ADD SIM")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                // TODO : Insert Method Add Sim Here
            }
        }
        else if (input.equals("CHANGE SIM")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                // TODO : Insert Method Change Sim Here
            }
        }
        else if (input.equals("LIST OBJECT")){
            // TODO : Harus dalam inGame, inHouse, dan inRuangan?
        }
        else if (input.equals("GO TO OBJECT")){
            // TODO : Harus dalam inGame, inHouse, dan inRuangan?
        }
        else if (input.equals("ACTION")){
            // TODO : Complete Actions
            // Tampilin List Aksi yang bisa dilakukan Sim (bergantung pada objek yang ada di sekitarnya kalo aksinya butuh objek)
            input = inputScanner.nextLine();

            // Aksi tanpa waktu
            if (input.equals("PINDAH RUANGAN")){
                System.out.printf("Masukkan Kode Ruangan yang akan dituju: ");
                input = inputScanner.nextLine();
                if (!input.equals(game.getActiveSim().getCurrentRoom().getNamaRuangan())){
                    Room targetRoom = game.getActiveSim().getCurrentHouse().getRoom(input);
                    if (!Objects.isNull(targetRoom)){
                        game.getActiveSim().moveRuangan(targetRoom);
                    }
                    else{
                        System.out.println("Ruangan dengan kode " + input + " tidak ada");
                    }
                }
                else{
                    System.out.println("Sim sudah ada di ruangan " + game.getActiveSim().getCurrentRoom().getNamaRuangan() + "!");
                }
            }
            else if (input.equals("PASANG BARANG")){
                // Tampilin barang yang ada di inventory, minta user pilih barang yang mau dipasang
                // game.getActiveSim().seeInventory();
                // System.out.printf("Pilih barang yang akan dipasang: ");
                // input = inputScanner.nextLine();
                // Thing toPlace = game.getActiveSim().getInventory().getItem(); // TODO : Bingung terkait akses inventorynya sama placeItem

            }
            
        }
        else{
            System.out.println("Perintah tidak dikenali, mohon masukkan perintah yang valid!");
            System.out.println("Jika ingin mengetahui daftar perintah, ketik 'HELP'!");
        }
    }
}

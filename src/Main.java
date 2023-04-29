package src;

import java.util.*;

public class Main {
    private static World gameWorld;
    public static void main(String[] args) {
        
        GameManager game = new GameManager();
        gameWorld = game.getWorld();

        startGame(game);
    }

    private static void startGame(GameManager game){
        Scanner scanner = new Scanner(System.in);
        House firstHouse;

        // Create New First Sim
        System.out.print("Masukkan nama lengkap sim baru: ");
        Sim newSim = new Sim(scanner.nextLine());
        game.getSimList().add(newSim);
        game.setActiveSim(newSim);

        // Create Rumah
        gameWorld.addHouse(1, 1, new House("R" + (game.getHouseCount() + 1)));
        // Generate Ruangan Pertama pada Rumah, masukkan sim pada ruangan pertama pada posisi (1,1)
        try{
            firstHouse = gameWorld.getHouse("R1");
            Room newRoom = new Room("Ruangan Utama", firstHouse);
            firstHouse.addRuangan(newRoom);
            game.getActiveSim().changeCurrentHouse(firstHouse);
            game.getActiveSim().changeCurrentRoom(newRoom);
            game.getActiveSim().changeCurrentPos(new Point(1,1));
        }
        catch (Exception e){
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
                    game.upgradeRumah();
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

            if (input.equals("PASANG BARANG")){
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

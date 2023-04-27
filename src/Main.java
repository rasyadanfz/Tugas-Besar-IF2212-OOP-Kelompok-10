package src;

import java.util.*;

public class Main {
    private static World gameWorld;
    private static GameManager game;
    private static String input;
    private static boolean inGame = false;
    public static void main(String[] args) {
        // Initialize Game
        initializeGame();

        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selamat Datang di Simplicity!!\nSilakan pilih aksi selanjutnya :");
        input = scanner.nextLine();

        // Commands
        gameCommands(input);
        
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
            game.getActiveSim().changeCurrentHouse(firstHouse);
            game.getActiveSim().changeCurrentRoom(firstHouse.getSeluruhRuangan().get(0));
            game.getActiveSim().changeCurrentPos(new Point(1,1));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

    private static void initializeGame(){
        game = new GameManager();
        gameWorld = game.getWorld();
    }

    private static void gameCommands(String input){
        if (input.equals("START")){
            startGame(game);
            inGame = true;
        }
        else if (input.equals("HELP")){
            // TODO : Create & Insert Help Method Here
        }
        else if (input.equals("EXIT")){
            System.out.println("Apakah Anda yakin ingin keluar dari Simplicity? (Y/N)");
            if (scanner.next().equals("Y")){
                // TODO : Create & Insert Exit Method Here
            }
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
            }
        }
        else if (input.equals("VIEW INVENTORY")){
            if (!inGame){
                System.out.println("Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            }
            else{
                System.out.println("Inventory Sim : ");
                game.getActiveSim().getInventory().printItems();
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
        }
        else{
            System.out.println("Perintah tidak dikenali, mohon masukkan perintah yang valid!");
            System.out.println("Jika ingin mengetahui daftar perintah, ketik 'HELP'!");
        }
    }
}

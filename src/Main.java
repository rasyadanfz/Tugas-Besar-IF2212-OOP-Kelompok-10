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
}

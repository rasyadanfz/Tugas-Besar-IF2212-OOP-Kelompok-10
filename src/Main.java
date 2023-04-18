package src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Input Reader
        

        GameManager game = new GameManager();
        startGame(game);
        
        game.getWorld().getMap().printMatrix();
        
        
    }

    private static void startGame(GameManager game){
        Scanner scanner = new Scanner(System.in);

        // Create New First Sim
        System.out.print("Masukkan nama lengkap sim baru: ");
        Sim newSim = new Sim(scanner.nextLine());
        game.getSimList().add(newSim);
        game.setActiveSim(newSim);

        // Create Rumah
        game.getWorld().addHouse(1, 1, new House("R1"));
        // Ganti rumah Sim ke rumah pertama
        game.getActiveSim().changeCurrentHouse(game.getWorld().getDaftarRumah().get(0));
        // TODO: Ganti ruangan Sim ke ruangan pertama

        game.setHari(1);

        // TODO: Implementasi Waktu
    }
}

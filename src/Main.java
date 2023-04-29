package src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Input Reader
        

<<<<<<< Updated upstream
        GameManager game = new GameManager();
        startGame(game);
        
        game.getWorld().getMap().printMatrix();
        
=======
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
        gameCommands(input);
>>>>>>> Stashed changes
        
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

package src;

import java.util.*;

import src.Exceptions.HouseNotFoundException;
import src.Thing.Jam;
import src.Thing.KasurSingle;
import src.Thing.KomporGas;
import src.Thing.MejaKursi;
import src.Thing.Toilet;
import src.Thing.*;

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

        System.out.println("\033[1;97m************ WELCOME SIMPLI-ZEN! ************\n\n");
        System.out.println("\033[1;97mWelcome to" + " \033[1;92mSim-Plicity!");
        System.out.println("\033[1;97mGame ini akan membawa kamu ke dalam dunia simulasi kehidupan yang menyenangkan!");
        System.out.println("\033[1;97mPlease Wait A Few Moments For System To Load The Game...\n");
        System.out.println("\033[1;97mLoading Game World...");
        System.out.println("\033[1;97mGame World Loaded!");

        System.out.println("\033[1;94mENJOY THE GAME!\n\n");
        System.out.println(
                "\033[1;92m================================================================================================");
        System.out.println(
                "\033[1;92m ░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗ ████████╗░█████╗░ ");
        System.out.println(
                "\033[1;92m ░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝ ╚══██╔══╝██╔══██╗");
        System.out.println(
                "\033[1;92m ░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░░ ░░░██║░░░██║░░██║");
        System.out.println(
                "\033[1;92m ░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░ ░░░██║░░░██║░░██║");
        System.out.println(
                "\033[1;92m ░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗ ░░░██║░░░╚█████╔╝");
        System.out.println(
                "\033[1;92m ░░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝ ░░░╚═╝░░░░╚════╝░");

        System.out.println(
                "\033[1;92m ░██████╗██╗███╗░░░███╗░░░░░░██████╗░██╗░░░░░██╗░█████╗░██╗████████╗██╗░░░██╗ ");
        System.out.println(
                "\033[1;92m ██╔════╝██║████╗░████║░░░░░░██╔══██╗██║░░░░░██║██╔══██╗██║╚══██╔══╝╚██╗░██╔╝");
        System.out.println(
                "\033[1;92m ╚█████╗░██║██╔████╔██║█████╗██████╔╝██║░░░░░██║██║░░╚═╝██║░░░██║░░░░╚████╔╝░");
        System.out.println(
                "\033[1;92m ░╚═══██╗██║██║╚██╔╝██║╚════╝██╔═══╝░██║░░░░░██║██║░░██╗██║░░░██║░░░░░╚██╔╝░░");
        System.out.println(
                "\033[1;92m ██████╔╝██║██║░╚═╝░██║░░░░░░██║░░░░░███████╗██║╚█████╔╝██║░░░██║░░░░░░██║░░░");
        System.out.println(
                "\033[1;92m ╚═════╝░╚═╝╚═╝░░░░░╚═╝░░░░░░╚═╝░░░░░╚══════╝╚═╝░╚════╝░╚═╝░░░╚═╝░░░░░░╚═╝░░░");
        System.out.println(
                "\033[1;92m================================================================================================\n\n");

        System.out.println("\033[1;93mWelcome to Sim-Plicity!\n\n");
        System.out.println("\033[1;94m1. START GAME");
        System.out.println("\033[1;94m2. HELP");
        System.out.println("\033[1;94m3. EXIT\n\n");

        System.out.print("\033[1;91mMasukkan perintah: ");
        input = inputScanner.nextLine();

        // Commands
        while (isActive) {
            while (!inGame) {
                if (input == null) {
                    System.out.printf("\033[1;93mSilakan pilih aksi selanjutnya : \033[0;39m");
                    input = inputScanner.nextLine();
                }
                gameCommands(input);
            }
            // TODO: Delete debug time kalo udah bener timenya
            System.out.println("TIME : " + game.getWorldTimer().getTime());

            if (!game.getActiveSim().getInActiveAction()) {
                System.out.printf("\033[1;93mSilakan pilih aksi selanjutnya : \033[0;39m");
                input = inputScanner.nextLine();
                gameCommands(input);
            } else {
                System.out.println("Aktif Sim: " + game.getActiveSim().getInActiveAction());
                game.runTime();
            }
        }
    }

    public static int getCurrentTime() {
        return game.getWorldTimer().getTime();
    }

    private static void startGame() {
        House firstHouse;

        // Start Game Interface
        System.out.println("\033[1;94m█▀ ▀█▀ ▄▀█ █▀█ ▀█▀   █▀▀ ▄▀█ █▀▄▀█ █▀▀");
        System.out.println("\033[1;94m▄█ ░█░ █▀█ █▀▄ ░█░   █▄█ █▀█ █░▀░█ ██▄\n");

        // Create New First Sim
        System.out.println("Masukkan nama lengkap sim baru: ");
        String newSimName = inputScanner.nextLine();
        game.addSim(newSimName);

        game.getActiveSim().getInventory().addItem(new KasurSingle());
        game.getActiveSim().getInventory().addItem(new KasurSingle());
        game.getActiveSim().getInventory().addItem(new Toilet());
        game.getActiveSim().getInventory().addItem(new KomporGas());
        game.getActiveSim().getInventory().addItem(new MejaKursi());
        game.getActiveSim().getInventory().addItem(new Jam());
        game.getActiveSim().getInventory().addItem(new Cermin());

        // Create Rumah
        try {
            game.addNewHouse(game.getActiveSim(), 1, 1);
            // Generate Ruangan Pertama pada Rumah, masukkan sim pada ruangan pertama pada
            // posisi (1,1)
            firstHouse = gameWorld.getHouse("H1");
            game.getActiveSim().changeCurrentHouse(firstHouse);
            game.getActiveSim().changeCurrentRoom(firstHouse.getDaftarRuangan().get(0));
            game.getActiveSim().changeCurrentPos(new Point(1, 1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void initializeGame() {
        isActive = true;
        game = new GameManager();
        gameWorld = game.getWorld();
    }

    private static void gameCommands(String input) {
        input = input.toUpperCase();
        if (!inGame) {
            if (input.equals("START")) {
                startGame();
                inGame = true;
            } else if (input.equals("HELP")) {
                game.help();
            } else if (input.equals("EXIT")) {
                System.out.println("Apakah anda yakin ingin keluar dari game? (Y/N)");
                input = inputScanner.nextLine();
                game.exit();
            } else {
                game.viewSimInfo();
            }
        } else {
            if (!game.getActiveSim().getInActiveAction()) {
                if (input.equals("HELP")) {
                    game.help();
                } else if (input.equals("EXIT")) {
                    System.out.println("Apakah anda yakin ingin keluar dari game? (Y/N)");
                    input = inputScanner.nextLine();
                    game.exit();
                } else if (input.equals("VIEW SIM INFO")) {
                    game.viewSimInfo();
                } else if (input.equals("VIEW CURRENT LOCATION")) {
                    game.viewCurrentLocation();
                } else if (input.equals("VIEW INVENTORY")) {
                    game.viewInventory();
                } else if (input.equals("UPGRADE RUMAH")) {
                    System.out.println("Silakan masukkan arah penambahan ruangan: ");
                    input = inputScanner.nextLine();
                    game.upgradeRumah(input);
                } else if (input.equals("MOVE ROOM")) {
                    System.out.printf("Masukkan Kode Ruangan yang akan dituju: ");
                    input = inputScanner.nextLine();
                    game.moveRoom(input);
                } else if (input.equals("EDIT ROOM")) {
                    game.editRoom();
                } else if (input.equals("ADD SIM")) {
                    System.out.print("Masukkan nama Sim baru : ");
                    input = inputScanner.nextLine();
                    game.addSim(input);
                    System.out.printf("Sim %s berhasil dibuat!\n", input);
                } else if (input.equals("CHANGE SIM")) {
                    game.changeSim(input, inputScanner);
                } else if (input.equals("LIST OBJECT")) {
                    game.listObject();
                } else if (input.equals("GO TO OBJECT")) {
                    game.listObject();
                    System.out.println("Masukkan posisi x benda yang ingin dituju");
                    int xTarget = Integer.parseInt(inputScanner.nextLine());
                    System.out.println("Masukkan posisi y benda yang ingin dituju");
                    int yTarget = Integer.parseInt(inputScanner.nextLine());
                    game.goToObject(xTarget, yTarget);
                } else if (input.equals("PASANG BARANG")) {
                    System.out.println("Masukkan nama barang yang ingin dipasang");
                    String itemName = inputScanner.nextLine();
                    System.out.println("Peta ruangan saat ini: ");
                    game.getActiveSim().getCurrentRoom().printPetaRuangan(game.getActiveSim());
                    System.out.println("Masukkan posisi x untuk pemasangan barang: ");
                    int x = Integer.parseInt(inputScanner.nextLine());
                    System.out.println("Masukkan posisi y untuk pemasangan barang: ");
                    int y = Integer.parseInt(inputScanner.nextLine());
                    try {
                        game.pasangBarang(itemName, x, y);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.equals("ACTION")) {
                    game.actions(false);
                } else {
                    System.out.println("Perintah tidak dikenali, mohon masukkan perintah yang valid!");
                    System.out.println("Jika ingin mengetahui daftar perintah, ketik 'HELP'!");
                }
            } else {
                System.out.println("Sim sedang melakukan aksi aktif!");
            }
        }
    }
}

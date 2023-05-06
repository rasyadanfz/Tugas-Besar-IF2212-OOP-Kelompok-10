package com.Kelompok10;

import java.util.*;

import com.Kelompok10.Exceptions.*;
import com.Kelompok10.Thing.*;

public class Main {
    private static World gameWorld;
    private static GameManager game;
    private static String input;
    private static boolean inGame = false;
    private static Scanner inputScanner;
    private static boolean isActive = false;
    private static boolean isCheatEnabled = false;

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
        System.out.println("\033[1;94m1. START");
        System.out.println("\033[1;94m2. HELP");
        System.out.println("\033[1;94m3. EXIT\n\n");

        System.out.print("\033[1;91mMasukkan perintah: ");

        // Commands
        while (isActive) {
            while (!inGame) {
                input = inputScanner.nextLine();
                gameCommands(input);
            }
            if (!game.getActiveSim().getInActiveAction()) {
                // Cek Sim Mati
                game.simsAliveCheck();
                if (game.getActiveSim() == null && !game.getSimList().isEmpty()) {
                    System.out.println("Silakan ganti sim karena sim seebelumnya mati!");
                    game.changeSim(input, inputScanner);
                }
                if (game.getActiveSim() != null) {
                    System.out.printf("\033[1;93mSilakan pilih aksi selanjutnya : \033[0;39m");
                    input = inputScanner.nextLine();
                    gameCommands(input);
                } else {
                    System.out.println("Seluruh sim mati!\nGame Over!!");
                    isActive = false;
                }
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
        try {
            game.addSim(newSimName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void initializeGame() {
        inputScanner = InputScanner.getInputScanner().getScanner();
        isActive = true;
        game = GameManager.getGameManager();
        gameWorld = game.getWorld();
        isCheatEnabled = game.getIsCheatEnabled();
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
                game.exit();
            } else {
                System.out.println("Silakan gunakan perintah START, HELP, atau EXIT!");
                System.out.printf("\033[1;93mSilakan pilih aksi selanjutnya : \033[0;39m");
            }
        } else if (input.equals("CH")) {
            if (!isCheatEnabled) {
                System.out.printf("Passcode: ");
                input = inputScanner.nextLine();
                game.setIsCheatEnabled(input, true);
                isCheatEnabled = true;
            } else {
                System.out.printf("Command Cheat:");
                input = inputScanner.nextLine();
                if (input.toUpperCase().equals("TS")) {
                    if (!game.getCheat().getIsTimeSkipEnabled()) {
                        game.getCheat().setIsTimeSkipEnabled(true);
                    } else {
                        game.getCheat().setIsTimeSkipEnabled(false);
                    }
                }
                if (input.toUpperCase().equals("M")) {
                    game.getActiveSim().setUang(9000);
                }
            }
        } else {
            if (!game.getActiveSim().getInActiveAction()) {
                if (input.equals("HELP")) {
                    game.help();
                } else if (input.equals("EXIT")) {
                    game.exit();
                } else if (input.equals("VIEW SIM INFO")) {
                    game.getActiveSim().showSimInfo();
                } else if (input.equals("VIEW CURRENT LOCATION")) {
                    game.getActiveSim().viewCurrentLocation();
                } else if (input.equals("VIEW INVENTORY")) {
                    game.getActiveSim().seeInventory();
                } else if (input.equals("UPGRADE RUMAH")) {
                    System.out.println("Silakan masukkan arah penambahan ruangan: ");
                    input = inputScanner.nextLine();
                    try {
                        game.getActiveSim().upgradeRumah(input);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.equals("MOVE ROOM")) {
                    System.out.println("Peta Rumah: ");
                    game.getActiveSim().getCurrentHouse().printPetaRumah();
                    System.out.printf("Masukkan Kode Ruangan yang akan dituju: ");
                    input = inputScanner.nextLine();
                    game.getActiveSim().moveRoom(input);
                } else if (input.equals("EDIT ROOM")) {
                    game.getActiveSim().editRoom();
                } else if (input.equals("ADD SIM")) {
                    if (!game.getHaveCreatedNewSim()) {
                        System.out.print("Masukkan nama Sim baru : ");
                        input = inputScanner.nextLine();
                        boolean avail = false;
                        for (int i = 0; i < game.getSimList().size(); i++) {
                            if (game.getSimList().get(i).getNamaLengkap().toLowerCase().equals(input.toLowerCase())) {
                                System.out.print("Nama Sim sudah ada! ");
                                avail = true;
                            }
                        }
                        if (!avail) {
                            try {
                                game.addSim(input);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                System.out.println("Gagal membuat sim baru!");
                            }
                        } else {
                            System.out.println("Gagal membuat Sim baru!");
                        }
                    } else {
                        System.out.println("Anda sudah membuat sim baru hari ini!");
                    }
                } else if (input.equals("CHANGE SIM")) {
                    game.changeSim(input, inputScanner);
                } else if (input.equals("LIST OBJECT")) {
                    try {
                        game.listObject();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.equals("GO TO OBJECT")) {
                    try {
                        game.listObject();
                        System.out.println("Masukkan posisi x benda yang ingin dituju");
                        int xTarget = Integer.parseInt(inputScanner.nextLine());
                        System.out.println("Masukkan posisi y benda yang ingin dituju");
                        int yTarget = Integer.parseInt(inputScanner.nextLine());
                        game.getActiveSim().goToObject(xTarget, yTarget);
                    } catch (NumberFormatException e) {
                        System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.equals("PASANG BARANG")) {
                    game.getActiveSim().getInventory().printFurnitures();
                    System.out.println("Masukkan nama barang yang ingin dipasang");
                    String itemName = inputScanner.nextLine();
                    System.out.println("Peta ruangan saat ini: ");
                    game.getActiveSim().getCurrentRoom().printPetaRuangan(game.getActiveSim());

                    System.out.println("Apakah kamu ingin melakukan rotate pada barang? (Y/N)");
                    String rotate = inputScanner.nextLine();
                    while (!rotate.toUpperCase().equals("Y") && !rotate.toUpperCase().equals("N")) {
                        System.out.println("Masukkan Y atau N!");
                        rotate = inputScanner.nextLine();
                    }
                    boolean isRotate = false;
                    if (rotate.toUpperCase().equals("Y")) {
                        System.out.println("Item berhasil di-rotate!");
                        isRotate = true;
                    }

                    try {
                        System.out.println("Masukkan posisi x untuk pemasangan barang: ");
                        int x = Integer.parseInt(inputScanner.nextLine());
                        System.out.println("Masukkan posisi y untuk pemasangan barang: ");
                        int y = Integer.parseInt(inputScanner.nextLine());
                        try {
                            game.getActiveSim().installBarang(itemName, x, y, isRotate);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                    }
                } else if (input.equals("BELI BARANG")) {
                    game.getActiveSim().buyFurniture();
                } else if (input.equals("BELI INGREDIENTS")) {
                    game.getActiveSim().buyIngredient();
                } else if (input.equals("JUAL BARANG")) {
                    game.getActiveSim().getInventory().printFurnitures();
                    System.out.printf("Masukkan nama barang yang ingin dijual: ");
                    input = inputScanner.nextLine();
                    game.getActiveSim().sellBarang(input);
                } else if (input.equals("AMBIL BARANG")) {
                    try {
                        game.listObject();
                        System.out.println("Masukkan posisi barang yang mau diambil");
                        System.out.println("Masukkan posisi x barang yang akan diambil: ");
                        int x = Integer.parseInt(inputScanner.nextLine());
                        System.out.println("Masukkan posisi y barang yang akan diambil: ");
                        int y = Integer.parseInt(inputScanner.nextLine());
                        game.getActiveSim().ambilBarang(x, y);
                    } catch (NumberFormatException e) {
                        System.out.println(e.getClass().getSimpleName() + ": Durasi harus berupa angka!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.equals("GANTI PEKERJAAN")) {
                    try {
                        game.getActiveSim().changeJob();
                    } catch (CantChangeJobException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.equals("ACTION")) {
                    game.getActiveSim().actions(false);
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

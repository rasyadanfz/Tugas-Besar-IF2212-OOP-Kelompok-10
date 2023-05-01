package src;

import java.util.*;

import src.Exceptions.HouseNotFoundException;
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
    private static int kodeIterator = 1;

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
    System.out.println("\033[1;92m ░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗ ████████╗░█████╗░ ");
    System.out.println("\033[1;92m ░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝ ╚══██╔══╝██╔══██╗");
    System.out.println("\033[1;92m ░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░░ ░░░██║░░░██║░░██║");
    System.out.println("\033[1;92m ░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░ ░░░██║░░░██║░░██║");
    System.out.println("\033[1;92m ░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗ ░░░██║░░░╚█████╔╝");
    System.out.println("\033[1;92m ░░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝ ░░░╚═╝░░░░╚════╝░");

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
            // TODO: Delete debug time kalo udah bener timenya
            System.out.println("TIME : " + game.getWorldTimer().getTime());
            gameCommands(input);
            System.out.printf("Silakan pilih aksi selanjutnya: ");
            input = inputScanner.nextLine();
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

        // Masukkan Item Default ke Inventory Sim
        game.getActiveSim().getInventory().addItem(new KasurSingle("K0" + kodeIterator)); // TODO : Implementasi Kode
                                                                                          // Otomatis Setiap
        // ada yang baru
        game.getActiveSim().getInventory().addItem(new Toilet("T0" + kodeIterator)); // TODO : Implementasi Kode
                                                                                     // Otomatis Setiap ada
        // yang baru
        game.getActiveSim().getInventory().addItem(new KomporGas("GS0" + kodeIterator)); // TODO : Implementasi Kode
                                                                                         // Otomatis Setiap
        // ada yang baru
        game.getActiveSim().getInventory().addItem(new MejaKursi("MK0" + kodeIterator)); // TODO : Implementasi Kode
                                                                                         // Otomatis Setiap
        // ada yang baru
        game.getActiveSim().getInventory().addItem(new Jam("J0" + kodeIterator)); // TODO : Implementasi Kode Otomatis
                                                                                  // Setiap ada yang
        // baru
        kodeIterator++;

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
        if (input.equals("START")) {
            startGame();
            inGame = true;
        } else if (input.equals("HELP")) {
            game.help();

            input = inputScanner.nextLine();

            if (input.equals("GAME DESCRIPTION")) {
                game.gameDescription();
            } else if (input.equals("HOW TO PLAY")) {
                game.howToPlay();
            } else if (input.equals("COMMAND LIST")) {
                game.commandList();
            }
        } else if (input.equals("EXIT")) {
            game.exit();
        } else if (input.equals("VIEW SIM INFO")) {
            if (!inGame) {
                System.out.println(
                        "Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            } else {
                System.out.println("SIM INFO:");
                System.out.println("Nama Sim: " + game.getActiveSim().getNamaLengkap());
                System.out.println("Pekerjaan: " + game.getActiveSim().getPekerjaan());
                System.out.println("Kesehatan: " + game.getActiveSim().getKesehatan());
                System.out.println("Kekenyangan: " + game.getActiveSim().getKekenyangan());
                System.out.println("Mood: " + game.getActiveSim().getMood());
                System.out.println("Uang: " + game.getActiveSim().getUang());
            }
        } else if (input.equals("VIEW CURRENT LOCATION")) {
            if (!inGame) {
                System.out.println(
                        "Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            } else {
                System.out.println("Current Location : ");
                System.out.println("Rumah : " + game.getActiveSim().getCurrentHouse().getKodeRumah());
                System.out.println("Ruangan : " + game.getActiveSim().getCurrentRoom().getNamaRuangan());
                System.out.println("Posisi : " + game.getActiveSim().getCurrentPos().toString());
                System.out.println("Peta Rumah: ");
                game.getActiveSim().getCurrentHouse().printPetaRumah();
                System.out.println("Peta Ruangan: ");
                game.getActiveSim().getCurrentRoom().printPetaRuangan();
            }
        } else if (input.equals("VIEW INVENTORY")) {
            if (!inGame) {
                System.out.println(
                        "Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            } else {
                game.getActiveSim().seeInventory();
                ;
            }
        } else if (input.equals("UPGRADE RUMAH")) {
            if (!inGame) {
                System.out.println(
                        "Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            } else {
                // Sim tidak sedang di rumah
                if (Objects.isNull(game.getActiveSim().getCurrentHouse())) {
                    System.out.println("Sim tidak dalam suatu rumah");
                } else {
                    // TODO : Insert Method Upgrade Rumah Here\
                    try {
                        Room newRoom = new Room("R0" + kodeIterator, gameWorld.getHouse("H1"));
                        System.out.println("Silakan masukkan arah penambahan ruangan: ");
                        String arah = inputScanner.nextLine();
                        game.getActiveSim().upgradeRumah(game.getActiveSim().getCurrentRoom(), newRoom, arah);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        } else if (input.equals("MOVE ROOM")) {
            if (!inGame) {
                System.out.println(
                        "Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            } else {
                // Sim tidak sedang di rumah
                if (Objects.isNull(game.getActiveSim().getCurrentHouse())) {
                    System.out.println("Sim tidak dalam suatu rumah");
                } else {
                    System.out.printf("Masukkan Kode Ruangan yang akan dituju: ");
                    input = inputScanner.nextLine();
                    if (!input.equals(game.getActiveSim().getCurrentRoom().getNamaRuangan())) {
                        Room targetRoom = game.getActiveSim().getCurrentHouse().getRoom(input);
                        if (!Objects.isNull(targetRoom)) {
                            game.getActiveSim().moveRuangan(targetRoom);
                        } else {
                            System.out.println("Ruangan dengan kode " + input + " tidak ada");
                        }
                    } else {
                        System.out.println("Sim sudah ada di ruangan "
                                + game.getActiveSim().getCurrentRoom().getNamaRuangan() + "!");
                    }
                }
            }
        } else if (input.equals("EDIT ROOM")) {
            if (!inGame) {
                System.out.println(
                        "Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            } else {
                // Sim tidak sedang di rumah
                if (Objects.isNull(game.getActiveSim().getCurrentHouse())) {
                    System.out.println("Sim tidak dalam suatu rumah!");
                }
                // Sim tidak di dalam ruangan
                else if (Objects.isNull(game.getActiveSim().getCurrentRoom())) {
                    System.out.println("Sim tidak dalam suatu ruangan!");
                } else {
                    // TODO : Insert Method Edit Ruangan Here
                    // System.out.printf("Opsi Edit:\n1.Beli barang baru\n2.Pemindahan barang");
                }
            }
        } else if (input.equals("ADD SIM")) {
            if (!inGame) {
                System.out.println(
                        "Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            } else {
                // TODO : Insert Method Add Sim Here
                System.out.print("Masukkan nama Sim baru : ");
                input = inputScanner.nextLine();
                game.addSim(input);
                System.out.printf("Sim %s berhasil dibuat!\n", input);
            }
        } else if (input.equals("CHANGE SIM")) {
            if (!inGame) {
                System.out.println(
                        "Anda belum memasuki permainan! Silakan memasuki permainan untuk menggunakan perintah ini!");
            } else {
                // TODO : Insert Method Change Sim Here
                game.printSimList();
                System.out.print("Mau ganti ke Sim mana? Ketik namanya : ");
                input = inputScanner.nextLine();
                // Validasi nama Sim
                Sim currentSim = game.getSim(input);
                while (currentSim == null) {
                    System.out.print("Nama Sim salah! Coba input ulang : ");
                    input = inputScanner.nextLine();
                    currentSim = game.getSim(input);
                }
                game.setActiveSim(currentSim);
            }
        } else if (input.equals("LIST OBJECT")) {
            // TODO : Harus dalam inGame, inHouse, dan inRuangan?
        } else if (input.equals("GO TO OBJECT")) {
            // TODO : Harus dalam inGame, inHouse, dan inRuangan?
        } else if (input.equals("ACTION")) {
            // TODO : Complete Actions
            // Tampilin List Aksi yang bisa dilakukan Sim (bergantung pada objek yang ada di
            // sekitarnya kalo aksinya butuh objek)
            input = inputScanner.nextLine();

            if (input.equals("PASANG BARANG")) {
                // Tampilin barang yang ada di inventory, minta user pilih barang yang mau
                // dipasang
                // game.getActiveSim().seeInventory();
                // System.out.printf("Pilih barang yang akan dipasang: ");
                // input = inputScanner.nextLine();
                // Thing toPlace = game.getActiveSim().getInventory().getItem(); // TODO :
                // Bingung terkait akses inventorynya sama placeItem
            }

        } else {
            System.out.println("Perintah tidak dikenali, mohon masukkan perintah yang valid!");
            System.out.println("Jika ingin mengetahui daftar perintah, ketik 'HELP'!");
        }
    }
}

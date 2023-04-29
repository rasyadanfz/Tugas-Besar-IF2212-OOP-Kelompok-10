package src;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    private World world;
    private ArrayList<Sim> simList;
    private int time;
    private int hari;
    private ArrayList<String> listOfActions;
    private Sim activeSim;
    private int houseCount;
    private int kasurCount = 0;

    public GameManager(){
        world = World.getWorld();
        simList = new ArrayList<Sim>();
        hari = 0;
        listOfActions = new ArrayList<String>();
        houseCount = 0;
    }

    public World getWorld(){
        return world;
    }
    
    public ArrayList<Sim> getSimList(){
        return simList;
    }

    public int getHari(){
        return hari;
    }

    public ArrayList<String> getListOfActions(){
        return listOfActions;
    }

    public Sim getActiveSim(){
        return activeSim;
    }

    public int getTime(){
        return time;
    }

    public int getHouseCount(){
        return houseCount;
    }

    public int getKasurCount(){
        return kasurCount;
    }

    public void printSimList(){
        int i = 1;
        System.out.println("Daftar Sim dalam Game: ");
        for (Sim s : simList){
            System.out.println(i + ". " + s.getNamaLengkap());
        }
    }

    // Setter
    public void setActiveSim(Sim sim){
        activeSim = sim;
    }

    public void setHari(int hari){
        this.hari = hari;
    }

    // Menu
    // TODO : Implementasi Help
    public void help(){
        System.out.println("\033[1;32mThis is the help information for the Sim-Plicity.");

        System.out.println("\033[1;91m====================================================================================================================================================================");
        
        System.out.println("\033[1;91m ______   _______  _______  ___   _  ______   ___   _______  _______  ___     _______  _______  ______   __   __  _______  ___   __    _  _______  __    _ ");
        System.out.println("\033[1;91m|      | |       ||       ||   | | ||    _ | |   | |       ||       ||   |   |       ||       ||    _ | |  |_|  ||   _   ||   | |  |  | ||   _   ||  |  | |");
        System.out.println("\033[1;91m|  _    ||    ___||  _____||   |_| ||   | || |   | |    _  ||  _____||   |   |    _  ||    ___||   | || |       ||  |_|  ||   | |   |_| ||  |_|  ||   |_| |");
        System.out.println("\033[1;91m| | |   ||   |___ | |_____ |      _||   |_|| |   | |   |_| || |_____ |   |   |   |_| ||   |___ |   |_|| |       ||       ||   | |       ||       ||       |");
        System.out.println("\033[1;91m| |_|   ||    ___||_____  ||     |_ |    __ ||   | |    ___||_____  ||   |   |    ___||    ___||    __ ||       ||       ||   | |  _    ||       ||  _    |");
        System.out.println("\033[1;91m|       ||   |___  _____| ||    _  ||   |  |||   | |   |     _____| ||   |   |   |    |   |___ |   |  ||| ||_|| ||   _   ||   | | | |   ||   _   || | |   |");
        System.out.println("\033[1;91m|______| |_______||_______||___| |_||___|  |||___| |___|    |_______||___|   |___|    |_______||___|  |||_|   |_||__| |__||___| |_|  |__||__| |__||_|  |__|\n");
    

        System.out.println("\033[1;31mSim-plicity adalah permainan yang ingin dimiliki oleh Indra dan Doni. Game ini adalah permainan tentang kehidupan sehari-hari sims, karakter orang dalam game ini. \nSelain sim, game ini memiliki entitas world sebagai dunia virtual, rumah sebagai tempat tinggal sim, ruangan, dan objek-objek. \nSim juga memiliki kesejahteraan yang berparameter mood, kekenyangan, dan kesehatan yang bisa mempengaruhi umur sim. \nSim juga memiliki pekerjaan dan dapat melakukan aksi yang bisa mempengaruhi parameter kesejahteraannya.");
        System.out.println("\033[1;31mGame ini diprogram menggunakan bahasa Java dengan berbasis Command Line Interface. \nPertama, pemain dapat menciptakan sim. Setiap pembuatan satu sim, user akan mendapatkan satu rumah baru berukuran 6x6.  \nUser dapat berpindah-pindah sim untuk memainkannya. Untuk cara bermain, akan dijelaskan lebih rinci pada bagian cara bermain. \n");
        System.out.println("\033[1;91m====================================================================================================================================================================\n\n\n\n");
    

        System.out.println("\033[1;93m====================================================================================================================================================================");
        System.out.println("\033[1;93m _______  _______  ______   _______    _______  _______  ______   __   __  _______  ___   __    _ ");
        System.out.println("\033[1;93m|       ||   _   ||    _ | |   _   |  |  _    ||       ||    _ | |  |_|  ||   _   ||   | |  |  | |");
        System.out.println("\033[1;93m|       ||  |_|  ||   | || |  |_|  |  | |_|   ||    ___||   | || |       ||  |_|  ||   | |   |_| |");
        System.out.println("\033[1;93m|       ||       ||   |_|| |       |  |       ||   |___ |   |_|| |       ||       ||   | |       |");
        System.out.println("\033[1;93m|      _||       ||    __ ||       |  |  _   | |    ___||    __ ||       ||       ||   | |  _    |");
        System.out.println("\033[1;93m|     |_ |   _   ||   |  |||   _   |  | |_|   ||   |___ |   |  ||| ||_|| ||   _   ||   | | | |   |");
        System.out.println("\033[1;93m|_______||__| |__||___|  |||__| |__|  |_______||_______||___|  |||_|   |_||__| |__||___| |_|  |__|" + "\n");

        System.out.println("\033[1;33m1. Buatlah Sim baru! Nantinya, kamu juga bisa memilih sim yang ingin kamu mainkan.");
        System.out.println("\033[1;33m2. Setiap kamu membuat Sim baru, kondisi kesejahteraan kamu (kekenyangan, mood, dan kesehatan) akan otomatis di-set 80 dan kamu juga akan diberi uang awal sebesar 100.");
        System.out.println("\033[1;33m3. Selain itu, kamu juga akan mendapatkan pekerjaan secara random, rumah yang berisi ruangan berdimensi 6x6, dan barang-barang default yang otomatis ada di inventory kamu, seperti kasur, toilet, kompor, kursi, meja, dan jam.");
        System.out.println("\033[1;33m4. Namun, berhati-hatilah karena jika salah satu saja kondisi kesejahteraanmu bernilai 0, kamu bisa mati X_X");
        System.out.println("\033[1;33m5. Sim juga akan otomatis memiliki pekerjaan secara random");
        System.out.println("\033[1;33m6. Kamu dapat menjalankan aksi dengan Sim kamu, loh! Berikut rincian dan penjelasannya :");
        System.out.println("      \033[1;33ma) Aksi Aktif: Aksi yang memerlukan waktu dan keterlibatan Sim secara langsung");
        System.out.println("      \033[1;33mb) ksi Upgrade: Aksi yang memerlukan waktu tetapi tidak memerlukan keterlibatan Sim secara langsung");
        System.out.println("      \033[1;33mc) Aksi Non-aktif: yang tidak memerlukan waktu");
        System.out.println("      \033[1;33md) ksi Penambahan Sim: Aksi untuk menciptakan Sim baru beserta house yang akan didapatkannya.\n");
        
        System.out.println("\033[1;34mAksi Aktif : ");
        System.out.println("   \033[1;34ma) Kerja");
        System.out.println("   \033[1;34mb) Olahraga");
        System.out.println("   \033[1;34mc) Sleep");
        System.out.println("   \033[1;34md) Eating");
        System.out.println("   \033[1;34me) Cook");
        System.out.println("   \033[1;34mf) Visit");
        System.out.println("   \033[1;34mg) Pee");
        System.out.println("   \033[1;34mh) Watching TV");
        System.out.println("   \033[1;34mi) Bath");
        System.out.println("   \033[1;34mj) Washing Hand\n\n");
        
        System.out.println("\033[1;35mAksi Upgrade : ");
        System.out.println("   \033[1;35ma) Upgrade Rumah");
        System.out.println("   \033[1;35mb) Buy Item");
        System.out.println("   \033[1;35mc) Sell Barang\n\n");

        System.out.println("\033[1;36mAksi Non-Aktif : ");
        System.out.println("   \033[1;36ma) lookPainting");
        System.out.println("   \033[1;36mb) Mirror");
        System.out.println("   \033[1;36mc) moveRuangan");
        System.out.println("   \033[1;36md) seeInventory");
        System.out.println("   \033[1;36me) installBarang");
        System.out.println("   \033[1;36mf) ambilBarang");
        System.out.println("   \033[1;36mg) seeTime\n\n");


        System.out.println("\033[1;33m7. Sim dapat membeli beberapa barang yang disediakan di dalam game. Barang-barang yang dapat dibeli adalah : ");
        System.out.println("      \033[1;30ma) Makanan");
        System.out.println("      \033[1;30mb)Kasur Single");
        System.out.println("      \033[1;30mc)Kasur Queen Size");
        System.out.println("      \033[1;30md)Kasur King Size");
        System.out.println("      \033[1;30me)Toilet");
        System.out.println("      \033[1;30mf)Kompor Gas");
        System.out.println("      \033[1;30mg)Kompor Listrik");
        System.out.println("      \033[1;30mh)Meja dan Kursi");
        System.out.println("      \033[1;30mi)Jam");
        System.out.println("      \033[1;30mj)TV");
        System.out.println("      \033[1;30mk)Shower"); 
        System.out.println("      \033[1;30ml)Lukisan ");
        System.out.println("      \033[1;30mm)Toilet ");
        System.out.println("      \033[1;30mn)Wastafel ");
        System.out.println("      \033[1;30mo)Cermin ");

        System.out.println("\033[1;93m====================================================================================================================================================================\033[0;39m");
    }

    // TODO : Implementasi Exit
    public void exit(){
        Scanner input = new Scanner(System.in);
        System.out.println("Apakah anda yakin ingin keluar dari game? (Y/N)");
        String answer = input.nextLine();
        if (answer.equals("Y")){
            System.out.println("Terima kasih telah bermain! \n Sampai jumpa lagi!");
            System.exit(0);
        }
        else{
            System.out.println("Kembali ke menu utama...");
            // TODO : Implementasi menu utama

        }

    }

    // TODO : Implementasi viewSimInfo
    public void viewSimInfo(){

    }

    // TODO : Implementasi viewCurrentLocation
    public void viewCurrentLocation(){

    }

    public void viewInventory(){
        activeSim.getInventory().printItems();
    }

}

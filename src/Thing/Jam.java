package src.Thing;

import src.Main;
import src.Sim;

public class Jam extends Thing {
    private static int sisaWaktuUpgrade;
    private static int sisaWaktuKirim;

    public Jam(String kodeItem) {
        super("Jam", kodeItem, 1, 1, 10);
    }

    public Jam() {
        super("Jam", 1, 1, 10);
    }

    public static void ambilSisaWaktuUpgrade(int waktuMulai, int waktuUpgrade) {
        int waktuSelesai = waktuMulai + waktuUpgrade;
        sisaWaktuUpgrade = waktuSelesai - Main.getCurrentTime();
    }

    public static void ambilSisaWaktuKirim(int waktuMulai, int waktuKirim) {
        int waktuSelesai = waktuMulai + waktuKirim;
        sisaWaktuKirim = waktuSelesai - Main.getCurrentTime();
    }

    public void lihatWaktu() {
        effect(null, 0);
    }

    public void effect(Sim sim, int duration) {
        System.out.println("TIME: " + Main.getCurrentTime());
        if (sisaWaktuUpgrade > 0) {
            System.out.println("Sisa durasi upgrade rumah: " + sisaWaktuUpgrade);
        }
        if (sisaWaktuKirim > 0) {
            System.out.println("Sisa durasi pengiriman barang: " + sisaWaktuKirim);
        }
    }
}

package com.Kelompok10.Thing;

import com.Kelompok10.Sim;
import com.Kelompok10.Timer;

public class Jam extends Thing {
    private int sisaWaktuKirim;
    private int sisaWaktuUpgrade;

    public Jam(String kodeItem) {
        super("Jam", kodeItem, 1, 1, 10);
    }

    public Jam() {
        super("Jam", 1, 1, 10);
    }

    public void setSisaWaktuUpgrade(Sim sim) {
        sisaWaktuUpgrade = sim.getSisaWaktuUpgrade();
    }

    public void setSisaWaktuKirim(Sim sim) {
        sisaWaktuKirim = sim.getItemDelivery().getRemainingDuration();
    }

    public void lihatWaktu(Sim sim) {
        System.out.println("Day: " + Timer.getTimer().getDay());
        System.out.printf("Sisa Waktu: %d menit %d detik \n", Timer.getTimer().getMinutes(),
                Timer.getTimer().getSeconds());
        setSisaWaktuKirim(sim);
        setSisaWaktuUpgrade(sim);
        if (sisaWaktuUpgrade > 0) {
            System.out.println("Sisa durasi upgrade rumah: " + sisaWaktuUpgrade);
        } else {
            System.out.println("Tidak ada proses upgrade rumah yang berlangsung!");
        }

        if (sisaWaktuKirim > 0) {
            System.out.println("Informasi pengiriman barang: ");
            System.out.println("Nama Barang: " + sim.getItemDelivery().getItemName());
            System.out.println("Sisa durasi pengiriman barang: " + sisaWaktuKirim);
        } else {
            System.out.println("Tidak ada pengiriman barang yang berlangsung!");
        }
    }
}

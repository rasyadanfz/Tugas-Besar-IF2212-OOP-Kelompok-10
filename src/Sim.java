package src;

import java.util.*;
import java.util.Random;

import javax.swing.text.html.StyleSheet.ListPainter;

import src.Jobs.*;;

public class Sim {
    private String namaLengkap;
    private Job pekerjaan;
    private int uang;
    private Inventory inventory;
    private int kekenyangan;
    private int kesehatan;
    private int mood;
    private String status;

    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        uang = 100;
        kekenyangan = 80; kesehatan = 80; mood = 80;


    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public int getMood() {
        return mood;
    }

    public void setKekenyangan(int num) {
        kekenyangan = num;
    }

    public void setKesehatan(int num) {
        kesehatan = num;
    }

    public void setMood(int num) {
        mood = num;
    }

    public Job getJob(ArrayList<? extends Job> listPekerjaan) {
        int i = listPekerjaan.size();
        Random pekerjaan = new Random();
    
        return (listPekerjaan.get(pekerjaan.nextInt(i-1)));
    }

    public static void main(String[] args) {
        // ArrayList<? extends Job> listPekerjaan = new ArrayList<? extends Job>();
        // System.out.println(listPekerjaan);
    }
}

package com.Kelompok10;

import java.util.Scanner;

public class InputScanner {
    private Scanner scanner;
    private static InputScanner inputScanner = new InputScanner();

    private InputScanner() {
        scanner = new Scanner(System.in);
    }

    public static InputScanner getInputScanner() {
        return inputScanner;
    }

    public Scanner getScanner() {
        return scanner;
    }
}

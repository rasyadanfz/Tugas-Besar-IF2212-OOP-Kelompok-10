package com.Kelompok10.Exceptions;

public class DurationNotValidException extends Exception {
    public DurationNotValidException(int duration) {
        super("Durasi bukan kelipatan " + duration + " detik");
    }
}

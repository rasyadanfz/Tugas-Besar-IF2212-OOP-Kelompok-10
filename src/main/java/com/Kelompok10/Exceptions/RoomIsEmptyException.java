package com.Kelompok10.Exceptions;

public class RoomIsEmptyException extends Exception {
    public RoomIsEmptyException(String roomCode) {
        super("Ruangan dengan kode " + roomCode + " kosong!!");
    }
}

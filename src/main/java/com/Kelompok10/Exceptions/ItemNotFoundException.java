package src.main.java.com.Kelompok10.Exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String s) {
        super("Item tidak ditemukan!");
    }
}

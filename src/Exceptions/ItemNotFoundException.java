package src.Exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String s) {
        super("Item tidak ditemukan!");
    }
}

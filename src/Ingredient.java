package src;

public abstract class Ingredient {
    private String nama;
    private int price;
    private int kekenyangan;

    public Ingredient(String nama, int price, int kekenyangan) {
        this.nama = nama;
        this.price = price;
        this.kekenyangan = kekenyangan;
    }
}

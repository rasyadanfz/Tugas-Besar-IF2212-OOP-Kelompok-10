package src.Foods;

import java.util.ArrayList;

public abstract class Food {
    public ArrayList<Ingredient> bahanMasak;

    public Food(ArrayList<Ingredient> bahanMasak) {
        this.bahanMasak = bahanMasak;
    }
}

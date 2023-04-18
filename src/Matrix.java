package src;

public class Matrix {
    private String[][] instance;
    private int panjang;
    private int lebar;

    public Matrix(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
        instance = new String[panjang][lebar];

        for (int i = 0; i < panjang; i++) {
            for (int j = 0; j < lebar; j++) {
                instance[i][j] = "-";
            }
        }
    }

    public String getItem(int x, int y) {
        return instance[x-1][y-1];
    }

    public void changeItem(int x, int y, String item) {
        instance[x-1][y-1] = item;
    }

    public void printMatrix() {
        for (int i = lebar-1; i >= 0; i--) {
            for (int j = 0; j < panjang; j++) {
                System.out.print(instance[j][i] + " ");
            }
            System.out.println();
        }
    }

    public String[][] getMatrix() {
        return instance;
    }

    // public static void main(String[] args) {
    //     Matrix mat = new Matrix(7, 4);
    //     mat.changeItem(5, 4, "R1");
    //     mat.printMatrix();
    //     System.out.println(mat.getItem(1, 3));
    // }
}

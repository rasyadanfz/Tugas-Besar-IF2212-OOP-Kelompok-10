package src.main.java.com.Kelompok10;

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
                instance[i][j] = "----";
            }
        }
    }

    public String getWorldItem(int x, int y) {
        return instance[x][y];
    }

    public void changeWorldItem(int x, int y, String item) {
        instance[x][y] = item;
    }

    public String getItem(int x, int y) {
        return instance[x - 1][y - 1];
    }

    public void changeItem(int x, int y, String item) {
        instance[x - 1][y - 1] = item;
    }

    public void printMatrix() {
        for (int i = lebar - 1; i >= 0; i--) {
            for (int j = 0; j < panjang; j++) {
                System.out.print(instance[j][i] + " ");
            }
            System.out.println();
        }
    }

    public void printMatrix(Sim sim) {
        for (int i = lebar - 1; i >= 0; i--) {
            for (int j = 0; j < panjang; j++) {
                if (j + 1 == sim.getCurrentPos().getX() && i + 1 == sim.getCurrentPos().getY()) {
                    String simFirstName = getFirstWord(sim.getNamaLengkap());
                    if (simFirstName.length() < 4) {
                        System.out.print(simFirstName);
                        for (int k = simFirstName.length(); k < 4; k++) {
                            System.out.print(".");
                        }
                        System.out.print(" ");
                    } else {
                        for (int k = 0; k < 4; k++) {
                            System.out.print(sim.getNamaLengkap().charAt(k));
                        }
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(instance[j][i] + " ");
                }
            }
            System.out.println();
        }
    }

    public String[][] getMatrix() {
        return instance;
    }

    // public static void main(String[] args) {
    // Matrix mat = new Matrix(7, 4);
    // mat.changeItem(5, 4, "R1");
    // mat.printMatrix();
    // System.out.println(mat.getItem(1, 3));
    // }

    private String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) {
            return text.substring(0, index).trim();
        } else {
            return text;
        }
    }
}

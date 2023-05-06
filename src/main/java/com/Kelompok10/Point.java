package src.main.java.com.Kelompok10;

public class Point {
    private int x;
    private int y;

    public Point(int X, int Y) {
        setX(X);
        setY(Y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int X) {
        x = X;
    }

    public void setY(int Y) {
        y = Y;
    }

    public String toString() {
        String str = "<" + x + ", " + y + ">";
        return str;
    }

    // Cek apakah posisi X kedua Point sama
    public boolean horizontalEquals(Point p1, Point p2) {
        return (p1.getX() == p2.getX());
    }

    // Cek apakah posisi Y kedua Point sama
    public boolean verticalEquals(Point p1, Point p2) {
        return (p1.getY() == p2.getY());
    }

    // Cek apakah dua Point ada di lokasi yang sama
    public boolean equals(Point p) {
        return (horizontalEquals(this, p) && verticalEquals(this, p));
    }

    // Cek apakah point berada di sekitar point (1 secara vertikal, horizontal, atau
    // diagonal)
    public boolean around(Point p) {
        if (verticalEquals(this, p)) {
            if (x - 1 == p.getX() || x + 1 == p.getX()) {
                return true;
            } else {
                return false;
            }
        } else if (horizontalEquals(this, p)) {
            if (y - 1 == p.getY() || y + 1 == p.getY()) {
                return true;
            } else {
                return false;
            }
        } else if (x - 1 == p.getX() || x + 1 == p.getX()) {
            if (y - 1 == p.getY() || y + 1 == p.getY()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

package src;

public class Point {
    private int x;
    private int y;

    public Point(int X, int Y){
        setX(X);
        setY(Y);
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int X){
        x = X;
    }
    public void setY(int Y){
        y = Y;
    }
    public String toString(){
        String str = "("+ x + ", " + y + ")";
        return str;
    }
    
    // Cek apakah posisi X kedua Point sama
    public boolean horizontalEquals(Point p1, Point p2){
        return (p1.getX() == p2.getX());
    }

    // Cek apakah posisi Y kedua Point sama
    public boolean verticalEquals(Point p1, Point p2){
        return (p1.getY() == p2.getY());
    }

    // Cek apakah dua Point ada di lokasi yang sama
    public boolean equals(Point p1, Point p2){
        return (horizontalEquals(p1, p2) && verticalEquals(p1, p2));
    }
}

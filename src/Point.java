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
}

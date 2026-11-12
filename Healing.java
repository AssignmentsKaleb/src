import javax.swing.*;

public class Healing{
    
    private int x,y,dy,width,height;
    private ImageIcon pic;

    public Healing(int startX){
        x = startX;
        y = 150;
        dy = 0;
        width = 40;
        height = 40;
        pic = new ImageIcon("Healing.png");
    }

    public void move(){y += dy;}

    public void setDy(int dy) { this.dy = dy;}
    public int getX() {return x;}
    public int getY(){ return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public ImageIcon getPic() {return pic;}
}
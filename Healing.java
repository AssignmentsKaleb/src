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

    public boolean collidesWith(Characters c){
        return this.x < c.getX() + c.getW() &&
                this.x + this.width > c.getX() &&
                this.y < c.getY() + c.getHeight() &&
                this.y + this.height > c.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageIcon getPic() {
        return pic;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
    }

}

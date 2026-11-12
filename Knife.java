import javax.swing.ImageIcon;

public class Knife{
    int x,y,width,height,dy;
    private ImageIcon pic;

    public Knife(){
        x = 150;
        y = 150;
        dy = 0;
        width = 40;
        height = 40;
        pic = new ImageIcon("Knife.png");
    }

    public Knife(int randX){

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

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public ImageIcon getPic() {
        return pic;
    }

    public void setPic(ImageIcon pic) {
        this.pic = pic;
    
    }
    public void move(){
        y+= dy;
    }
}
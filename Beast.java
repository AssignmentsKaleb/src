import javax.swing.ImageIcon;

public class Beast extends Characters{

    private int x,y,dx,width,height,range,attack,health,mana;
    private ImageIcon pic;
    public Beast(){
        x = 650;
        y = 350;
        pic = new ImageIcon("Beast\\Beast.png");
        super(new ImageIcon("Beast.png"),50,150,35,700,150);
        super.setX(100);
    }
    public Beast(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("Beast.png"),range,attack,health,mana);
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
    public int getDx() {
        return dx;
    }
    public void setDx(int dx) {
        this.dx = dx;
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
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public ImageIcon getPic() {
        return pic;
    }
    public void setPic(ImageIcon pic) {
        this.pic = pic;
    }
    public void move(){
        x += dx;

        if(x<0){
            x = 0;
        }
        if (x+width > 1400){
            x = 1400 - width;
        }
    }
}


    
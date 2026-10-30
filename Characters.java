import javax.swing.ImageIcon;
import java.awt.Graphics;
public class Characters {
    
private int x,y,w,h;
private int mana, health, speed, damage;
private ImageIcon pic;


    public Characters(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        this.x = x;
        this.y = y;
        this.w = 50;
        this.h = 50;
        this.pic = pic;
        this.mana = mana;
        this.health = health;
        this.damage = attack;
        this.speed = range;
    }
public Characters(ImageIcon imageIcon, int pic2, int attack, int range, int health2, int mana2) {
        this(0, 0, imageIcon, range, attack, health2, mana2);
    }
public Characters(ImageIcon imageIcon, int y2, int i, int range, int attack, int health2, Weapon weapon) {
    this(0, 0, imageIcon, range, attack, health2, i);
}
public void drawChar(Graphics g2d){
    g2d.drawImage(pic.getImage(), x, y, w, h, null);
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


    public int getW() {
        return w;
    }


    public void setW(int w) {
        this.w = w;
    }


    public int getH() {
        return h;
    }


    public void setH(int h) {
        this.h = h;
    }


    public int getMana() {
        return mana;
    }


    public void setMana(int mana) {
        this.mana = mana;
    }


    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {
        this.health = health;
    }


    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getDamage() {
        return damage;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }
    
}

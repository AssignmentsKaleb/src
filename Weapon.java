import javax.swing.ImageIcon;

public class Weapon {

    private int damage, x,y,w,h;
    private int attackSpeed;
    private ImageIcon pic, movePic;

    public Weapon() {
        damage = 0;
        attackSpeed = 0;
        pic = new ImageIcon("pic.jpg");
        x = 0;
        y = 0;
        w = 50;
        h = 50;
    }
    public String toString(){
        return "Weapon";
    }
    public Weapon(int damage, int attackSpeed, ImageIcon pic) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.pic = pic;
        x = 0;
        y=0;
        w=50;
        h=50;
    }
}

import javax.swing.ImageIcon;

public class Ranged extends Characters{
    public Ranged(ImageIcon imageIcon, int i, int j, int k, int l, int m){
       super(new ImageIcon("Ranged.png"),50,100,50,500,250);
    }
    public Ranged(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("Ranged.png"),range,attack,health,mana);
    }
    public String toString(){
        return "Ranged Weapon:";
    }
    
}


import javax.swing.ImageIcon;
public class Swordsman extends Characters{
    public Swordsman(){
        super(new ImageIcon("Swordsman.png"),50,100,50,500,250);
    }
    public Swordsman(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("Swordsman.png"),range,attack,health,mana);
    }
    public String toString(){
        return "Swordsman";
    }
    
}




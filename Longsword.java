
import javax.swing.ImageIcon;
public class Longsword extends Characters{
    public Longsword(){
        super(new ImageIcon("Longsword.png"),50,100,50,500,250);
    }
    public Longsword(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("Longsword.png"),range,attack,health,mana);
    }
    public String toString(){
        return "Longsword";
    }
    
}


import javax.swing.ImageIcon;

public class SavageRoar  extends Characters{
    public SavageRoar(){
        super(new ImageIcon("SavageRoar.png"),50,150,35,700,150);
        super.setX(100);
    }
    public SavageRoar(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("SavageRoar.png"),range,attack,health,mana);
    }

    public String toString(){
        return "SavageRoar";
    }
    
}

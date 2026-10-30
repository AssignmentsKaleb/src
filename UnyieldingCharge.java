import javax.swing.ImageIcon;

public class UnyieldingCharge extends Characters{
    public UnyieldingCharge(){
        super(new ImageIcon("Unyielding Charge.png"),50,100,50,500,250);
    }
    public UnyieldingCharge(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("Unyielding Charge.png"),range,attack,health,mana);
    }
    public String toString(){
        return "UnyieldingCharge";
    }
    
}

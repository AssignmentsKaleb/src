import javax.swing.ImageIcon;

public class FieldRecovery  extends Characters{
    public FieldRecovery(){
        super(new ImageIcon("FieldRecovery.png"),50,150,35,700,150);
        super.setX(100);
    }
    public FieldRecovery(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("FieldRecovery.png"),range,attack,health,mana);
    }

    public String toString(){
        return "FieldRecovery";
    }
    
}

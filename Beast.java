import javax.swing.ImageIcon;

public class Beast extends Characters{
    public Beast(){
        super(new ImageIcon("Beast.png"),50,150,35,700,150);
        super.setX(100);
    }
    public Beast(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("Beast.png"),range,attack,health,mana);
    }

    public String toString(){
        return "Tank";
    }
    
}

import javax.swing.ImageIcon;

public class LongswordStrike extends Characters{
    public LongswordStrike(){
        super(new ImageIcon("LongswordStrike.png"),50,150,35,700,150);
        super.setX(100);
    }
    public LongswordStrike(int x, int y, ImageIcon pic, int range, int attack, int health, int mana){
        super(x,y,new ImageIcon("LongswordStrike.png"),range,attack,health,mana);
    }

    public String toString(){
        return "LongswordStrike";
    }
    
}


    

import javax.swing.ImageIcon;

public class Magic{

    int x,y,width,height,dy;
    private ImageIcon pic;

    public Magic(){
        x = 150;
        y = 150;
        dy = 0;
        width = 40;
        height = 40;
        pic = new ImageIcon("Magic.png");
    }
        public Magic(int xV){
            x = xV;
            y = 150;
            dy = 0;
            width = 40;
            height = 40;
            pic = new ImageIcon("Magic.png");
        }

        public boolean coolidesWith(Characters c){
            return this.x < Characters.getX() + Characters.getWidth() &&
                    this.x + this.width > Characters.getX() &&
                    this.y < Characters.getY() + Characters.getHeight() &&
                    this.y + this.height > Characters.getY();
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
        public int getWidth() {
            return width;
        }
        public void setWidth(int width) {
            this.width = width;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public int getDy() {
            return dy;
        }
        public void setDy(int dy) {
           y+=dy;
        }
        public ImageIcon getPic() {
            return pic;
        }
        public void setPic(ImageIcon pic) {
            this.pic = pic;
        }
        public void move(){
            y+=dy;
        }


}
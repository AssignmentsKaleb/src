import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage; 
import java.awt.event.*;
import java.util.ArrayList;
 

public class Game extends JPanel  implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	
	private BufferedImage back;
	private int key,timer, counter, score, lives;
	private char screen;
	private ArrayList<Characters> displayChars;
	private ArrayList <Knife> KnivesPositions;
	private ArrayList <Magic> MagicPositions;
	private ArrayList <Healing> HealingPositions;
	private ImageIcon BackgroundH;
	


	public Game() {
		
		//This is the music
		Player p = new Player();
		p.playmusic("track02.wav"); 
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		new Thread(this).start();
		
		 BackgroundH = new ImageIcon("BackgroundH.png");
		
		screen = 'S';



		
		
	}
	
	  public void run()
	   {
	   	try
	   	{
	   		while(true)
	   		{
	   		   Thread.currentThread().sleep(5);
	            repaint();
	         }
	      }catch(Exception e)
	      {
	      }
	  	}

		public void screen(Graphics g2d){
			
			switch(screen){

		
			
				//if (timer% 10 == 0){ if timer>=2000) g2d.drawString("Fantasy Time", 400, 150);
				//timer++;
				//if(timer% 10 == 0)
				//if(timer>=3000)
				//g2d.drawstring("Enter",400,200);

			
				
				case 'S':
					g2d.drawImage(BackgroundH.getImage(),0,0,null);
					g2d.setFont(new Font("Arial", Font.BOLD, 30));
					g2d.setColor(Color.RED);
					g2d.drawString("Once Upon Fantasy ", getWidth()/2-150, getHeight() / 2 - 100);
					g2d.drawString("Enter to Start", getWidth()/2-100, getHeight() / 2);
					break;


					case 'G':
						g2d.drawImage(BackgroundH.getImage(), 0,0, getWidth(), getHeight(), this);
                        counter++;
						getHealingPositions(g2d);
						getMagicPositions(g2d);
						getKnifePositions(g2d);

			            drawCharacters(g2d);
						charactersMove();

						checkCollisions();

						g2d.setColor(Color.RED);
						g2d.setFont(new Font("Arial", Font.BOLD, 24));
						g2d.drawString("Score: " + score, 10, 30);
						g2d.drawString("Lives: " + lives, 10, 60);
						break;

						case 'W':
							g2d.drawImage(BackgroundH.getImage(), 0,0, getWidth(), getHeight(), this);
							g2d.setFont(new Font("Arial", Font.BOLD, 30));
							g2d.setColor(Color.RED);
							g2d.drawString("You Win!", getWidth()/2-100, getHeight() / 2 - 100);
							g2d.drawString("Score: " + score, getWidth()/2-100, getHeight() / 2);
							break;

						case 'L':	
	
							g2d.setFont(new Font("Arial", Font.BOLD, 30));
							g2d.setColor(Color.RED);
							g2d.drawString("I'm Defeated Enter!", getWidth()/2-100, getHeight() / 2 - 100);
							g2d.drawString("Score: " + score, getWidth()/2-100, getHeight() / 2);
							break;

			}

		}
	
	private void getMagicPositions(Graphics g2d) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'getMagicPositions'");
		}

	private void getKnifePositions(Graphics g2d) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'getKnifePositions'");
		}

	private void checkCollisions() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'checkCollisions'");
		}

	private void charactersMove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'charactersMove'");
		}

	private void drawCharacters(Graphics g2d) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'drawCharacters'");
		}

	private void getHealingPositions(Graphics g2d) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException("Unimplemented method 'getHealingPositions'");
		}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		//mainChar.setX(e.getX());
	}
	
	  
	  public  void paint(Graphics g){
	  
		  Graphics2D  twoDgraph= (Graphics2D)g;
		
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));


		Graphics g2d = back.createGraphics();
	
		
		g2d.clearRect(0,0,getSize().width, getSize().height);
		screen(g2d);
		if(screen =='G'){
			if(counter % 300 == 0){
				int randX = 10 + (int)(Math.random() * ((getWidth() - 40 )));
				HealingPositions.add(new Healing(randX));
			}

			for (int i = 0; i < HealingPositions.size(); i++) {
				Healing h = HealingPositions.get(i);
				h.move();
				g2d.drawImage(h.getPic().getImage(), h.getX(), h.getY(), h.getWidth(), h.getHeight(), null);

				Rectangle hRect = new Rectangle(h.getX(), h.getY(), h.getWidth(), h.getHeight());
				Rectangle cRect = new Rectangle(h.getX(), h.getY(), h.getWidth(), h.getHeight());
				if (hRect.intersects(cRect)) {
					HealingPositions.remove(i);
					i--;
					lives++;
		            lives = Math.min(5, lives + 1); // Increase lives by 1, but not above 5
					if (lives <= 0){
						screen = 'L';
					}
					else if (h.getY() > getHeight()) {
						HealingPositions.remove(i);
						i--;
					}
					if (counter % 190 == 0){
						int randX2 = 10 + (int)(Math.random() * ((getWidth() - 40 )));
						MagicPositions.add(new Point(randX2, 0));
					}
				}
			}
		}
		
		


		//do not delte this line of code, it draws everything above
		twoDgraph.drawImage(back, null, 0, 0);
		
		
		
		
		
	}
	
}

}
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
	private ImageIcon BackgroundB;
	private ImageIcon BackgroundS;
	private ImageIcon BackgroundH;
	private ImageIcon BackgroundD;


	public Game() {
		
		//This is the music
		Player p = new Player();
		p.playmusic("track02.wav"); 
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		new Thread(this).start();
		 BackgroundB = new ImageIcon("BackgroundB.png");
		 BackgroundS = new ImageIcon("BackgroundS.png");
		 BackgroundH = new ImageIcon("BackgroundH.png");
		 BackgroundD = new ImageIcon("BackgroundD.png");
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
			if(back==null)
				back = (BufferedImage)(createImage(getWidth(),getHeight()));
			Graphics g2 = back.createGraphics();
			g2.clearRect(0,0,getSize().width, getSize().height);
		}
		public void screen(Graphics g2d){
			switch(screen){
				//if (timer% 10 == 0){ if timer>=2000) g2d.drawString("Fantasy Time", 400, 150);
				//timer++;
				//if(timer% 10 == 0)
				//if(timer>=3000)
				//g2d.drawstring("Enter",400,200);

			
				
				case 'S':
					g2d.drawImage(BackgroundB.getImage(),0,0,null);
					g2d.setFont(new Font("Arial", Font.BOLD, 30));
					g2d.setColor(Color.RED);
					g2d.drawString("Once Upon Fantasy ", getWidth()/2-150, getHeight() / 2 - 100);
					g2d.drawString("Enter to Start", getWidth()/2-100, getHeight() / 2);
					break;


					case 'G':
						g2d.drawImage(BackgroundB.getImage(), 0,0, getWidth(), getHeight(), this);
                        counter++;
						getHealingPositions(g2d);

						drawCharacters(g2d);
						charactersMove();

						checkCollisions();

						g2d.setColor(Color.RED);
						g2d.setFont(new Font("Arial", Font.BOLD, 24));
						g2d.drawString("Score: " + score, 10, 30);
						g2d.drawString("Lives: " + lives, 10, 60);
						break;
			}
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
		//start coding here
		
		


		//do not delte this line of code, it draws everything above
		twoDgraph.drawImage(back, null, 0, 0);
		
		
		
		
		
	}
	
}
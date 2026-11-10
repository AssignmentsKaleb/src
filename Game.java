import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage; 
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class Game extends JPanel  implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	
	private BufferedImage back;
	private int key, counter, score, lives;
	private ArrayList<Characters> displayChars;
	private String level;
	


	
	public Game() {
		
		//This is the music
		Player p = new Player();
		p.playmusic("track02.wav"); 
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		new Thread(this).start();
		
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{

	
	public Main () {
		super("Fantasy ");
		setSize(2250, 1200);
		setLocationRelativeTo(null);
		setResizable(false);

		Game play = new Game();
		getContentPane().add(play);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	public static void main(String[] args) {
		Main run = new Main();
		

	}


}

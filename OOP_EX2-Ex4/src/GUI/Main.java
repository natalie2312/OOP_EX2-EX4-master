package GUI;
import java.awt.Image;

import javax.swing.JFrame;


public class Main 
{
	public static void main(String[] args)
	{
		MainWindow window = new MainWindow();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
	}
}

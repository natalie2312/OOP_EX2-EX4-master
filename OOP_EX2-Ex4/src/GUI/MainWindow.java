package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Geom.Point3D;


public class MainWindow extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	ArrayList<Point3D> pacman = new ArrayList<Point3D>();
	ArrayList<Point3D> fruit = new ArrayList<Point3D>();
	int choice = 0 ;

	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
    }
		
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem item1 = new MenuItem("Pacman");
		MenuItem item2 = new MenuItem("Fruit");
		
		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		this.setMenuBar(menuBar);
		
	       item1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent listnerP) {
	            	
	            	if (listnerP.getActionCommand().equals("Pacman")) {
	            		System.out.println("you chose pacman.");
	            		choice = 1; 
	            	}

	            }});
	       
	       item2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent listnerF) {
	            	
	            	if (listnerF.getActionCommand().equals("Fruit")) {
	            		System.out.println("you chose fruit.");
	            		choice = 2; 
	            	}

	            }});

	       
		try {
			 myImage = ImageIO.read(new File("C:\\Users\\micha\\eclipse-workspace\\cont\\Ariel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

//	int x = -1;
//	int y = -1;

	public void paint(Graphics g)
	{
		g.drawImage(myImage,0,0,this.getWidth(),this.getHeight(),this); // resizing the picture 
		//g.drawImage(myImage, 0, 0, this);
	
		Iterator<Point3D> pac_iterator = pacman.iterator();
		Iterator<Point3D> fruit_iterator = fruit.iterator();
		int rP= 30;
		int rF = 20;
		Color pacman = new Color(255, 255, 113); // Color white
		Color fruit = new Color(255, 102, 102); // red color for fruit

		
		while(pac_iterator.hasNext()) {
			Point3D onPoint = pac_iterator.next();
			int pX = (int)onPoint.x();
			int pY = (int)onPoint.y();
			g.setColor(pacman);
			g.fillOval(pX,pY,rP,rP);
		}

		while(fruit_iterator.hasNext()) {
			Point3D onPoint = fruit_iterator.next();
			int pX = (int)onPoint.x();
			int pY = (int)onPoint.y();
			g.setColor(fruit);
			g.fillOval(pX,pY,rF,rF);
		
		}
	
		
		
//		if(x!=-1 && y!=-1)
//		{
//			int r = 10;
//			x = x - (r / 2);
//			y = y - (r / 2);
//			g.fillOval(x, y, r, r);
//		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
//		int x = arg.getX();
//		int y = arg.getY();

	int pointX= arg.getX();
	int pointY= arg.getY();
		if(choice == 1) {
		pacman.add(new Point3D(pointX,pointY,0));	
		}
		
		if(choice == 2) {
			fruit.add(new Point3D(pointX,pointY,0));	
		}
		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

//	public void resizeWindow(int newWidth,int  newHeight){
//		System.out.println("inside resize window");
//		Image img = myImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//     
//	}
	


}

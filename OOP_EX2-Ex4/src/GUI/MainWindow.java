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
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algorithm.ShortestPathAlgo;
import GIS.Game;
import GIS.Map;
import GIS.Packman;
import GIS.Path;
import GIS.fruit;
import Geom.Point3D;


public class MainWindow extends JFrame implements MouseListener
{
	public BufferedImage myImage ;
	public BufferedImage fruitImage ;
	public BufferedImage pacImage ;
	int choice = 0 ;
	Game game = new Game();
	Map map;
	ArrayList<Path> arr;

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
		MenuItem item3 = new MenuItem("Play");
		MenuItem item4 = new MenuItem("Clear");
		MenuItem item5 = new MenuItem("Load file");
		MenuItem item6 = new MenuItem("save game");


		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
		menu.add(item5);
		menu.add(item6);
		this.setMenuBar(menuBar);

		//listner for each menu item


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

		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent listnerPlay) {
				if (listnerPlay.getActionCommand().equals("Play")) {
					choice = 3; 
					System.out.println("you chose play.");

					if(game.isEmpty())
						System.out.println("cant play an empty game.");
					else
					{
						ShortestPathAlgo a= new ShortestPathAlgo(game);
						arr= a.solution();
					}


				}
				repaint();
			}});

		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent listenClear) {
				if (listenClear.getActionCommand().equals("Clear")) {
					System.out.println("you chose clear.");
					choice = 4; //clears the array lists and the photo 
					game.getFruits().clear();
					game.getPackmans().clear();
					repaint();
				}
			}});

		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("CSV file", "csv"));//filters the files that shown just to csv files
				chooser.showOpenDialog(null);
				File chosenFile = chooser.getSelectedFile();
				game = new Game(chosenFile.getPath());
				choice = 5;
				repaint();
				//לשלוח את הכתובת של הפייל שקיבלנו למפה
			}
		});

		item6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent saveGame) {
				if (saveGame.getActionCommand().equals("save game")) {
					if(game.isEmpty())
						System.out.println("cant save an empty game.");
					else
						game.toCSV("game");
				}
			}});

		try {
			myImage = ImageIO.read(new File("C:\\Users\\Natalie\\eclipse-workspace\\OOP_EX2-EX4-master\\Ariel1.jpg"));
			fruitImage = ImageIO.read(new File("fruit.png"));
			pacImage = ImageIO.read(new File("caricon12.gif_c200"));
			map = new Map(myImage);
			map = new Map(myImage);
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void paint(Graphics g)
	{
		g.drawImage(myImage,0,0,this.getWidth(),this.getHeight(),this); // resizing the picture 
		//g.drawImage(myImage, 0, 0, this);

		Iterator <Packman> it = game.getPackmans().iterator();
		Iterator<fruit> it2 = game.getFruits().iterator();

		int rP= 30;
		int rF = 20;
		Color pacman = new Color(255, 255, 113); // Color yellow
		Color fruit = new Color(255, 102, 102); // red color for fruit
		Color line = new Color(255, 255, 255); //color white


		if(choice!=4) {
			while(it.hasNext()) {
				Packman p = it.next();
				Point3D pp = p.getGps();
				//				int pX = (int)pp.x();
				//				int pY = (int)pp.y();
				Point3D npp = map.coords2pics(pp);
				System.out.println("to pix, x:"+npp.x()+", y:"+npp.y());
				int pX = (int)npp.x();
				int pY = (int)npp.y();
				g.drawImage(pacImage, pX, pY,40,40,this);

				//				g.setColor(pacman);
				//				g.fillOval(pX,pY,rP,rP);
			}

			while(it2.hasNext()) {
				fruit f =it2.next();
				Point3D pf = f.getGps();
				//				int fX = (int)pf.x();
				//				int fY = (int)pf.y();
				Point3D npf = map.coords2pics(pf);
				int fX = (int)npf.x();
				int fY = (int)npf.y();
				g.drawImage(fruitImage, fX, fY,20,20,this);
				//				g.setColor(fruit);
				//				g.fillOval(fX,fY,rF,rF);

			}
		}

		if(choice==3) {
			Iterator<Path> it1= arr.iterator();
			while(it1.hasNext()) {
				Path path = it1.next();
				for(int i=1; i<path.size();i++) {
					Point3D sec = path.get(i);
					sec= map.coords2pics(sec);
					Point3D first = path.get(i-1);
					first = map.coords2pics(first);
					g.setColor(line);
					g.drawLine((int)first.x(),(int) first.y(),(int) sec.x(), (int)sec.y());
				}
			}	
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");

		int pointX= arg.getX();
		int pointY= arg.getY();

		if(choice == 1) {
			Point3D p = new Point3D(pointX,pointY,0);
			Point3D p2c= map.pics2coords(p);
			System.out.println("to coord, x:"+p2c.x()+", y:"+p2c.y());
			Packman pac = new Packman (1, p2c,1,1);
			//Packman pac= new Packman(0,p,1,1);   //מכניס למפה את הנקודה בפיקסלים ולא בקאורדינטות
			game.getPackmans().add(pac);
		}

		if(choice == 2) {
			Point3D p = new Point3D(pointX,pointY,0);
			Point3D p2c= map.pics2coords(p);
			//fruit fr= new fruit(0,p,1);   //מכניס למפה את הנקודה בפיקסלים ולא בקאורדינטות
			fruit fr = new fruit (1, p2c,1);
			game.getFruits().add(fr);
		}



		if(choice ==4) {
			game.getFruits().clear();
			game.getPackmans().clear();
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

}

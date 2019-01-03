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
import File_format.Path2Kml;
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
	ArrayList<Path> arr2;
	double x ;
	double y; 

	boolean flag = false;

	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}

	private ArrayList<Path> copyArray(ArrayList a){
		ArrayList<Path> arrM = new ArrayList<Path>();
		for(int i=0; i<a.size(); i++) {
			arrM.add((Path) a.get(i));
		}

		return arrM; 
	}

	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Game"); 
		Menu menu1 = new Menu("Creation"); 
		Menu menu2 = new Menu("Kml");
		MenuItem item1 = new MenuItem("Pacman");
		MenuItem item2 = new MenuItem("Fruit");
		MenuItem item3 = new MenuItem("Play");
		MenuItem item4 = new MenuItem("Clear");
		MenuItem item5 = new MenuItem("Load file");
		MenuItem item6 = new MenuItem("save game");
		MenuItem item7 = new MenuItem("end game");
		MenuItem item8 = new MenuItem("csv to kml");


		menuBar.add(menu);
		menuBar.add(menu1);
		menuBar.add(menu2);


		menu1.add(item1);
		menu1.add(item2);
		menu.add(item3);
		menu1.add(item4);
		menu.add(item5);
		menu.add(item6);
		menu.add(item7);
		menu2.add(item8);


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
						arr= new ShortestPathAlgo(game).getSolution();
						
						


					}
					repaint();

				}

			}});

		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent listenClear) {
				if (listenClear.getActionCommand().equals("Clear")) {
					System.out.println("you chose clear.");
					choice = 4; //clears the array lists and the photo 
					game.getFruits().clear();
					game.getPackmans().clear();
					repaint();
					if(arr!=null)
						arr.clear();
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
			}
		});

		item6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent saveGame) {
				if (saveGame.getActionCommand().equals("save game")) {
					if(game.isEmpty())
						System.out.println("cant save an empty game.");
					else
						game.toCSV("new game");
				}
			}});



		item7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent saveGame) {
				if (saveGame.getActionCommand().equals("end game")) {
					System.exit(0);
				}
			}});

		item8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent saveGame) {
				if (saveGame.getActionCommand().equals("csv to kml")) {
					Game game= new Game("new game.csv");
					ArrayList<Path> a= new ShortestPathAlgo(game).getSolution();
					Path2Kml p= new Path2Kml("kmlFile", a);
					System.out.println("you have created a Kml file");
				}
			}});

		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
			fruitImage = ImageIO.read(new File("cherry.png"));
			pacImage = ImageIO.read(new File("pac.gif_c200"));
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

		Color line = new Color(255, 255, 255); //color white


		if(choice!=4) {
			while(it.hasNext()) {
				Packman pacman = it.next();
				Point3D pacmanGPS = pacman.getGps();
				Point3D pacmanPix= map.coords2pics(pacmanGPS,this.getHeight(),this.getWidth());
				int pX = (int)pacmanPix.x();
				int pY = (int)pacmanPix.y();
				g.drawImage(pacImage, pX, pY,40,40,this);


			}

			while(it2.hasNext()) {
				fruit fruit =it2.next();
				Point3D fruitGPS = fruit.getGps();

				Point3D fruitPIX = map.coords2pics(fruitGPS,this.getHeight(),this.getWidth());

				int fX = (int)fruitPIX.x();
				int fY = (int)fruitPIX.y();
				g.drawImage(fruitImage, fX, fY,30,30,this);


			}
		}

		if(choice==3) {
			Iterator<Path> it1= arr.iterator();
			while(it1.hasNext()) { //moves on all pathes. for each pacman there is a path. 
				Path path = it1.next();
				for(int i=1; i<path.size();i++) {
					Point3D sec = path.get(i);
					sec= map.coords2pics(sec,this.getHeight(),this.getWidth());
					Point3D first = path.get(i-1);
					first = map.coords2pics(first,this.getHeight(),this.getWidth());
					g.setColor(line);
					g.drawLine((int)first.x(),(int) first.y(),(int) sec.x(), (int)sec.y());


				}
			}	
		}

//		if(flag) {
//			g.drawImage(pacImage, (int)x,(int) y,40,40,this);	
//		}


	}
	
	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");

		int pointX= arg.getX();
		int pointY= arg.getY();

		System.out.println("("+ pointX + "," + pointY +")");


		if(choice == 1) {
			Point3D p = new Point3D(pointX,pointY,0);
			Point3D p2c= map.pics2coords(p,this.getHeight(),this.getWidth());
			System.out.println("to coord, x:"+p2c.x()+", y:"+p2c.y());
			Packman pac = new Packman (1, p2c,1,1);
			//Packman pac= new Packman(0,p,1,1);   //מכניס למפה את הנקודה בפיקסלים ולא בקאורדינטות
			game.getPackmans().add(pac);
		}

		if(choice == 2) {
			Point3D p = new Point3D(pointX,pointY,0);
			Point3D p2c= map.pics2coords(p,this.getHeight(),this.getWidth());
			//fruit fr= new fruit(0,p,1);   //מכניס למפה את הנקודה בפיקסלים ולא בקאורדינטות
			fruit fr = new fruit (1, p2c,1);
			game.getFruits().add(fr);
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


//	public void paintPacman () {
//		Iterator<Path> iter2= arr.iterator();
//		while(iter2.hasNext()) { //moves on all pathes. for each pacman there is a path. 
//			Path path =iter2.next();
//			for(int i=1; i<path.size();i++) {
//				Point3D sec = path.get(i);
//				sec= map.coords2pics(sec,this.getHeight(),this.getWidth());
//
//				x= sec.x();
//				y= sec.y();
//
//				revalidate();
//				repaint();
//
//				try {
//					Thread.sleep(1000);
//
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//
//			}
//		}
//	}
}
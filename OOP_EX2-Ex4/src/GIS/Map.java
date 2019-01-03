package GIS;

import java.awt.image.BufferedImage;

import Coords.MyCoords;
import Geom.Point3D;
import javafx.scene.image.Image;
import Coords.MyCoords;

public class Map {

	private BufferedImage image;
//	private Point3D gps1 = new Point3D(32.106734,35.202104);
//	private Point3D gps2 = new Point3D(32.102339, 35.212495);
	private Point3D gps1 = new Point3D(32.105766,35.201957);
	private Point3D gps2 = new Point3D(32.102092,35.212226);
	private Point3D gps3;
	private double high;
	private double width;
	private MyCoords myCoords;
	private int pixWid = 1433;
	private int pixhi = 642;
//קאורדינאטות לפיקסלים
	private double unitX ; //= pixWid / (Math.abs(gps2.y() - gps1.y()));
	private double unitY ;// = pixhi / (Math.abs(gps2.x() - gps1.x()));

//פיקסלים לקאורדניטאות
	private double unitX1 ;//= (Math.abs(gps2.y() - gps1.y())) / pixWid;
	private double unitY1 ;//= (Math.abs(gps2.x() - gps1.x())) / pixhi;
	
	
//	private double unitX1 = (Math.abs(gps2.x() - gps1.x())) / pixWid;
//	private double unitY1 = (Math.abs(gps2.y() - gps1.y())) / pixhi;


	
	
	
	public Map(BufferedImage myImage) {
		this.image = myImage;
		this.high = myImage.getHeight();
		this.width = this.image.getWidth();
		gps3 = new Point3D(this.gps1.x(), this.gps2.y());
		myCoords = new MyCoords();
		
		System.out.println("unitx" + unitX);
		System.out.println("unity" + unitY);
	}

	public Map(int height, int width2) {
		// TODO Auto-generated constructor stub
		this.high= height;
		this.width= width2;
		myCoords = new MyCoords();

	}

	public Point3D coords2pics(Point3D gps, int height,int width ) {
//original
	 unitX = width / (Math.abs(gps2.y() - gps1.y()));
	 unitY = height / (Math.abs(gps2.x() - gps1.x()));
		
		double disY = Math.abs(gps.x() - gps1.x());
		double disX = Math.abs(gps.y() - gps1.y());
		double x = disX * unitX;
		double y = disY * unitY;
		

		// של נטלי
//		 double disX1= gps.x()-gps1.x();
//		 double disX2= gps2.x()- gps1.x();
//		 double x= (disX1/disX2)*this.width;
//		
//		 double disY1= gps.y()-gps1.y();
//		 double disY2= gps2.y()- gps1.y();
//		 double y= (disY1/disY2)*this.high;

		return new Point3D(x,y);
	}

	public Point3D pics2coords(Point3D gps, int height,int width) {
		
		 unitX1 = (Math.abs(gps2.y() - gps1.y())) / width;
		 unitY1 = (Math.abs(gps2.x() - gps1.x())) / height;
		
		double y= gps1.y() - gps.x()*unitX1;
		System.out.println("gps1: "+ gps1.x() +"gps.x*unitX = "+(gps.x()*unitX));
		double x= gps1.x() - gps.y()*unitY1;
		System.out.println("x: " + x);
		System.out.println("to coords , x: " +x + "to y: " + y);
		
		
		



//		double disX = gps.x() / unitX;
//		double disY = gps.y() / unitY;
//		double x = gps1.x() - disY;
//		double y =  gps1.y() - disX;

		// נטלי
//		 double disX1= gps.x()/this.width;
//		 double disX2= gps2.x()-gps1.x();
//		 double x= disX1*disX2 +gps1.x();
//		
//		 double disY1= gps.y()/this.high;
//		 double disY2= gps2.y()-gps1.y();
//		 double y= disY1*disY2 +gps1.y();

		return new Point3D(x,y);
	}

	public double distance(Point3D p1, Point3D p2) {
		return p1.distance2D(p2);
	}

	public double azimut(Point3D p1, Point3D p2) {
		Point3D point1 = pics2coords(p1,642,1433);
		Point3D point2 = pics2coords(p2,642,1433);
		MyCoords myCoords = new MyCoords();
				
		double azi = myCoords.azimuth_elevation_dist(point1, point2)[0];
		return azi;
	}

	public BufferedImage getImage() {
		return image;
	}

}
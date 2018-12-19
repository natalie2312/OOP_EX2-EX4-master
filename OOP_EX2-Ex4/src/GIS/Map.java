package GIS;

import java.awt.image.BufferedImage;

import Coords.MyCoords;
import Geom.Point3D;
import javafx.scene.image.Image;
import Coords.MyCoords;


public class Map {

	private BufferedImage image;
	private Point3D gps1= new Point3D(32.106046, 35.202574);
	private Point3D gps2= new Point3D(32.101858, 35.212405);
	private Point3D gps3;
	private double high;
	private double width;
	private MyCoords myCoords;

	public Map(BufferedImage myImage) {
		this.image= myImage;
		this.high= myImage.getHeight();
		this.width= this.image.getWidth();
		gps3= new Point3D(this.gps1.x(), this.gps2.y());
        myCoords = new MyCoords();

	}

	public Point3D coords2pics(Point3D gps) {

		double disX1= gps.x()-gps1.x();
		double disX2= gps2.x()- gps1.x();
		double x= (disX1/disX2)*this.width;
		
		double disY1= gps.y()-gps1.y();
		double disY2= gps2.y()- gps1.y();
		double y= (disY1/disY2)*this.high;

		return new Point3D(x,y);
	}

	public Point3D pics2coords(Point3D gps) {

		double disX1= gps.x()/this.width;
		double disX2= gps2.x()-gps1.x();
		double x= disX1*disX2 +gps1.x();
		
		double disY1= gps.y()/this.high;
		double disY2= gps2.y()-gps1.y();
		double y= disY1*disY2 +gps1.y();

		return new Point3D(x,y);
	}


	public double distance(Point3D p1, Point3D p2) {
		return p1.distance2D(p2);
	}
	
	public double azimut(Point3D p1, Point3D p2) {
		Point3D pp= pics2coords(p1);
        Point3D ppp= pics2coords(p2);
        MyCoords myCoords = new MyCoords();
		double azi= myCoords.azimuth_elevation_dist(pp,ppp)[0];
		return azi;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}

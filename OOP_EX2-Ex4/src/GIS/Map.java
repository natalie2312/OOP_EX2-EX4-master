package GIS;

import java.awt.image.BufferedImage;

import Coords.MyCoords;
import Geom.Point3D;
import javafx.scene.image.Image;
import Coords.MyCoords;


public class Map {

	private BufferedImage image;
	private Point3D gps1;
	private Point3D gps2;
	private Point3D gps3;
	private double high;
	private double width;
	private MyCoords myCoords;

	public Map(BufferedImage myImage, Point3D gps1, Point3D gps2) {
		this.image= myImage;
		this.gps1= gps1;
		this.gps2= gps2;
		this.high= myImage.getHeight();
		width= this.image.getWidth();
		gps3= new Point3D(this.gps1.x(), this.gps2.y());
        myCoords = new MyCoords();

	}

	public Point3D coords2pics(Point3D gps) {

		Point3D v= this.myCoords.vector3D(gps1, gps);
		double disY = this.myCoords.distance3d( gps3,gps1);
		System.out.println(disY);
		double y= v.y()* this.image.getHeight()/disY;
		double disX= this.myCoords.distance3d(gps3, gps2);
		double x= v.x()* (this.image.getWidth()/ disX);

		return new Point3D(x,y);
	}

	public Point3D pics2coords(Point3D gps) {

		double disY = this.myCoords.distance3d(gps1, gps3);
		double y= gps.y()* (disY/this.high);
		double disX= this.myCoords.distance3d(gps2, gps3);
		double x= gps.x()* (this.width/disX);

		return new Point3D(x,y);
	}


	public double distance(Point3D p1, Point3D p2) {
		return p1.distance2D(p2);
	}
	
	public double azimut(Point3D p1, Point3D p2) {
        MyCoords myCoords = new MyCoords();
		double azi= myCoords.azimuth_elevation_dist(p1,p2)[0];
		return azi;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}

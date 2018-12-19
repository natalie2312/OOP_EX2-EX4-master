package GIS;

import Geom.Point3D;

public class fruit {

	private int id;
	private Point3D gps;
	private int weigth;

	public fruit(int id, Point3D gps, int weidth) {
		this.id= id;
		this.gps= gps;
		this.weigth= weidth;
	}

	public int getId() {
		return id;
	}
	
	public Point3D getGps() {
		return gps;
	}

	public int getWeigth() {
		return weigth;
	}

}

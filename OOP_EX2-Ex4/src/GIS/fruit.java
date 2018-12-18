package GIS;

import Geom.Point3D;

public class fruit {

	private Point3D gps;
	private int weigth;

	public fruit(Point3D gps, int weidth) {
		this.gps= gps;
		this.weigth= weidth;
	}

	public Point3D getGps() {
		return gps;
	}

	public int getWeigth() {
		return weigth;
	}

}

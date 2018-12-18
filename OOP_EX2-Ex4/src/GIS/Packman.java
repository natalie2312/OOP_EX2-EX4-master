package GIS;

import Geom.Point3D;

public class Packman {

	private int id;
	private Point3D gps;
	private int speed;
	private int radius;
    private int score = 0;
	
	public Packman(int id, Point3D gps, int speed, int radius) {
		this.id= id;
		this.gps= gps;
		this.speed= speed;
		this.radius= radius;
	}

	public int getId() {
		return id;
	}

	public Point3D getGps() {
		return gps;
	}

	public void setGps(Point3D gps) {
		this.gps = gps;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public double getScore() {
        return score;
    }

    public void addScore(int scoreToAdd) {
        score += scoreToAdd;
    }
	
	
}

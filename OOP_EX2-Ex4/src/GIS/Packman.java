package GIS;

import Geom.Point3D;

public class Packman {

	private Point3D gps;
	private int speed;
	private int radius;
    private int score = 0;
    private fruit closeFruit;
    private double timeToFruit=0;
	
	public Packman(Point3D gps, int speed, int radius) {
		this.gps= gps;
		this.speed= speed;
		this.radius= radius;
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
	
    public fruit getCloseFruit() {
    	return this.closeFruit;
    }
    
    public void setTimeToFruit(double t) {
    	this.timeToFruit+= t;
    }
    
//    public void setCloseFruit(fruit f) {
//    	this.closeFruit= f;
//    }
//    
//    public void setCloseFruit(fruit f) {
//    	this.closeFruit= f;
//    }
//	
}

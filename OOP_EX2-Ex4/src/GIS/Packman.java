package GIS;

import Geom.Point3D;

public class Packman {

	private int id;
	private Point3D gps;
	private int speed;
	private int radius;
    private double score = 0;
    private fruit closeFruit;
    private double time=0;
    private double timeToFruit;
    private Path path= new Path();
	
	public Packman(int id, Point3D gps, int speed, int radius) {
		this.id= id;
		this.gps= gps;
		this.speed= speed;
		this.radius= radius;
		path.add(gps);
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
	
	public void setScore(double d) {
        this.score= d;
    }
    
    public fruit getCloseFruit() {
    	return this.closeFruit;
    }
    
    public void setCloseFruit(fruit f) {
    	this.closeFruit= f;
    }
    
    public double getTime() {
    	return this.time;       // זמן שהוא כבר עבר
    }
    
    public void setTime(double t) {
    	this.time+= t;       // זמן שהוא כבר עבר
    }
    
    public double getTimeToFruit() {
    	return this.timeToFruit;
    }
    
    public void setTimeToFruit(double t) {
    	this.timeToFruit= t;
    }
	
    public Path getPath() {
    	return this.path;
    }
    
}

package GIS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Geom.Point3D;

public class Path extends ArrayList<Point3D> {

	ArrayList<Point3D> path;

	double[] time;
	int i=0;

	public Path() {
		path = new ArrayList<Point3D>();
		time = new double[100];
	}

	public double length() {
		double sum=0;
		Iterator<Point3D> it= path.iterator();	
		Point3D p= it.next(); 

		while(it.hasNext())   // while the layer has more elements
		{
			Point3D p2= it.next();
			sum+= p.distance2D(p2);
			p= p2;
		}
		return sum;
	}

	public ArrayList<Point3D> getPath() {
		return path;
	}

	public double[] getTime() {
		return this.time;       // זמן שהוא כבר עבר
	}

	public double getTimeToFruit(int index) {
		return time[index];
	}

	public void addPointTime(double t) {
		time[i+1] = time[i]+ t; 
		i++; // זמן שהוא כבר עבר
	}

	public Point3D LestIndex() {
		// TODO Auto-generated method stub
		return path.get(path.size()-1);
	}

}
package GIS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Geom.Point3D;

public class Path extends ArrayList<Point3D> {

	ArrayList<Point3D> path = new ArrayList<Point3D>();
	double[] time = new double[path.size()];
	int i=1;
	
	public Path() {
    	time[0]= 0;
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
	
	   public double[] getTime() {
	    	return this.time;       // זמן שהוא כבר עבר
	    }
	    
	    public void addPointTime(double t) {
	    	time[i++]= t;;       // זמן שהוא כבר עבר
	    }
	    
}
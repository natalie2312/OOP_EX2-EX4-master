package GIS;

import java.util.HashSet;
import java.util.Iterator;

import Geom.Point3D;

public class Path extends HashSet<Point3D> {

	HashSet<Point3D> path = new HashSet<>();
	
	public Path() {
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
}

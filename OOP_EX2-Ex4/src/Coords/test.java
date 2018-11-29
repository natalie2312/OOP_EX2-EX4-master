package Coords;

import Geom.Point3D;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point3D a= new Point3D(32.103315, 35.209039, 670);
		Point3D b= new Point3D(32.106352, 35.205225, 650);
		Point3D c= new Point3D(337.699, -359.249, -20);
		
		MyCoords l= new MyCoords();
		System.out.println(l.add(a, c));
		
		System.out.println(l.vector3D(a, b));
		System.out.println(l.distance3d(a, b));
		System.out.println(l.isValid_GPS_Point(a));
		
		
		}
		

}

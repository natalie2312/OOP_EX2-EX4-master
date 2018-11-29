package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter {

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		int r= 6371000;
		double lon= Math.cos(Math.toRadians(gps.x()));
		
		double x= Math.asin(local_vector_in_meter.x()/r);
		double y= Math.asin(local_vector_in_meter.y()/(r*lon));
		double z= gps.z()+local_vector_in_meter.z();
		
		x= Math.toDegrees(x)+ gps.x();
		y= Math.toDegrees(y)+ gps.y();
		
		return new Point3D(x,y,z);
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D p= vector3D(gps0,gps1);
		return Math.sqrt(p.x()*p.x()+ p.y()*p.y());
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double r= 6371000;
		double lon= Math.cos(Math.toRadians(gps0.x()));
		
		double x= r*Math.sin(Math.toRadians((gps1.x()-gps0.x())));
		double y= lon*r*Math.sin(Math.toRadians((gps1.y()-gps0.y())));
		double z= gps1.z()-gps0.z();
		
		return new Point3D(x,y,z);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] arr= new double[3];

		arr[0]= azimuth(gps0,gps1);
		arr[2]= distance3d(gps0,gps1);
		arr[1]= Math.toDegrees(Math.asin((gps1.z()-gps0.z())/arr[2]));

		return arr;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if(p.x()<-90 || p.x()>90)
			return false;
		if(p.y()<-180 || p.y()>180)
			return false;
		if(p.z()<-450)
			return false;
		return true;
	}

	private double azimuth(Point3D p1, Point3D p2) {
		Point3D p= vector3D(p1,p2);
		double dx= p.x();
		double dy= p.y();
		double a= Math.toDegrees(Math.atan(Math.abs(dy/dx)));

		if(dx>0 && dy>0)
			return a;
		else if(dx<0 && dy>0)
			return 180-a;
		else if(dx<0 && dy<0)
			return 180+a;
		else
			return 360-a;
	}

}

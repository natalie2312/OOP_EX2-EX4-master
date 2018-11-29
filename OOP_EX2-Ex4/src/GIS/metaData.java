package GIS;

import Coords.MyCoords;
import Geom.Point3D;

public class metaData implements Meta_data  {

	private long UTC;
    private Point3D orientation;
	
	public metaData(long UTC, Point3D orientation) {
		this.UTC= UTC;
        this.orientation = orientation;
	}
	
	@Override
	public long getUTC() {
		return UTC;
	}

	@Override
	public String toString() {
		return "UTC"+getUTC()+ "Orientation"+ get_Orientation().toString();
	}
	
	@Override
	public Point3D get_Orientation() {
		MyCoords coords = new MyCoords();
        double[] orientations = coords.azimuth_elevation_dist(orientation, new Point3D(0,0,0));
        return new Point3D(orientations[0], orientations[1], orientations[2]);
        }

}

package GIS;

import Geom.Point3D;

public class metaData implements Meta_data  {

	private long UTC;
	
	public metaData(long UTC) {
		this.UTC= UTC;
	}
	
	@Override
	public long getUTC() {
		return this.UTC;
	}

	@Override
	public String toString() {
		return "UTC:"+getUTC();
	}
	
	@Override
	public Point3D get_Orientation() {
		return null;
        }

}

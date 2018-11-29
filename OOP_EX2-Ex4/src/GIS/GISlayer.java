package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import Geom.Point3D;

public class GISlayer extends HashSet<GIS_element> implements GIS_layer {

	HashSet<GIS_element> set = new HashSet<>();
	private Meta_data data;
	private long creationTime;
	
	public GISlayer() {
        creationTime = System.currentTimeMillis();
    }

	@Override
	public Meta_data get_Meta_data() { 
//		
//		return new Meta_data() {
//            @Override
//            public long getUTC() {
//                return creationTime;
//            }
//
//            @Override
//            public Point3D get_Orientation() {
//                return null;
//            }
//        };
		return null;

	}

}

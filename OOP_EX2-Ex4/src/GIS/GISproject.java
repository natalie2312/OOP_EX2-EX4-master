package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class GISproject extends HashSet<GIS_layer> implements GIS_project {
	
	private HashSet<GIS_layer> set = new HashSet<>();
    private Meta_data data;
    private long creatTime;

    public GISproject() {
    	creatTime = System.currentTimeMillis();
    }
    
	@Override
	public Meta_data get_Meta_data() {
		return null;
	}

}

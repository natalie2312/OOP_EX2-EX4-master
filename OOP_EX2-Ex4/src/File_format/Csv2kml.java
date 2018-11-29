package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GISelement;
import GIS.GISlayer;
import Geom.Point3D;

public class Csv2kml {
	
	public static void csv2kml(String file)
	{
		GIS_layer layer= cvs2layer.csv2layer(file);
		
    	String name= file.substring(0, file.length()-4);
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(name+".kml"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}

		kml.startWrite(pw);		
		
		kml.addLayer(pw, layer);
		kml.closeKml(pw);
		
	}
}

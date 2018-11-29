package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import GIS.GIS_element;
import GIS.GIS_layer;
import Geom.Point3D;
import java.util.Iterator;



public class kml {
	
	public static void startWrite(PrintWriter pw) {

		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");	

		pw.print("<Document><Style id=");
		pw.print("\"red\"");
		pw.print("><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=" );
		pw.print("\"yello\"");		
		pw.print("><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=" );
		pw.print("\"green\"");		
		pw.print("><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>" );
		pw.print("<Folder>");
		pw.print("<name>blabla</name>");

	}
	
	public static void addLayer(PrintWriter pw, GIS_layer layer) {
		
		Iterator<GIS_element> it= layer.iterator();		
		while(it.hasNext()) 
		{
			GIS_element element= it.next();
			addElement(pw, element);
		}
		
	}
	public static void addElement(PrintWriter pw, GIS_element element) {
		
		//בתוך לולאה שקוראת קבצים

		if(!(element.getGeom() instanceof Point3D)) return;
		
		Point3D point = (Point3D)element.getGeom();

		pw.println("<Placemark>");
		pw.println("<name>"+ element.getName()+ "</name>");
		pw.println("<description> Date: <b>"+ element+ "</b></description>");
		pw.println("<styleUrl>#red</styleUrl>");
		pw.println("<Point>");
		pw.println("<coordinates>"+ point.y() +","+ point.x()+ ","+ point.z()+ "</coordinates>");
		pw.println("</Point>");
		pw.println("</Placemark>");
		
	}
	
	public static void closeKml(PrintWriter pw) {
		
		pw.println("</Folder></Document></kml>");
		pw.close();
	}
	
}

package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import GIS.GIS_element;
import GIS.GISelement;
import GIS.GISlayer;
import Geom.Point3D;

public class Csv2kml {
	
	

	public void layer2kml(GISlayer layer, String file)
	{
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

		StringBuilder sb = new StringBuilder();
		startWrite(sb);

// לשלוח לפונקציה את PW SB ואובייקט
		
		while(layer.iterator().hasNext()) 
		{
			GIS_element element= layer.iterator().next();
			addElementToKML(pw, sb, element);
		}
		

		pw.close();
		System.out.println("done!"); // רק בשביל לראות שהקובץ נוצר


	}

	private static StringBuilder startWrite(StringBuilder sb) {

		sb.append("<?xml version=" );
		sb.append("\"1.0\"");
		sb.append(" encoding=");
		sb.append("\"UTF-8\"");
		sb.append("?>" );
		sb.append('\n');

		sb.append("<kml xmlns=");
		sb.append("\"http://www.opengis.net/kml/2.2\">" );
		sb.append('\n');		

		sb.append("<Document><Style id=");
		sb.append("\"red\"");
		sb.append("><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=" );
		sb.append("\"yello\"");		
		sb.append("><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=" );
		sb.append("\"green\"");		
		sb.append("><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>" );


		return sb;

	}
	
	public void addElementToKML(PrintWriter pw, StringBuilder sb, GIS_element element) {
		
		//בתוך לולאה שקוראת קבצים

		if(!(element.getGeom() instanceof Point3D)) return;
		
		Point3D point = (Point3D)element.getGeom();

		sb.append("<Placemark>");
		sb.append('\n');
		sb.append("<name>"+ element.getName()+ "</name>");
		sb.append('\n');
		sb.append("<description> Date: <b>"+ element.getData().getUTC()+ "</b></description>");
		sb.append('\n');
		sb.append("<styleUrl>#red</styleUrl>");
		sb.append('\n');
		sb.append("<Point>");
		sb.append('\n');
		sb.append("<coordinates>"+ point.y() +","+ point.x()+ ","+ point.z()+ "</coordinates>");
		sb.append('\n');
		sb.append("</Point>");
		sb.append('\n');
		sb.append("</Placemark>");
		sb.append('\n');
		sb.append("</Document>" );
		sb.append('\n');
		sb.append("</kml>");

		pw.write(sb.toString());
		
	}

}

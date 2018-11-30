package File_format;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GISelement;
import GIS.GISlayer;
import GIS.Meta_data;
import GIS.metaData;
import Geom.Point3D;

public class cvs2layer {

    public static GIS_layer csv2layer(String file) {
    	
        String[] lines = CSVReader.Reader(file);
        GIS_layer Layer = new GISlayer();
        String cvsSplitBy = ",";

        for (int i = 2; i < lines.length; i++) {
        	
        	String[] Info = lines[i].split(cvsSplitBy);
        	
        	String name= Info[1];
            String time = Info[3];

            double lat = Double.parseDouble(Info[6]);
            double lon = Double.parseDouble(Info[7]);
            double alt = Double.parseDouble(Info[8]);
            Point3D geom = new Point3D(lat, lon, alt);
            
            DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(time, parseFormatter);
            long UTC = dateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
            
            Meta_data data = new metaData(UTC);
            
            GIS_element element = new GISelement(geom, data, name);
            Layer.add(element);
        }
        
        return Layer;
    }
	
}

package File_format;

import java.util.ArrayList;

import Algorithm.ShortestPathAlgo;
import GIS.GIS_project;
import GIS.GISproject;
import GIS.Game;
import GIS.Path;

public class Path2KML {
	
	public Path2KML(ShortestPathAlgo a, ArrayList<Path> arr, Game g) {
        GIS_project Game_Porject = a.GetPathProject(arr, g);
		MultiCSV.project2kml(Game_Porject);
	}

}

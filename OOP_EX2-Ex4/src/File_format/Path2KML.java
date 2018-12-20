package File_format;

import Algorithm.ShortestPathAlgo;

public class Path2KML {
	
	public Path2KML() {
		
		MultiCSV.project2kml(ShortestPathAlgo.getProject());
	}

}

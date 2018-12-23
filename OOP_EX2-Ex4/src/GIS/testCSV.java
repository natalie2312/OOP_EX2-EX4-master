package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Algorithm.ShortestPathAlgo;
import File_format.MultiCSV;
import File_format.Path2Kml;

public class testCSV {

	public static void main(String[] args) {
		
		Game game= new Game("game_1543685769754.csv");
		//game.toCSV("jj");
		ArrayList<Path> a= new ShortestPathAlgo(game).getSolution();

		
		
		Path2Kml p= new Path2Kml("kmlFile", a);

		Iterator<Path> it= a.iterator();
		while(it.hasNext()) {
			Path path = it.next();
			for(int i=1; i<path.size();i++) {
				System.out.println(path.get(i));
			}
			System.out.println();
//			System.out.println(it.next().toString());
		}
//		
//		System.out.println();
//		
//		Iterator<Packman> it2= game.getPackmans().iterator();
//		while(it2.hasNext()) {
//			System.out.println(it2.next().getScore());
//		}
//		
//		
	}

}

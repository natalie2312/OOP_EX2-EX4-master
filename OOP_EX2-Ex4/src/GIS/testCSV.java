package GIS;

import File_format.MultiCSV;

public class testCSV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Game game= new Game("game_1543684662657.csv");
		System.out.println("\ndone");
		
		game.toCSV();
		System.out.println("done");
		
	}

}

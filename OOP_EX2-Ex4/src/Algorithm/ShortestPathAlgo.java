package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.Game;
import GIS.Packman;
import GIS.Path;
import GIS.fruit;
import Geom.Point3D;

public class ShortestPathAlgo extends ArrayList<Path> {
	
	ArrayList<Path> Paths = new ArrayList<Path>();
	
	public ShortestPathAlgo(Game game) {
		
		ArrayList<fruit> tempFruits = game.getFruits();
		ArrayList<Packman> tempPackmans = game.getPackmans();
		
		while(!tempFruits.isEmpty()) {
			
			Iterator<Packman> itP= game.getPackmans().iterator();
			Iterator<Packman> temp_itP= tempPackmans.iterator();

			double min= 100000000;
			Packman bestPac= itP.next();
			
			while(temp_itP.hasNext())
			{
				Iterator<fruit> itF= game.getFruits().iterator();	
				
				Packman p= temp_itP.next();
				double minTimeToFruit= 1000000000;
				
				while(itF.hasNext()) {
					fruit runner= itF.next();
					double time= p.getGps().distance2D(runner.getGps())/p.getSpeed();
					if(time<= minTimeToFruit) {
						minTimeToFruit= time;
						p.setTimeToFruit(time);
						p.setCloseFruit(runner);
					}
				}	
				
				if(p.getTimeToFruit()<= min) {
					min= p.getTimeToFruit();
					bestPac= p;
				}
			}
			
			double time= bestPac.getTimeToFruit();
			bestPac.setGps(bestPac.getCloseFruit().getGps());
			bestPac.setTime(time);
			bestPac.getPath().add(bestPac.getCloseFruit().getGps());
			tempFruits.remove(bestPac.getCloseFruit());
		}
		
		Iterator<Packman> temp= tempPackmans.iterator();
		while(temp.hasNext()) {
			Packman p= temp.next();
			Paths.add(p.getPath());
		}
		
	}
	
	public ArrayList<Path> solution(){
		return Paths;
	}
}

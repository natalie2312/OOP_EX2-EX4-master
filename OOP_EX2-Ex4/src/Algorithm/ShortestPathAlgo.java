package Algorithm;

import java.util.HashSet;
import java.util.Iterator;

import GIS.Game;
import GIS.Packman;
import GIS.Path;
import GIS.fruit;

public class ShortestPathAlgo extends HashSet<Path> {
	
	HashSet<Path> Paths = new HashSet<>();
	
	public ShortestPathAlgo(Game game) {
		
		HashSet<fruit> tempFruits = game.getFruits();
		
		while(!tempFruits.isEmpty()) {
			
			Iterator<Packman> itP= game.getPackmans().iterator();		
			double min=100;
			Packman bestPac= itP.next();
			
			while(itP.hasNext())
			{
				Iterator<fruit> itF= game.getFruits().iterator();	
				
				Packman p= bestPac;
				double minTimeToFruit= 100;
				
				while(itF.hasNext()) {
					fruit runner= itF.next();
					double time= p.getGps().distance2D(runner.getGps())/p.getSpeed();
					if(time< minTimeToFruit) {
						minTimeToFruit= time;
						p.setTimeToFruit(time);
						p.setCloseFruit(runner);
					}
				}	
				
				if(p.getTimeToFruit()< min) {
					min= p.getTimeToFruit();
					bestPac= p;
				}
			}
			
			fruit f= bestPac.getCloseFruit();
			double time= bestPac.getTimeToFruit();
			bestPac.setGps(f.getGps());
			bestPac.setTime(time);
			bestPac.getPath().add(f.getGps());
			tempFruits.remove(bestPac.getCloseFruit());
			
		}
		
		
	}

}

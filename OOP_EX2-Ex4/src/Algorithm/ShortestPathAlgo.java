package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.GISlayer;
import GIS.GISproject;
import GIS.Game;
import GIS.Packman;
import GIS.Path;
import GIS.fruit;
import Geom.Point3D;

public class ShortestPathAlgo extends ArrayList<Path> {
	
	ArrayList<Path> Paths = new ArrayList<Path>();
	static GIS_project project= new GISproject();
	
	public ShortestPathAlgo(Game game) {
		
		ArrayList<fruit> tempFruits = new ArrayList<fruit>(game.getFruits());
		ArrayList<Packman> tempPackmans = new ArrayList<Packman>(game.getPackmans());
		
		while(!tempFruits.isEmpty()) {
			
			Iterator<Packman> itP= game.getPackmans().iterator();
			Iterator<Packman> temp_itP= tempPackmans.iterator();

			double min= 100000000;
			Packman bestPac= itP.next();
			
			while(temp_itP.hasNext())
			{
				Iterator<fruit> itF= tempFruits.iterator();	
				
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
			int i= tempFruits.indexOf(bestPac.getCloseFruit());
			tempFruits.remove(i);

			bestPac.addScore(bestPac.getCloseFruit().getWeigth());
		}
		
		Iterator<Packman> temp= tempPackmans.iterator();
		while(temp.hasNext()) {
			Packman p= temp.next();
			Paths.add(p.getPath());
		}
		addScores(game, tempPackmans);
	}
	
	public ArrayList<Path> solution(){
		return Paths;
	}
	
	public void addScores(Game game, ArrayList<Packman> tempPackmans) {
		
		for(int i=0; i<tempPackmans.size(); i++) {
			game.getPackmans().get(i).setScore(tempPackmans.get(i).getScore());
			
		}
	}
	
//	public void createLayer() {
//		GIS_layer layer= new GISlayer();
//		for(int i=0; i<max(); i++) {
//			
//		}
//		
//	}
	
	public int max() {
		int max= Paths.get(0).size();
		for(int i=1; i<Paths.size(); i++) {
			int num= Paths.get(i).size();
			if(num> max)
				max= num;
		}
		return max;
	}
	
	public static GIS_project getProject(){
		return project;
	}
}

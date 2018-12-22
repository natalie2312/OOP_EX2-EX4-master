package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.GISelement;
import GIS.GISlayer;
import GIS.GISproject;
import GIS.Game;
import GIS.Meta_data;
import GIS.Packman;
import GIS.Path;
import GIS.fruit;
import GIS.metaData;
import Geom.Geom_element;
import Geom.Point3D;

public class ShortestPathAlgo extends ArrayList<Path> {
	
	static ArrayList<Path> Paths = new ArrayList<Path>();
	ArrayList<fruit> tempFruits;
	ArrayList<Packman> tempPackmans;
	
	public ShortestPathAlgo(Game game) {
		
		tempFruits = new ArrayList<fruit>(game.getFruits());
		tempPackmans = new ArrayList<Packman>(game.getPackmans());
		
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
			bestPac.getPath().addPointTime(time);
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
	
	public static GIS_project GetPathProject(ArrayList<Path> arr, Game g) {
		
        GIS_project Game_Porject = new GISproject();

        for (int i = 0; i < max(arr); i++) {

            GIS_layer layer = new GISlayer();

            for (int j = 0; j < g.getFruits().size(); j++) {
                fruit f = g.getFruits().get(j);
                Geom_element Geom = f.getGps();
                Meta_data GisData = new metaData();
                GIS_element FruitEl = new GISelement(Geom, GisData);
                layer.add(FruitEl);
            }
            if (i == 0) {
                for (int j = 0; j < (g.getPackmans().size()); j++) {
                    Packman p = g.getPackmans().get(j);
                    Geom_element Geom = p.getGps();
                    Meta_data GisData = new metaData(arr.get(i).get(0));
                    GIS_element PacEl = new GISelement(Geom, GisData);
                    layer.add(PacEl);
                }
            }
            if (i != 0) {
                for (Path currnetPath : arr) {
                    Point3D f = currnetPath.get(i-1);
                    Geom_element geom_element = f;
                    Point3D Ori;
                    Meta_data data;
                    if(currnetPath.size()<i) {
                    	Ori = currnetPath.get(i);
                        data = new metaData(Ori);
                    }
                    else {
                    	Ori = null;
                        data = new metaData();
                    }
                    GIS_element gis_element = new GISelement(geom_element, data);
                    layer.add(gis_element);
                }
            }
            Game_Porject.add(layer);
        }
        return Game_Porject;
    }
	
	
	public static int max(ArrayList<Path> arr) {
		int max= arr.get(0).size();
		for(int i=1; i<arr.size(); i++) {
			int num= arr.get(i).size();
			if(num> max)
				max= num;
		}
		return max;
	}

}

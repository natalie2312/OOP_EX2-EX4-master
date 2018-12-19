package GIS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import File_format.CSVReader;
import Geom.Point3D;

public class Game extends ArrayList<Object> {

	ArrayList<fruit> fruits = new ArrayList<fruit>();
	ArrayList<Packman> Packmans = new ArrayList<Packman>();

	public Game(){
	}

	public Game(String csvFile) {

		File file = new File(csvFile);

		if (isCsv(file)) {

			String[] lines= CSVReader.Reader(csvFile);

			for (int i=1; i<lines.length; i++) {

				String[] Info = lines[i].split(",");

				String type= Info[0];
				int id= Integer.parseInt(Info[1]);
				double lat = Double.parseDouble(Info[2]);
				double lon = Double.parseDouble(Info[3]);
				double alt = Double.parseDouble(Info[4]);

				Point3D gps = new Point3D(lat, lon, alt);  // create the point

				int Speed_Weight= Integer.parseInt(Info[5]);

				if(type.equals("P")) {
					int raduis= Integer.parseInt(Info[6]);
					Packman p= new Packman(id, gps, Speed_Weight, raduis);
					Packmans.add(p);
				}

				else {
					fruit f= new fruit(id, gps, Speed_Weight);
					fruits.add(f);
				}
			}
		}

		else
			System.err.print("Wrong Input!");
	}

	private boolean isCsv(File fileType){
		return fileType.getName().endsWith(".csv");
	}
	
	public void toCSV(String name) {
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(name+".csv"));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        StringBuilder sb = new StringBuilder();
        start(sb);
        addPackmanSet(sb);
        addFruitSet(sb);

        pw.write(sb.toString());
        pw.close();
		
	}
	
	public void start(StringBuilder sb) {
		sb.append("Type");
        sb.append(",");
		sb.append("id");
        sb.append(",");
        sb.append("Lat");
        sb.append(',');
        sb.append("Lon");
        sb.append(',');
        sb.append("Alt");
        sb.append(',');
        sb.append("Speed/Weight");
        sb.append(',');
        sb.append("Radius");
        sb.append(',');
        sb.append(Packmans.size());
        sb.append(',');
        sb.append(fruits.size());
        sb.append('\n');
	}

	public void addPackmanSet(StringBuilder sb) {
		
		Iterator<Packman> it= Packmans.iterator();		
		while(it.hasNext())   // while the layer has more elements
		{
			Packman p= it.next();  
			addPackman(sb, p); 
		}
	}
	
	public void addFruitSet(StringBuilder sb) {
		
		Iterator<fruit> it= fruits.iterator();		
		while(it.hasNext())   // while the layer has more elements
		{
			fruit f= it.next();  
			addFruit(sb, f); 
		}
	}
	
	public void addPackman(StringBuilder sb, Packman p) {
        sb.append("P");
        sb.append(',');
        sb.append(p.getId());
        sb.append(',');
        sb.append(p.getGps().x());
        sb.append(',');
        sb.append(p.getGps().y());
        sb.append(',');
        sb.append(p.getGps().z());
        sb.append(',');
        sb.append(p.getSpeed());
        sb.append(',');
        sb.append(p.getRadius());

        sb.append('\n');
	}
	
	public void addFruit(StringBuilder sb, fruit f) {
        sb.append("F");
        sb.append(',');
        sb.append(f.getId());
        sb.append(',');
        sb.append(f.getGps().x());
        sb.append(',');
        sb.append(f.getGps().y());
        sb.append(',');
        sb.append(f.getGps().z());
        sb.append(',');
        sb.append(f.getWeigth());
        sb.append(',');

        sb.append('\n');
	}

	public ArrayList<fruit> getFruits() {
		return fruits;
	}

	public ArrayList<Packman> getPackmans() {
		return Packmans;
	}
	
	public boolean isEmpty() {
		return fruits.isEmpty()&Packmans.isEmpty();
	}
	
}

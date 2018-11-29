package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.GISproject;


public class MultiCSV {
	// File object 

	public static void dir2kml(String name) 
	{ 
		File maindir = new File(name); 
		ArrayList <File> csv_files= new ArrayList<File>();	
		ArrayList<File> result = findCSVs(maindir, csv_files);
		
		GIS_project project= new GISproject();
		
		Iterator<File> it= result.iterator();	
		
		while(it.hasNext()) {
			File file= it.next();
			GIS_layer layer= cvs2layer.csv2layer(file.getPath());
			project.add(layer);
		}
		
		project2kml(project);
		
	}


	public static ArrayList findCSVs(File maindir, ArrayList csv_files)
	{
		File[] filesInDir = maindir.listFiles();
		for(int i=0; i<filesInDir.length ; i++)
		{
			if(filesInDir[i].isFile() && filesInDir[i].getName().endsWith(".csv"))
			{
				if(isRightFormat(filesInDir[i])) {
					csv_files.add(filesInDir[i]);
				}
				if(filesInDir[i].isDirectory())
				{
					findCSVs(filesInDir[i], csv_files);
				}

			}
		}
		return csv_files;
	}
	

	public static boolean isRightFormat(File file) {
		String line = "";
		String[] csvFormat = {"MAC","SSID","AuthMode","FirstSeen","Channel","RSSI","CurrentLatitude","CurrentLongitude","AltitudeMeters","AccuracyMeters","Type"};

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			line = br.readLine();
			line = br.readLine();
			String [] format = line.split(",");

			return Arrays.equals(format, csvFormat);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public static void project2kml(GIS_project project) {
		
		PrintWriter pw = null;
		try 
		{
			pw = new PrintWriter(new File("dir.kml"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		
		kml.startWrite(pw);
		
		Iterator<GIS_layer> it= project.iterator();	
		while(it.hasNext()) {
			GIS_layer layer= it.next();
			kml.addLayer(pw, layer);
		}
		
		kml.closeKml(pw);
	}
	
}

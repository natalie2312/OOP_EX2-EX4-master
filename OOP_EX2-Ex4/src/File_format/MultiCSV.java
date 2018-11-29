package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class MultiCSV {
	// File object 

		public static ArrayList findCSVs(File maindir, ArrayList csv_files)
		{
			File[] filesInDir = maindir.listFiles();
			for(int i=0;i < filesInDir.length ; i++)
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

			public static void main(String[] args) 
			{ 
				String maindirpath = "D:\\qq"; 
				File maindir = new File(maindirpath); 
				ArrayList <File> csv_files= new ArrayList<File>();	
				ArrayList<File> result = findCSVs(maindir, csv_files);
				for(int i=0;i < result.size() ; i++)
				{
					System.out.print(result.get(i) + "\n");
				}
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

}

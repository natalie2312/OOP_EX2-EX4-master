package GIS;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class text{
	static BufferedImage myImage;
	public static void main(String[] args){
		Point3D gps1= new Point3D(32.106046,35.212405);
		Point3D gps2= new Point3D(32.101650,35.202574);
		Point3D inPixel = new Point3D(645,478);
		Point3D inPixel1 = new Point3D(353,432);
		
		{
		        try {
		            String mapPath = "Ariel1.png";
		            myImage = ImageIO.read(new File(mapPath));
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }	
	}
		Map map= new Map(myImage, gps1, gps2);
		Point3D expected = new Point3D(708,432);
        Point3D actual = map.coords2pics(new Point3D(32.104 , 35.210));   
        System.out.println(expected+" yit"+ actual );
        
	}
}

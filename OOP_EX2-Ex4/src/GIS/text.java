//package GIS;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import Geom.Point3D;
//
//public class text{
//	static BufferedImage myImage;
//	public static void main(String[] args){
//		Point3D gps1= new Point3D(32.106046,35.212405);
//		Point3D gps2= new Point3D(32.101650,35.202574);
//		Point3D inPixel = new Point3D(645,478);
//		Point3D inPixel1 = new Point3D(353,432);
//		
//		{
//		        try {
//		            String mapPath = "Ariel1.jpg";
//		            myImage = ImageIO.read(new File(mapPath));
//		            
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }	
//	}
//		Map map= new Map(myImage);
//		Point3D expected = new Point3D(0,0);
//        Point3D actual = map.coords2pics(new Point3D(32.106046, 35.202574));   
//        System.out.println(expected+" yit "+ actual );
//        
//        Point3D expected2 = new Point3D(32.106046, 35.202574);
//        Point3D actual2 = map.pics2coords(new Point3D(710,300));   
//        System.out.println(expected2+" yit "+ actual2 );
//        Point3D oo= new Point3D(850,130);
//        System.out.println(map.pics2coords(oo)+ "nae point");
//        Point3D p= map.pics2coords(actual2);
//        Point3D p2= map.coords2pics(p);
//        System.out.println(p2);
//        
//        
//	}
//}

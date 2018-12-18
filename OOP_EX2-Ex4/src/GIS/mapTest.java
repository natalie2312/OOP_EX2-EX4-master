package GIS;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;
import javafx.scene.image.Image;

class mapTest {

	private Map map;
    private BufferedImage myImage;
    private Point3D inPixel;
    private Point3D inPixel1;

	{
		try {
			String mapPath = "Ariel1.png";
			myImage = ImageIO.read(new File(mapPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		
		Point3D gps1= new Point3D(35.212405, 32.106046);
		Point3D gps2= new Point3D(35.202574, 32.101650);
		inPixel = new Point3D(645,478);
        inPixel1 = new Point3D(353,432);

		this.map= new Map(this.myImage, gps1, gps2);
		
	}

//	@Test
//	void testMap() {
////		fail("Not yet implemented");
//	}

	@Test
	void testCoords2pics() {

		Point3D actualCoordinates = new Point3D(32.10809746113932,35.20590088832077);
        Point3D actualPixel = map.coords2pics(actualCoordinates);
        Assert.assertTrue(inPixel1.close2equals(actualPixel,0.00001));
	}

	@Test
	void testPics2coords() {
		Point3D expected = new Point3D(32.10979442049724,35.206255140318355);
        Point3D actual = map.pics2coords(inPixel);
        Assert.assertTrue(expected.close2equals(actual,0.00001));
	}

	@Test
	void testDistance() {
        double expectedDistance = Math.sqrt(87380);
        double actual = map.distance(inPixel1,inPixel);
        Assert.assertEquals(expectedDistance,actual,0.00001);
	}
	
	@Test
	void testAzimut() {
		double azimuthPix = map.azimut(inPixel1,inPixel);
        MyCoords myCoords = new MyCoords();
        double azimuthGps = myCoords.azimuth_elevation_dist(map.pics2coords(inPixel1),
                map.pics2coords(inPixel))[0];
        Assert.assertEquals(azimuthGps,azimuthPix,0.0001);
	}


}

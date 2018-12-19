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

class MapTest {
	private Map map;
    private BufferedImage myImage;
    private Point3D inPixel;
    private Point3D inPixel1;

	{
		try {
			String mapPath = "Ariel1.jpg";
			myImage = ImageIO.read(new File(mapPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		
		Point3D gps1= new Point3D(32.106046, 35.202574);
		Point3D gps2= new Point3D(32.101858, 35.212405);
		inPixel = new Point3D(0,0);
        inPixel1 = new Point3D(1433,642);

		this.map= new Map(this.myImage);
		
	}

//	@Test
//	void testMap() {
////		fail("Not yet implemented");
//	}

	@Test
	void testCoords2pics() {

		Point3D actualCoordinates = new Point3D(32.106046, 35.202574);
        Point3D actualPixel = map.coords2pics(actualCoordinates);
        Assert.assertTrue(inPixel.close2equals(actualPixel,0.1));
	}

	@Test
	void testPics2coords() {
		Point3D expected = new Point3D(32.101858, 35.212405);
        Point3D actual = map.pics2coords(inPixel1);
        Assert.assertTrue(expected.close2equals(actual,0.001));
	}

	@Test
	void testDistance() {
        double expectedDistance = Math.sqrt(1433*1433+642*642);
        double actual = map.distance(inPixel1,inPixel);
        Assert.assertEquals(expectedDistance,actual,0.001);
	}
	
	@Test
	void testAzimut() {
		double azimuthPix = map.azimut(inPixel1,inPixel);
        MyCoords myCoords = new MyCoords();
        double azimuthGps = myCoords.azimuth_elevation_dist(map.pics2coords(inPixel1),map.pics2coords(inPixel))[0];
        Assert.assertEquals(azimuthGps,azimuthPix,0.001);
	}
}

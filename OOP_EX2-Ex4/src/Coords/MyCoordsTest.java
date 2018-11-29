package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;

class MyCoordsTest {
	
	Point3D a;
	Point3D b;
	Point3D c;
	MyCoords l;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		a= new Point3D(32.103315, 35.209039, 670);
		b= new Point3D(32.106352, 35.205225, 650);
		c= new Point3D(337.699, -359.249, -20);
		l= new MyCoords();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAdd() {
		assertEquals(true, l.add(a, c).equals(new Point3D(32.106352000071396,35.20522500219698,650.0)));
	}

	@Test
	void testDistance3d() {
		assertEquals(493.05233183241336, l.distance3d(a, b));
	}

	@Test
	void testVector3D() {
		assertEquals(true, l.vector3D(a, b).equals(new Point3D(337.69899206128815,-359.2492069388189,-20.0)));
	}

	@Test
	void testAzimuth_elevation_dist() {
		double[] ar= l.azimuth_elevation_dist(a, b);
		double[] ar2= {313.2585, -2.3253, 493.0523};
		assertArrayEquals(ar, ar2, 0.05);
	}

	@Test
	void testIsValid_GPS_Point() {
		assertEquals(true, l.isValid_GPS_Point(a));
		assertEquals(false, l.isValid_GPS_Point(c));

	}

}

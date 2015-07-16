import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Author: Mukund Bhudia
 */
public class DijkstraTest {

    public List<Road> listOfRoads = new ArrayList<Road>();

    @Before
    public void setUp() throws Exception {
        //Populate roads. Could be read from file.
        listOfRoads.add(new Road("AB5"));
        listOfRoads.add(new Road("BC4"));
        listOfRoads.add(new Road("CD7"));
        listOfRoads.add(new Road("DC8"));
        listOfRoads.add(new Road("DE6"));
        listOfRoads.add(new Road("AD5"));
        listOfRoads.add(new Road("CE2"));
        listOfRoads.add(new Road("EB3"));
        listOfRoads.add(new Road("AE7"));
    }

    @Test
    public void TestExplicitRoute_ABC_Expect9() throws SatNav.NoSuchRouteException {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(9, satNav.getDistanceOfExplicitRoute("A-B-C"));
    }

    @Test
    public void TestExplicitRoute_AD_Expect5() throws SatNav.NoSuchRouteException {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(5, satNav.getDistanceOfExplicitRoute("A-D"));
    }

    @Test
    public void TestExplicitRoute_ADC_Expect13() throws SatNav.NoSuchRouteException {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(13, satNav.getDistanceOfExplicitRoute("A-D-C"));
    }

    @Test
    public void TestExplicitRoute_AEBCD_Expect21() throws SatNav.NoSuchRouteException {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(21, satNav.getDistanceOfExplicitRoute("A-E-B-C-D"));
    }

    @Test(expected = SatNav.NoSuchRouteException.class)
    public void TestExplicitRoute_AED_ExpectNoSuchRouteException() throws SatNav.NoSuchRouteException {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(0, satNav.getDistanceOfExplicitRoute("A-E-D"));
    }

    @Test
    public void TestRoutesWithMax3Junctions_CC_Expect2() {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(2, satNav.routesWithMaxJunctions("C", "C", 3));
        //Check the routes found have all the expected routes
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-D-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-E-B-C"));
    }

    @Test
    public void TestRoutesWith4Junctions_AC_Expect3() {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(3, satNav.routesWithJunctions("A", "C", 4));
        //Check the routes found have all the expected routes
        assertTrue(satNav.getPrintedFoundRoutes().contains("A-B-C-D-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("A-D-C-D-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("A-D-E-B-C"));
    }

    @Test
    public void TestShortestRoute_AC_Expect9() {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(9, satNav.shortestRoute("A", "C"));
    }

    @Test
    public void TestShortestRoute_BB_Expect9() {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(9, satNav.shortestRoute("B", "B"));
    }

    @Test
    public void TestRoutesDistanceLessThan30_CC_Expect9() {
        SatNav satNav = new SatNav(listOfRoads);
        assertEquals(9, satNav.routesWithDistanceLessThan("C", "C", 30));
        //Check the routes found have all the expected routes
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-D-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-E-B-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-E-B-C-D-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-D-C-E-B-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-D-E-B-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-E-B-C-E-B-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-E-B-C-E-B-C-E-B-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-D-E-B-C-E-B-C"));
        assertTrue(satNav.getPrintedFoundRoutes().contains("C-E-B-C-D-E-B-C"));
    }
}

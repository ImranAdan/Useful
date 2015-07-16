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
    public void TestExplicitRoute_ABC_Expect9() throws Dijkstra.NoSuchRouteException {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(9, dijkstra.getDistanceOfExplicitRoute("A-B-C"));
    }

    @Test
    public void TestExplicitRoute_AD_Expect5() throws Dijkstra.NoSuchRouteException {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(5, dijkstra.getDistanceOfExplicitRoute("A-D"));
    }

    @Test
    public void TestExplicitRoute_ADC_Expect13() throws Dijkstra.NoSuchRouteException {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(13, dijkstra.getDistanceOfExplicitRoute("A-D-C"));
    }

    @Test
    public void TestExplicitRoute_AEBCD_Expect21() throws Dijkstra.NoSuchRouteException {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(21, dijkstra.getDistanceOfExplicitRoute("A-E-B-C-D"));
    }

    @Test(expected = Dijkstra.NoSuchRouteException.class)
    public void TestExplicitRoute_AED_ExpectNoSuchRouteException() throws Dijkstra.NoSuchRouteException {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(0, dijkstra.getDistanceOfExplicitRoute("A-E-D"));
    }

    @Test
    public void TestRoutesWithMax3Junctions_CC_Expect2() {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(2, dijkstra.routesWithMaxJunctions("C", "C", 3));
        //Check the routes found have all the expected routes
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-D-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-E-B-C"));
    }

    @Test
    public void TestRoutesWith4Junctions_AC_Expect3() {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(3, dijkstra.routesWithJunctions("A", "C", 4));
        //Check the routes found have all the expected routes
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("A-B-C-D-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("A-D-C-D-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("A-D-E-B-C"));
    }

    @Test
    public void TestShortestRoute_AC_Expect9() {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(9, dijkstra.shortestRoute("A", "C"));
    }

    @Test
    public void TestShortestRoute_BB_Expect9() {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(9, dijkstra.shortestRoute("B", "B"));
    }

    @Test
    public void TestRoutesDistanceLessThan30_CC_Expect9() {
        Dijkstra dijkstra = new Dijkstra(listOfRoads);
        assertEquals(9, dijkstra.routesWithDistanceLessThan("C", "C", 30));
        //Check the routes found have all the expected routes
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-D-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-E-B-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-E-B-C-D-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-D-C-E-B-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-D-E-B-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-E-B-C-E-B-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-E-B-C-E-B-C-E-B-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-D-E-B-C-E-B-C"));
        assertTrue(dijkstra.getPrintedFoundRoutes().contains("C-E-B-C-D-E-B-C"));
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mukund Bhudia
 */
public class Route {

    private String startCity;
    private String endCity;
    private int routeLength;
    private int numberOfJunctions;
    private List<Road> roads;

    Route() {
        roads = new ArrayList<Road>();
        routeLength = 0;
        numberOfJunctions = 0;
    }

    /**
     * Allows a route to be populated by taking in a Road object.
     * @param givenRoad the road to be added to the route
     */
    public void addRoadToRoute(Road givenRoad){
        roads.add(givenRoad);
        startCity = roads.get(0).getStartCity();
        numberOfJunctions = roads.size();   //Update junctions
        for (int i = 0; i < roads.size(); i++) {
            if (i == roads.size() - 1) {    //Update the end city of the route
                endCity = roads.get(i).getEndCity();
            }
        }
    }

    /**
     * Calculates the total length of the route by adding up lengths of the roads.
     * @return the length of the route
     */
    public int getRouteLength(){
        int routeLength = 0;
        for (Road road : roads) {
            routeLength += road.getRoadLength();
        }
        return routeLength;
    }

    /**
     * Returns the destination city of the route.
     * @return destination city as a string
     */
    public String getEndCity() {
        return endCity;
    }

    /**
     * Returns the city that the route begins at.
     * @return the start city of the route as a string
     */
    public String getStartCity() {
        return startCity;
    }

    /**
     * Returns a list of roads that constitutes the route.
     * @return a list of Road objects
     */
    public List<Road> getRoads() {
        return roads;
    }

    /**
     * Returns the number of junctions of a route.
     * @return integer number of junctions
     */
    public int getNumberOfJunctions(){
        return roads.size();
    }

    /**
     * Prints out the total route by going through a list of the routes roads.
     * @return a string of the start city and destination city of every road in the route in the form A-B-C
     */
    public String printRoute(){
        StringBuilder printedRoute = new StringBuilder();
        if ( roads.size() == 0 ) {
            return "";
        } else {
            printedRoute.append(roads.get(0).getStartCity());
            for (int i = 0; i < roads.size() - 1; i++) {
                Road road = roads.get(i);
                printedRoute.append("-");
                printedRoute.append(road.getEndCity());
            }
            printedRoute.append("-");
            printedRoute.append(roads.get(roads.size() - 1).getEndCity());
            return printedRoute.toString();
        }
    }

    /**
     * Erases all roads that are contained inside the route.
     */
    public void removeAllRoads(){
        roads.clear();
        startCity = null;
        endCity = null;
        routeLength = 0;
        numberOfJunctions = 0;
    }

    /**
     * Removes the last road added to the route (if it exists).
     */
    public void removeLastRoadAdded(){
        roads.remove(roads.size() - 1);
        if (roads.size() > 0) {
            endCity = roads.get(roads.size() - 1).getEndCity();
        } else {
            endCity = null;
            startCity = null;
        }
        this.routeLength =  getRouteLength();
        numberOfJunctions = roads.size();
    }
}
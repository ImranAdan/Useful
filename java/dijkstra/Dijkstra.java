import java.util.*;

/**
 * @Author Mukund Bhudia
 */
public class Dijkstra {

    private List<Road> listOfRoads = new ArrayList<Road>();
    private List<Route> listOfFoundRoutes = new ArrayList<Route>();
    private Set<String> cities = new HashSet<String>();

    /**
     * SatNav object
     * @param givenListOfRoads needs a list of roads to be initialised
     */
    public Dijkstra(List<Road> givenListOfRoads) {
        this.listOfRoads = givenListOfRoads;
        for (Road road : listOfRoads) {     //Populate cities set
            cities.add(road.getStartCity());
            cities.add(road.getEndCity());
        }
    }

    /**
     * Get the distance of the route that traverses all cities stated in the route supplied.
     * @param givenRoute the route given as a string
     * @return length of route
     * @throws NoSuchRouteException
     */
    public int getDistanceOfExplicitRoute(String givenRoute) throws NoSuchRouteException{
        int distanceOfRoute = 0;
        //Split the route to check out into an array for processing each char
        String[] testChars = givenRouteToStringArray(givenRoute);

        //Get each city from given route
        String firstCity;
        String secondCity;
        String endCityOfGivenRoute = testChars[testChars.length - 1];
        Route foundRoute = new Route();

        for (int i = 0; i < testChars.length; i++) {       //For every city given...
            //...go through all roads listed
            firstCity = testChars[i];
            if (i != (testChars.length - 1)) {
                secondCity = testChars[i + 1];  //We look for the character and its neighbour in the given string
                for (int j = 0; j < listOfRoads.size(); j++) {  //Then loop through all the roads to find a match
                    if (listOfRoads.get(j).getStartCity().equals(firstCity) &&
                            listOfRoads.get(j).getEndCity().equals(secondCity)) {
                        foundRoute.addRoadToRoute(listOfRoads.get(j));
                    }
                }
            }
        }
        //Check that we have a route
        if (foundRoute.getRouteLength() != 0) {
            if (foundRoute.getEndCity().equals(endCityOfGivenRoute)) {  //And we check if the route ends at the destination
                distanceOfRoute = foundRoute.getRouteLength();
            } else {
                foundRoute.removeAllRoads();
                throw new NoSuchRouteException();
            }
        }
        return distanceOfRoute;
    }

    /**
     * Converts a given route from a string to an array of strings by splitting with a delimiter.
     * @param givenRoute the route given as a string (we assume the route has been entered in the correct format)
     * @return string array of each city
     */
    public String[] givenRouteToStringArray(String givenRoute) {
        String delimiter = "-";
        return givenRoute.split(delimiter);
    }

    /**
     * Obtains the number of routes that have a given maximum number of junctions.
     * @param startCity the beginning of the route
     * @param endCity the end city of the route
     * @param maxNoOfJunctions the maximum number of junctions a route should have
     * @return number of routes found
     */
    public int routesWithMaxJunctions(String startCity, String endCity, int maxNoOfJunctions){
        Route tempRoute = new Route();  //Route object to be populated by the DFS road explorer
        maxJunctionsRoadExplorer(listOfRoads, tempRoute, startCity, startCity,
                endCity, maxNoOfJunctions);
        return listOfFoundRoutes.size(); //returns the list of routes populated by maxJunctionsRoadExplorer
    }

    /**
     * Obtains the number of routes that have a given number of junctions.
     * @param startCity the beginning of the route
     * @param endCity the end city of the route
     * @param numberOfJunctions the number of junctions a route should have
     * @return number of routes found
     */
    public int routesWithJunctions(String startCity, String endCity, int numberOfJunctions){
        int totalNumberOfFoundRoutes = 0;
        routesWithMaxJunctions(startCity, endCity, numberOfJunctions);   //We re-use the routesWithMaxJunctions DFS...
        for (Route foundRoute : listOfFoundRoutes) {             //..and go through the list of results...
            if (foundRoute.getNumberOfJunctions() == numberOfJunctions) {
                totalNumberOfFoundRoutes++;                     //and count the number of routes with the exact number of junctions
            }
        }
        return totalNumberOfFoundRoutes;
    }

    /**
     * Obtains the number of routes that have a given maximum total distance.
     * @param startCity the beginning of the route
     * @param endCity the end city of the route
     * @param maxDistance total distance a route should have
     * @return number of routes found
     */
    public int routesWithDistanceLessThan(String startCity, String endCity, int maxDistance){
        Route testRoute = new Route();
        maxDistanceRoadExplorer(listOfRoads, testRoute, startCity,
                startCity, endCity, maxDistance);
        return listOfFoundRoutes.size();
    }

    /**
     * Gets the shortest path from the start city to the destination city by using Dijkstra's algorithm.
     * @param startCity the city we want to set of from
     * @param endCity the destination city we want to end up at
     * @return the total length of the route from start city to end city
     */
    public int shortestRoute(String startCity, String endCity) {
        List<Vertex> unvisited = new ArrayList<Vertex>();
        List<Vertex> visited = new ArrayList<Vertex>();
        List<Vertex> allVertices = new ArrayList<Vertex>();
        Vertex currentVertex = new Vertex();
        boolean hasReachedEndCity = false;

        for (String city : cities) {
            Vertex newVertex = new Vertex(city);
            if (city.equals(startCity)) {
                currentVertex = newVertex;  //Label the first city vertex as the current
                newVertex.setDistance(0);   //Set the start city vertex as 0 distance otherwise the
                // constructor of vertex sets the distance as -1 by default
                visited.add(newVertex);     //Mark the first vertex as visited
            } else {
                unvisited.add(newVertex);   //All other vertices have yet to be visited
            }
            allVertices.add(newVertex);     //We maintain a list of all vertices
        }

        while (!unvisited.isEmpty() && !hasReachedEndCity) { //Main Dijkstra loop
            //We go through all roads looking for adjacent vertices to the current marked vertex
            for (Road road : listOfRoads) {
                if (currentVertex.getCityName().equals(road.getStartCity())) {
                    int tempDistance = currentVertex.getDistance() + road.getRoadLength();  //calculate distance
                    // between start city and the current labeled vertex
                    for (Vertex vertex : allVertices) {
                        if (vertex.getCityName().equals(road.getEndCity())) {
                            //This is an adjacent vertex
                            if (visited.get(0).getCityName().equals(endCity) && vertex.getCityName().equals(endCity)) {
                                //This is the case that we find the distance from one vertex to the same vertex (e.g. B-B)
                                Vertex newEndVertex = new Vertex(vertex.getCityName());
                                newEndVertex.setDistance(tempDistance);
                                newEndVertex.setPrevious(currentVertex);
                                currentVertex = newEndVertex;
                                unvisited.clear();  //The destination vertex has been reached and there are no more vertices to visit
                            } else {
                                if (vertex.getDistance() == -1 || tempDistance < vertex.getDistance()) {
                                    //If the adjacent vertex hasn't been visited or is closer than the
                                    // distance via the current vertex
                                    vertex.setDistance(tempDistance);   //...then we update the distance of the adjacent vertex
                                    vertex.setPrevious(currentVertex);
                                }
                            }
                        }
                    }
                }
            }

            if (unvisited.size() > 0) { //For any unvisited vertex we look for the smallest unmarked vertex...
                int smallestDistanceSoFar = unvisited.get(0).getDistance();
                currentVertex = unvisited.get(0);
                for (int i = 1; i < unvisited.size(); i++) {
                    if (smallestDistanceSoFar != -1) {
                        if (unvisited.get(i).getDistance() != -1) {
                            if (unvisited.get(i).getDistance() < smallestDistanceSoFar) {
                                smallestDistanceSoFar = unvisited.get(i).getDistance();
                                currentVertex = unvisited.get(i);   //the smallest unmarked vertex is now current
                            }
                        }
                    } else {
                        smallestDistanceSoFar = unvisited.get(i).getDistance();
                        currentVertex = unvisited.get(i);
                    }
                }
                unvisited.remove(currentVertex);
            }
            visited.add(currentVertex); // mark the new smallest vertex as visited
            hasReachedEndCity = currentVertex.getCityName().equals(endCity);
        }

        List<Vertex> totalRoute = new ArrayList<Vertex>(); //list to build up the route of the shortest path in reverse order
        Vertex targetCity = currentVertex;
        totalRoute.add(targetCity);
        while (targetCity.getPrevious() != null) {
            totalRoute.add(targetCity.getPrevious());
            targetCity = targetCity.getPrevious();
        }
        Collections.reverse(totalRoute);    //put the list of routes in the correct order.

        return currentVertex.getDistance();
    }

    /**
     * Recursive DFS style method that goes through all roads and builds up routes until a maximum number junctions
     * per route is found. We assume cycles are allowed in this method.
     * @param allRoads the roads loaded into the SatNav object
     * @param tempRoute the route that is being built up from traversing all roads
     * @param startCity the city where we start from
     * @param startCityToCheck the current start city we are looking for when traversing all roads
     * @param finalEndCity the destination city
     * @param maxJunctions the maximum number of junctions each route found though have
     */
    public void maxJunctionsRoadExplorer(List<Road> allRoads, Route tempRoute, String startCity,
                                         String startCityToCheck, String finalEndCity, int maxJunctions) {

        for (int i = 0; i < allRoads.size(); i++) {
            if (allRoads.get(i).getStartCity().equals(startCityToCheck) &&
                    (tempRoute.getNumberOfJunctions() <= maxJunctions)) {
                tempRoute.addRoadToRoute(allRoads.get(i));  //Add a road to our working route if it's the
                // road we're looking for and the working route is less than the max number of junctions
                if (tempRoute.getStartCity().equals(startCity) && tempRoute.getEndCity().equals(finalEndCity) &&
                        (tempRoute.getNumberOfJunctions() <= maxJunctions)) {
                    //The route has been found so we add it to the main list of routes
                    listOfFoundRoutes.add(copyRoute(tempRoute));
                }
                //We then make a recursive call to the method to now look for roads that begin with the end of the previously accepted road
                maxJunctionsRoadExplorer(allRoads, tempRoute, startCity, allRoads.get(i).getEndCity(), finalEndCity, maxJunctions);
                if (tempRoute.getNumberOfJunctions() > 0) {
                    tempRoute.removeLastRoadAdded();    //Here we remove the last road as we are after the recursive call
                    // which has terminated as the previous road added has exceeded the maximum number of junctions for the route
                }
            } else if (tempRoute.getEndCity() != null) {
                if (tempRoute.getEndCity().equals(finalEndCity) && (tempRoute.getNumberOfJunctions() > maxJunctions)) {
                    return; //We end the recursion if we've reached the destination city and the number of junctions exceeds our max
                }
            }
        }
    }

    /**
     * Recursive DFS style method that goes through all roads and builds up routes until a maximum total distance
     * per route is found. We assume cycles are allowed in this method.
     * @param allRoads the roads loaded into the SatNav object
     * @param tempRoute the route that is being built up from traversing all roads
     * @param startCity the city where we start from
     * @param startCityToCheck the current start city we are looking for when traversing all roads
     * @param finalEndCity the destination city
     * @param maxDistance the maximum distance each route found though have
     */
    public void maxDistanceRoadExplorer(List<Road> allRoads, Route tempRoute,
                                        String startCity, String startCityToCheck, String finalEndCity, int maxDistance) {

        for (int i = 0; i < allRoads.size(); i++) {
            if (allRoads.get(i).getStartCity().equals(startCityToCheck) && (tempRoute.getRouteLength() < maxDistance)) {
                tempRoute.addRoadToRoute(allRoads.get(i));  //Add a road to our working route if its the
                // road we're looking for and the working route is less than the maximum distance
                if (tempRoute.getStartCity().equals(startCity) && tempRoute.getEndCity().equals(finalEndCity) &&
                        (tempRoute.getRouteLength() < maxDistance)) {
                    //The route has been found so we add it to the main list of routes
                    listOfFoundRoutes.add(copyRoute(tempRoute));
                }
                //We then make a recursive call to the method to now look for roads that begin with the end of the previously accepted road
                maxDistanceRoadExplorer(allRoads, tempRoute, startCity, allRoads.get(i).getEndCity(),
                        finalEndCity, maxDistance);
                if (tempRoute.getNumberOfJunctions() > 0) {
                    tempRoute.removeLastRoadAdded();    //Here we remove the last road as we are after the recursive call
                    // which has terminated as the previous road added has exceeded the maximum distance for the route
                }
            } else if (tempRoute.getEndCity() != null) {
                if (tempRoute.getEndCity().equals(finalEndCity) && (tempRoute.getRouteLength() >= maxDistance)) {
                    return; //We end the recursion if we've reached the destination city and the number of junctions exceeds our max
                }
            }
        }
    }

    /**
     * Copies a route object so it can be added as a new instance to a collection.
     * @param routeToCopy the original route to be copied
     * @return the new route which is a copy of the original
     */
    public Route copyRoute(Route routeToCopy) {
        Route routeToOutPut = new Route();  //create a new route object...
        for (int i = 0; i < routeToCopy.getRoads().size(); i++) {   //...and copy its roads across
            routeToOutPut.addRoadToRoute(routeToCopy.getRoads().get(i));
        }
        return routeToOutPut;
    }

    /**
     * Outputs the list of routes found from the depth first searches
     * @return routes found as a list of routes
     */
    public List<Route> getListOfFoundRoutes() {
        return listOfFoundRoutes;
    }

    /**
     * For every route found from the recursive searches this method forms a set of routes as a string
     * @return set of routes found in String format (e.g. A-B-C)
     */
    public Set<String> getPrintedFoundRoutes(){
        Set<String> printedRoutes = new HashSet<String>();
        for (Route foundRoute : listOfFoundRoutes){
            printedRoutes.add(foundRoute.printRoute());
        }
        return printedRoutes;
    }

    /**
     * Exception that is thrown when no route can be found from a route query.
     */
    public static class NoSuchRouteException extends Exception {
        @Override
        public String toString(){
            return "NO SUCH ROUTE";
        }
    }
}

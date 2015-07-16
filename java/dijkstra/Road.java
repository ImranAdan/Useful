/**
 * @Author Mukund Bhudia
 */
public class Road {

    private String roadID;
    private String startCity;
    private String endCity;
    private int roadLength;

    /**
     * Represents a road and contains the length, the start city, end city and ID of the road.
     * @param givenRoadID takes in the road ID which is the in the form XYN where X is the start city,
     * Y the end city, and N the distance of the road that is between the start and end city
     */
    Road(String givenRoadID) {
        this.roadID = givenRoadID;  //We assume the roadID is entered in the correct format here
        //As we know (or assume!) the form of the roadID we can used substring to strip out the details
        this.startCity = givenRoadID.substring(0,1).toUpperCase();  //Ensure cities are uppercase
        this.endCity = givenRoadID.substring(1,2).toUpperCase();
        this.roadLength = Integer.parseInt(givenRoadID.substring(2,3));
    }

    /**
     * Returns the road ID used to construct the road object.
     * @return road ID as a string
     */
    public String getRoadID() {
        return roadID;
    }

    /**
     * Returns the city that the marks the beginning of the road.
     * @return the start city as a string
     */
    public String getStartCity() {
        return startCity;
    }

    /**
     * Returns the city that marks the end of the road.
     * @return the end city as a string
     */
    public String getEndCity() {
        return endCity;
    }

    /**
     * Returns the length of the road that is the distance between the start city and the end city.
     * @return the road length as an integer
     */
    public int getRoadLength() {
        return roadLength;
    }
}
/**
 * @Author: Mukund Bhudia
 */
public class Vertex {

    private String cityName;
    private int distance;
    private Vertex previous;

    public Vertex(){
    }

    /**
     * Vertex object is used in the Dijkstra's shortest route algorithm
     * @param cityName the vertex will represent a city so it takes in its name
     */
    public Vertex(String cityName){
        this.cityName = cityName.toUpperCase(); //Make sure city is entered as uppercase form
        this.distance = -1;
        this.previous = null;
    }

    /**
     * Obtains the city name that this vertex represents
     * @return the city name of the vertex as a string
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Returns the current distance from the source vertex (start city) to this vertex
     * @return integer of the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets the distance of the vertex from source to this vertex
     * @param distance takes in current distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Returns the previous vertex that the shortest route algorithm visited before this vertex
     * @return outputs the previous vertex objects
     */
    public Vertex getPrevious() {
        return previous;
    }

    /**
     * Sets he previous vertex that the shortest route algorithm visited before this vertex
     * @param previous takes in the previous vertex object
     */
    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (distance != vertex.distance) return false;
        return cityName.equals(vertex.cityName) && !(previous != null ? !previous.equals(vertex.previous) : vertex.previous != null);

    }

    @Override
    public int hashCode() {
        int result = cityName.hashCode();
        result = 31 * result + distance;
        result = 31 * result + (previous != null ? previous.hashCode() : 0);
        return result;
    }
}

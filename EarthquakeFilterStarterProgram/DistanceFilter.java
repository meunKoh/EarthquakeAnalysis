
/**
 * This class should include private instance variables for a location and a maximum distance, 
 * a constructor to initialize those variables, and a satisfies method that returns true 
 * if a QuakeEntry’s distance from the given location is less than the specified maximum distance. 
 * Otherwise it should return false.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-04)
 */
public class DistanceFilter implements Filter {
    private Location myLocation;
    private double distMax;
    
    public DistanceFilter(Location from, double max){
        myLocation = from;
        distMax = max;
    }

    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(myLocation) < distMax;
    }
    
    public String getName(){
        return "Distance";
    }
}

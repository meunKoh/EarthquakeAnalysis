
/**
 * This class should include private instance variables for a minimum and maximum depth, 
 * a constructor to initialize those variables, and a satisfies method that returns true 
 * if a QuakeEntry’s depth is between the minimum and maximum depths, 
 * or equal to one of them. Otherwise it should return false.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-04)
 */
public class DepthFilter implements Filter{
    private double depthMin;
    private double depthMax;
    
    public DepthFilter(double min, double max){
        depthMin = min;
        depthMax = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return (qe.getDepth() >= depthMin && qe.getDepth() <= depthMax);
    }
    
    public String getName(){
        return "Depth";
    }
}

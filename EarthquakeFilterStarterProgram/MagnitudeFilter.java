
/**
 * This class includes private instance variables for a minimum and maximum magnitude, 
 * a constructor to initialize those variables, and a satisfies method that returns true 
 * if a QuakeEntry’s magnitude is between the minimum and maximum magnitudes, 
 * or equal to one of them. Otherwise it should return false.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-04)
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
        
    public MagnitudeFilter(double min, double max) { 
        magMin = min;
        magMax = max;
    }
   
    public boolean satisfies(QuakeEntry qe){
        return (qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax);
    }
    
    public String getName(){
        return "Magnitude";
    }
}

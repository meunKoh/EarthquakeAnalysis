
/** 
 * @author (MyoungEun Koh) 
 * @version (2019-08-04)
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    
    public MinMagFilter(double min) { 
        magMin = min;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    
    public String getName(){
        return "MinMag";
    }
}

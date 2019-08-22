import java.util.Comparator;
/**
 * sort earthquakes by the last word in their title first and break ties by magnitude.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-08)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String idx = ", ";
        String lastWord1 = q1.getInfo().substring(q1.getInfo().indexOf(idx)+2);
        String lastWord2 = q2.getInfo().substring(q2.getInfo().indexOf(idx)+2);
        int compare = lastWord1.compareTo(lastWord2);
        if(compare == 0){
            compare = Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return compare;
    }

}

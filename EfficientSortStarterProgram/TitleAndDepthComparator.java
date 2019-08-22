import java.util.Comparator;
/**
 * Compare the titles in alphabetical order and break ties by depth.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-08)
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int compare = q1.getInfo().compareTo(q2.getInfo());
        if(compare == 0){
            compare = Double.compare(q1.getDepth(), q2.getDepth());
        }
        return compare;
    }

}

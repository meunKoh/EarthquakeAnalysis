import java.util.*;
import edu.duke.*;

/**
 * @author (MyoungEun Koh) 
 * @version (2019-08-04)
 */

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MagnitudeFilter(4.0, 5.0); 
        Filter f = new MagnitudeFilter(3.5, 4.5);
        ArrayList<QuakeEntry> l1  = filter(list, f); 
         
        //Filter f2 = new DepthFilter(-35000.0, -12000.0);
        Filter f2 = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> l2 = filter(l1, f2);
        
        for (QuakeEntry qe: l2) { 
            System.out.println(qe);
        }
        System.out.println("# that satisfies the filters are : " + l2.size());
        
        //Location japan = new Location(35.42, 139.43);
        Location denver = new Location(39.7392, -104.9903);
        Filter f3 = new DistanceFilter(denver, 1000*1000.0);
        ArrayList<QuakeEntry> l3 = filter(list, f3);
        //Filter f4 = new PhraseFilter("end", "Japan");
        Filter f4 = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> l4 = filter(l3, f4);
        for (QuakeEntry qe: l4) { 
            //System.out.println(qe);
        }
        System.out.println("# that satisfies the filters are : " + l4.size());
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);   
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        //Filter f1 = new MagnitudeFilter(0.0, 2.0);
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        maf.addFilter(f1);
        //Filter f2 = new DepthFilter(-100000.0, -10000.0);
        Filter f2 = new DepthFilter(-180000.0, -30000.0);
        maf.addFilter(f2);
        //Filter f3 = new PhraseFilter("any", "a");
        Filter f3 = new PhraseFilter("any", "o");
        maf.addFilter(f3);
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        
        for(QuakeEntry qe : filtered){
            System.out.println(qe);
        }
        System.out.println("# that satisfies the filters are : " + filtered.size());
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);   
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        //Filter f1 = new MagnitudeFilter(0.0, 3.0);
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        maf.addFilter(f1);
        
        //Location tulsa = new Location(36.1314, -95.9372);
        //Filter f2 = new DistanceFilter(tulsa, 10000*1000.0);
        Location billund = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(billund, 3000*1000.0);
        maf.addFilter(f2);
        
        //Filter f3 = new PhraseFilter("any", "Ca");
        Filter f3 = new PhraseFilter("any", "e");
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        
        for(QuakeEntry qe : filtered){
            System.out.println(qe);
        }
        System.out.println("# that satisfies the filters are : " + filtered.size());
        System.out.println("Filters used are: " + maf.getName());
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}

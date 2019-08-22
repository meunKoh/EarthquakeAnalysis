
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //return all the earthquakes from quakeData that have a magnitude larger than magMin.
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;              
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //return all the earthquakes from quakeData that are less than distMax from the location from. 
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public void bigQuakes() {
        //print earthquakes above a certain magnitude, and also print the number of such earthquakes. 
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        //System.out.println("read data for " + list.size() + " quakes");5.10
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        System.out.println("read data for " + listBig.size() + " quakes");
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void closeToMe() {
        //print out the earthquakes within 1000 Kilometers to a specified city (such as Durham, NC). 
        //For each earthquake found, print the distance from the earthquake to the specified city, 
        //followed by the information about the city (use getInfo()). 
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        System.out.println("# quakes read: " + close.size());
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }

    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        //return all the earthquakes from quakeData whose depth is between minDepth and maxDepth, exclusive. 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }           
        }
        return answer;
    }
    
    public void quakesOfDepth(){
        //print all the earthquakes from a data source whose depth is between a given minimum and maximum value.
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";   
        ArrayList<QuakeEntry> list = parser.read(source);
        double minDepth = -12000.0;
        double maxDepth = -10000.0;
        ArrayList<QuakeEntry> betweenDepth = filterByDepth(list,minDepth,maxDepth);
        System.out.println("Data source whose depth is between " + minDepth +" and "+ maxDepth);
        System.out.println("# quakes read: " + betweenDepth.size());
        for(int k=0; k<betweenDepth.size(); k++){
            QuakeEntry entry = betweenDepth.get(k);
            System.out.println("depth is : " + entry.getDepth() + ", info : " + entry.getInfo());
        }
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        //return all the earthquakes from quakeData whose titles have the given phrase found at location 
        //where (“start” means the phrase must start the title, “end” means the phrase must end the title 
        //and “any” means the phrase is a substring anywhere in the title.)
        ArrayList<QuakeEntry> filtered = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            String currTitle = qe.getInfo();
            if(where.equals("start")){
                if(currTitle.indexOf(phrase) == 0){
                    filtered.add(qe);
                }
            }
            if(where.equals("end")){
                if(currTitle.lastIndexOf(phrase) == currTitle.length() - phrase.length()){
                    filtered.add(qe);
                }
            }
            if(where.equals("any")){
                if(currTitle.indexOf(phrase) != -1){
                    filtered.add(qe);
                }
            }
        }
        return filtered;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        String where = "start";
        String phrase = "Quarry Blast";
        ArrayList<QuakeEntry> filtered = filterByPhrase(list,where,phrase);
        System.out.println("read data for " + list.size() + " quakes.");
        for(QuakeEntry qe : filtered){
            System.out.println(qe);
        }
        System.out.println("Found " + filtered.size() + " quakes that match " + phrase + " at " + where);
    }
}

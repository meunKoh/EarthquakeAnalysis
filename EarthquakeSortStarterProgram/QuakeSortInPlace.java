
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-07)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        //call checkInSortedOrder and stop early if the ArrayList is already sorted. 
        //This method should print how many passes were needed to sort the elements.
        int howMany = 0;
        for(int i=0; i<in.size(); i++){
            if(checkInSortedOrder(in)==true){
                System.out.println(howMany + " passes were needed to sort the elements.");
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            howMany++;
        }
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        //returns the index position of the QuakeEntry with the largest depth considering only those 
        //QuakeEntry’s from position from to the end of the ArrayList.
        int largeIdx = from;
        for(int i=from+1; i<quakeData.size(); i++){
            if(quakeData.get(i).getDepth() > quakeData.get(largeIdx).getDepth()){
                largeIdx = i;
            }
        }
        return largeIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        //sorts 'in' by depth in reverse order from largest depth to smallest depth.
        int howMany = 0;
        for(int i=0; i<in.size(); i++){
            int largeIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qlarge = in.get(largeIdx);
            in.set(i,qlarge);
            in.set(largeIdx,qi);
            howMany++;
            if(howMany == 50){
                break;
            }
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        //makes one pass of bubble sort on the ArrayList. It should take advantage of the fact 
        //that the last numSorted elements are already in sorted order.
        for(int i=0; i<quakeData.size()-1; i++){
            QuakeEntry qFormer = quakeData.get(i);
            QuakeEntry qLatter = quakeData.get(i+1);
            if(qFormer.getMagnitude() > qLatter.getMagnitude()){
                quakeData.set(i,qLatter);
                quakeData.set(i+1,qFormer);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        //call onePassBubbleSort in.size() – 1 times to sort the elements in in.
        for(int i=0; i<in.size()-1; i++){
            onePassBubbleSort(in,i);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        //returns true if the earthquakes are in sorted order by magnitude from smallest to largest.
        for(int i=0; i<quakes.size()-1; i++){
            if(quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        //call checkInSortedOrder and stop early if the ArrayList is already sorted. 
        //This method should print how many passes were needed to sort the elements.
        int howMany = 0;
        for(int i=0; i<in.size()-1; i++){
            if(checkInSortedOrder(in)==true){
                System.out.println(howMany + " passes were needed to sort the elements.");
                break;
            }
            onePassBubbleSort(in,i);
            howMany++;
        }
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/earthQuakeDataWeekDec6sample1.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        // sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            //System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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

import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-02)
 */

public class LargestQuakes {
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("read data for " + list.size() + " quakes");
        System.out.println("the largest earthquake is at location " 
                           + indexOfLargest(list)
                           + " and has magnitude "
                           + list.get(indexOfLargest(list)).getMagnitude());
        int howMany = 50;                           
        System.out.println("The largest " + howMany + " earthquakes in this data : ");
        ArrayList<QuakeEntry> largeList = getLargest(list,howMany);
        for(QuakeEntry qe : largeList){
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        //returns an integer representing the index location in data of the earthquake with the largest magnitude. 
        int largest = 0;
        for(int i=0; i<data.size(); i++){
            if(data.get(i).getMagnitude() > data.get(largest).getMagnitude()){
                largest = i;
            }
        }
        return largest;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        //returns an ArrayList of type QuakeEntry of the top howMany largest magnitude earthquakes from quakeData. 
        //The quakes returned should be in the ArrayList in order by their magnitude, with the largest magnitude earthquake in index position 0. 
        //If quakeData has fewer than howMany earthquakes, then the number of earthquakes returned in the ArrayList 
        //is equal to the number of earthquakes in quakeData
        ArrayList<QuakeEntry> copy = quakeData;
        ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
        for(int i=0; i<howMany; i++){
            int currLarge = indexOfLargest(copy);
            result.add(copy.get(currLarge));
            copy.remove(currLarge);
        }
        return result;
    }
}

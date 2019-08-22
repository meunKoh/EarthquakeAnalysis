import java.util.ArrayList;
/**
 * This class has a private ArrayList of Filters that is created in the constructor that has no parameters. 
 * This class has two methods, 
 * 1) a void method named addFilter with one Filter parameter that adds the Filter to its private ArrayList
 * 2) a method named satisfies that has one QuakeEntry parameter and returns true 
 * if the QuakeEntry satisfies all the filter conditions, otherwise it returns false.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-04)
 */
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        boolean result = false;
        int trues = 0;
        for(Filter f : filters){
            if(f.satisfies(qe)){
                trues++;
            }
        }
        if(trues == filters.size()){
            result = true;
        }
        return result;
    }
    
    public String getName(){
        String names = "";
        for(Filter f : filters){
            names += f.getName() + "\t" ;
        }
        return names;
    }
}

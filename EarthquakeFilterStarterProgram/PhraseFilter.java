
/**
 * This class includes two private instance variables for 
 * 1) a String representing the type of request that indicates where to search 
 *    in the title and has one of three values: (“start”, ”end”, or “any”)
 * 2) a String indicating the phrase to search for in the title of the earthquake 
 *   (Note the title of the earthquake can be obtained through the getInfo method). 
 * This class also has a constructor to initialize those variables, and a satisfies method 
 * that returns true if the phrase is found at the requested location in the title. 
 * If the phrase is not found, this method should return false.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-04)
 */
public class PhraseFilter implements Filter{
    private String whe;
    private String phr;
    
    public PhraseFilter(String where, String phrase){
        whe = where;
        phr = phrase;
    }

    public boolean satisfies(QuakeEntry qe){
        boolean result = false;
        String title = qe.getInfo();
        if(whe.equals("start")){
            if(title.indexOf(phr) == 0){
                result = true;
            }
        }
        if(whe.equals("end")){
            if(title.lastIndexOf(phr) == (title.length() - phr.length())){
                result = true;
            }
        }
        if(whe.equals("any")){
            if(title.indexOf(phr) != -1){
                result = true;
            }
        }
        return result;
    }
    
    public String getName(){
        return "Phrase";
    }
}

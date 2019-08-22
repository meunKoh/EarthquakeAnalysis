
/**
 * Write a description of interface Filter here.
 * 
 * @author (MyoungEun Koh) 
 * @version (2019-08-04)
 */
public interface Filter
{
    public boolean satisfies(QuakeEntry qe); 
    public String getName();
}

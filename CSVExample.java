import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of class CSVExample here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CSVExample
{
    public static void readFood()
    {
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        for(CSVRecord record:parser)
        {
            System.out.println(record.get("Name")+" ");
            System.out.println(record.get("Favorite Color")+"");
            System.out.println(record.get("Favorite Food")+" ");
        }
    }
public static void main(String args[])
{
	readFood();
}
}
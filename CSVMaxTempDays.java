import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of class CSVMaxTempDays here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CSVMaxTempDays
{
    public CSVRecord hottestHourInFile(CSVParser parser)
    {
        CSVRecord largestSoFar=null;
        for(CSVRecord currentRow: parser)
        {
           largestSoFar=getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    public void testHottestInDay()
    {
        FileResource fr=new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest=hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was "+largest.get("TemperatureF")+" at "+largest.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays()
    {
        CSVRecord largestSoFar=null;
        DirectoryResource dr=new DirectoryResource();
        for(File f: dr.selectedFiles())
        {
             FileResource fr=new FileResource(f);
             CSVRecord currentRow=hottestHourInFile(fr.getCSVParser());
             largestSoFar=getLargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }
    public void testHottestInManyDays()
    {
        CSVRecord largest=hottestInManyDays();
        System.out.println("hottest temperature was "+largest.get("TemperatureF")+" at "+largest.get("DateUTC"));
    }
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar)
    {
        if(largestSoFar==null)
        {
           largestSoFar=currentRow;
        }
        else
        {
            double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp=Double.parseDouble(largestSoFar.get("TemperatureF"));           
            if(currentTemp>largestTemp)
            {
                largestSoFar=currentRow;
            }
        }
        return largestSoFar;
    }
    public static void main(String args[])
    {
        CSVMaxTempDays t=new CSVMaxTempDays();
        t.testHottestInManyDays();
    }
}

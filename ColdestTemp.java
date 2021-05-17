import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of class ColdestTemp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColdestTemp
{
    public CSVRecord coldestHourInFile(CSVParser parser) 
    {
        CSVRecord coldestSoFar = null;       
        for (CSVRecord current: parser) 
        {
            if (coldestSoFar == null) 
            {
                coldestSoFar = current;
            }
            else
            {
             double currentTem = Double.parseDouble(current.get("TemperatureF"));
             double coldestTem = Double.parseDouble(coldestSoFar.get("TemperatureF"));
             if (currentTem != -9999 && currentTem < coldestTem) {
                 coldestSoFar = current;
             }
            }
        }
        return coldestSoFar;
    }

    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord result = coldestHourInFile(fr.getCSVParser());
        System.out.print("The coldest temperature : ");
        System.out.println(result.get("TemperatureF"));
    }
    
    public String fileWithColdestTemperature()
    {    
        CSVRecord coldestSoFar=null;
        String coldestFileName = null;
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) 
            {
                coldestSoFar = currentRow;
            }
            else
            {
                double currentTem = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTem = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTem != -9999 && currentTem < coldestTem) 
                {
                    coldestSoFar = currentRow;
                    coldestFileName=f.getName();
                }
            }
        }
        return coldestFileName;
    }
    
    public void testFileWithColdestTemperature() 
    {
        String coldestname = fileWithColdestTemperature();
        System.out.print("Coldest day was in file ");
        System.out.println(coldestname);
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.print("The coldest temperature on that day was ");
        System.out.println(coldest.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were");
        for (CSVRecord record:fr.getCSVParser()) 
        {
            System.out.print(record.get("DateUTC"));
            System.out.print(" ");
            System.out.println(record.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
         CSVRecord lowestSoFar = null;
         double lowest = 0;
         double current = 0;
         for (CSVRecord record: parser) 
         {
            if (lowestSoFar == null) 
            {
                lowestSoFar = record;
            }
            if (record.get("Humidity").equals("N/A")) 
            {
                current = -999;
            }
            else 
            {
                current = Double.parseDouble(record.get("Humidity"));
            }            
            if (lowestSoFar.get("Humidity").equals("N/A")) 
            {
                lowest = -999;
            }
            else 
            {
                lowest = Double.parseDouble(lowestSoFar.get("Humidity"));
            }           
            if (current < lowest && current != -999) 
            {
                lowestSoFar = record;
            }
         }
         return lowestSoFar;
    }
    public void testLowestHumidityInFile() 
    {
        FileResource fr = new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord result = lowestHumidityInFile(parser);
        System.out.print("Lowest Humidity was ");
        System.out.print(result.get("Humidity"));
        System.out.print(" at ");
        System.out.println(result.get("DateUTC"));
    }
    public String lowestHumidityInManyFiles() 
    {
        DirectoryResource dr =  new DirectoryResource();
        CSVRecord lowest = null;
        String filename = null;
        for (File f: dr.selectedFiles()) 
        {
            FileResource fr = new FileResource(f);
            CSVParser parser=fr.getCSVParser();
            CSVRecord current = lowestHumidityInFile(parser);
            if (lowest == null) 
            {
                lowest = current;
            }
            else
            {
            double currentHum = Double.parseDouble(current.get("Humidity"));
            double lowestHum = Double.parseDouble(lowest.get("Humidity"));
            if (currentHum < lowestHum) 
            {
                lowest = current;
                filename = f.getName();
            }
            }
        }
        return filename;
    }    
    public void testLowestHumidityInManyFile() 
    {
         
        String filename = lowestHumidityInManyFiles();
        System.out.print("Day with lowest humidity was in file ");
        System.out.println(filename);
        FileResource fr = new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.print("Lowest Humidity was ");
        System.out.print(lowest.get("Humidity"));
        System.out.print(" at ");
        System.out.println(lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) 
    {
        double sum = 0;
        int number = 0;
        for (CSVRecord record:parser) 
        {
            double current = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + current;
            number = number + 1;
        }
        sum = sum / number;
        return sum;
    }    
    public void testTemperatureInFile() 
    {
        FileResource fr = new FileResource();
        double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.print("Average temperature in file is ");
        System.out.println(average);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        double number = 0;
        double humidity = 0;
        for (CSVRecord record: parser) 
        {
            if (record.get("Humidity").equals("N/A")) 
            {
                humidity = -999;
            }
            else 
            {
                humidity = Double.parseDouble(record.get("Humidity"));
            }
            if (humidity >= value) 
            {
                number = number + 1;
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
            }
        }       
        return sum/number;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() 
    {
        FileResource fr = new FileResource();
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (average == 0) 
        {
            System.out.println("No temperatures with that humidity");
        }
        else 
        {
            System.out.print("Average temperature with high Humidity is ");
            System.out.println(average);
        }
    }
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        parser=fr.getCSVParser();
        testFileWithColdestTemperature();
    }
    public static void main(String args[])
    {
        ColdestTemp ct=new ColdestTemp();
        ct.tester();
    }
}


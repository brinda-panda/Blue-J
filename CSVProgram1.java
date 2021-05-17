import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVProgram1 
{
    public void tester() 
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        parser=fr.getCSVParser();
        countryInfo(parser, "Nauru");
        parser=fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
	numberOfExporters(parser, "cocoa");
	parser = fr.getCSVParser();
	bigExporters(parser, "$999,999,999,999");
    }
    //Part 2
    public void countryInfo(CSVParser parser, String country) 
    {
        System.out.println("Info about countries ");
        //for each row in the CSV File
        for (CSVRecord record : parser) 
        {
            //Look at the "Exports" column
            String country_info = record.get("Country");
            //Check if it contains exportOfInterest
            if (country_info.contains(country)) {
                //If so, write down the "Country" from that row
                String c = record.get("Country");
                System.out.println(record.get("Country") + " " + record.get("Exports") +" " + record.get("Value (dollars)"));
            }
        }
    }
    //Part 3
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        System.out.println();
	for(CSVRecord record : parser)
	{
	     String Exports = record.get("Exports");
	     if(Exports.contains(exportItem1) && Exports.contains(exportItem2))
	     {
	         System.out.println( record.get("Country"));
	     }
        }
    }
    //Part 4
    public void numberOfExporters(CSVParser parser, String exportItem)
    {
        int count=0;
        for(CSVRecord record: parser ){
            String exports=record.get("Exports");
            if(exports.contains(exportItem))
            {
                count++;
            }
        }
        System.out.println("\nThere are " + count + " countries export " + exportItem + ".");
    }
    //Part 5
    public void bigExporters(CSVParser parser, String amount)
    {
        System.out.println();
        for(CSVRecord record:parser)
        {
            String value=record.get("Value (dollars)");
            if(value.length()>amount.length())
            {
                System.out.println(record.get("Country") + ", " + record.get("Value (dollars)") );
            }
        }
    }
    public static void main(String args[])
    {
        CSVProgram1 CP=new CSVProgram1();
        CP.tester();
    }
}

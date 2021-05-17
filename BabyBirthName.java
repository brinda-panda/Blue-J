import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of class BabyBirthName here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BabyBirthName
{
    public void printNames() 
    {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) 
        {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) 
            {
                System.out.println("Name " + rec.get(0) +" Gender " + rec.get(1) +" Num Born " + rec.get(2));                       
            }
        }
    }
    public void totalBirths(FileResource fr)
    {
        int totalBirths=0;
        int numGirl = 0;
        int numBoy = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) 
        {
             int numBorn = Integer.parseInt(rec.get(2));
             String gender = rec.get(1);
             if(gender.equals("F")) 
                numGirl++;
             else 
                numBoy++;
                
             totalBirths++;
        }
        System.out.println("Girls: " + numGirl +", Boys: " + numBoy +", Total: " + totalBirths);
    }
    public void testTotalBirths()
    {
         FileResource fr = new FileResource("us_babynames_by_year/yob1905.csv");
         totalBirths(fr);
    }
    public void totalBoysToGirls(FileResource fr)
    {
        int totalBirths=0, totalGirls=0, totalBoys=0;
        for (CSVRecord rec : fr.getCSVParser(false)) 
        {
             int numBorn = Integer.parseInt(rec.get(2));
             totalBirths+=numBorn;
             if(rec.get(1).equals("M"))
             {
                 totalBoys+=numBorn;
             }
             else
             {
                 totalGirls+=numBorn;
             }
        }
        System.out.println("Total Births= "+totalBirths);
        System.out.println("Total Girls= "+totalGirls);
        System.out.println("Total Boys= "+totalBoys);
    }
     public void testTotalBoysToGirls()
    {
         FileResource fr = new FileResource("data/yob1900.csv");
         totalBoysToGirls(fr);
    }
    public int getRank(int year, String name, String gender)
    {
        FileResource fr = new FileResource();
        int rank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) 
        {
            if (record.get(1).equals(gender)) 
            {
                rank++;
                if (record.get(0).equals(name)) 
                {
                    return rank;
                }
            }       
        }
         return -1;
    }
    public void testgetRank() 
    {
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    public String getName(int year, int rank, String gender)
    {
        FileResource fr = new FileResource();
        int num = 0;
        for (CSVRecord record : fr.getCSVParser(false)) 
        {
            if (record.get(1).equals(gender)) 
            {
                num++;
                if (num==rank) 
                {
                    return record.get(0);
                }
            }       
        }
         System.out.println("the rank: " + rank + "... The last one rank " + num + ".");
         return "No Name";
    }
    public void testgetName() 
    {
        String result = getName(1982,450, "M");
        System.out.println(result);
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank=getRank(year,name,gender);
        String equalName=getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+" would be "+equalName+" if was born in "+newYear);
    }
    public void testWhatIsNameInYear() 
    {
        whatIsNameInYear("Owen",1974, 2014,"M");
    }
    public int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr=new DirectoryResource();
        int ranktonow = 0;
        int findall = 0;
        int yearHigh = 0;
        for (File f: dr.selectedFiles())
        {
            String filename = f.getName();
            int year = Integer.parseInt(filename.substring(3,7));
            FileResource fr=new FileResource(f);
            int rank = 0;
            int find = 0;
            for(CSVRecord record:fr.getCSVParser(false))
            {
                if (record.get(1).equals(gender)) 
                {
                    rank += 1;
                    if (record.get(0).equals(name)) 
                    {
                        find = 1;
                        break;
                    }
                }
            }
            if (find == 1) 
            {
                findall = 1;
                if (ranktonow == 0) 
                {   ranktonow = rank;
                    yearHigh=year;
                }
                else if (ranktonow > rank) 
                {    ranktonow = rank;
                     yearHigh=year;
                }
            }
        }
        return yearHigh;
    }
    public void testyearOfHighestRank() 
    {
        int year = yearOfHighestRank("Mich", "M");
        System.out.println("Highest rank is in year  "+year);
    }
    public double getAverageRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int fileNum = 0;
	int totalRank = 0;	
	for(File fi : dr.selectedFiles())
	{
		fileNum++;
		FileResource fr = new FileResource(fi);	
		int pivot = 0;
		int currRank = 0;
		for(CSVRecord record : fr.getCSVParser(false) )
		{			
		    if(record.get(1).equals(gender)) 
		    {    pivot++;
		        if(record.get(0).equals(name)) 
		        {		
		            currRank = pivot;
		            break;
			} //end if record.equals name condition;
                   }		
		}//end for Record loop;
		totalRank += currRank;			
	}//end for file loop;	
	if(totalRank == 0) 
	   return -1;
	else 
	   return (double)(totalRank)/fileNum;
    }
    public void testGetAverageRank()
    {
         System.out.println("Average rank is "+getAverageRank("Robert", "M"));
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) 
    {
        FileResource fr = new FileResource();
	int sum = 0;
	for(CSVRecord record : fr.getCSVParser(false))
	{
	    if(record.get(1).equals(gender))
	    {
	        if(record.get(0).equals(name)) 
	        {
	             return sum;
	        }
		sum += Integer.parseInt(record.get(2));					
            }		
        }     
	return sum;
    }
    public void testTotalBirthsRankedHigher()
    {
         System.out.println(getTotalBirthsRankedHigher(1990,"Drew", "M"));
    }
    
    public static void main(String args[])
    {
        BabyBirthName Bname=new BabyBirthName();
        Bname.testTotalBirths();
    }
}

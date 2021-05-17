package StringsFirstAssignments;
import edu.duke.*;

/**
 * Write a description of class Part4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part4
{
    public void processWebPage()
    {
    URLResource webPage=new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
      for ( String word : webPage.words() )
      {
        String lowerCase=word.toLowerCase();
        if(lowerCase.contains("youtube.com"))
        {
            int start=lowerCase.indexOf("\"");
            int end=lowerCase.indexOf("\"");
            String ytLink=word.substring(start+1,end);
            System.out.println(ytLink);
        }
      }
    }
}

package StringsSecondAssignments;


/**
 * Write a description of class Part2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part2
{
    public int howMany(String stringa, String stringb)
    {
        int ctr=0;
        int firstOccur=stringb.indexOf(stringa);
        if(firstOccur>-1)
        {
            ctr++;
            while(stringb.indexOf(stringa,firstOccur)!=-1 && firstOccur!=-1)
          {
            ctr++;
            firstOccur=stringb.indexOf(stringa,firstOccur+stringa.length());
          }
        ctr--;
        }
        else
        {
            ctr=0;
        }
        return ctr;
    }
    public void testHowMany()
    {
        String stringa="AB";
        String stringb="CABTACAB";
        int count= howMany(stringa, stringb);
        if(howMany(stringa,stringb)==0)
        {
            System.out.println("No occurence found");
        }
        else
        {
            System.out.println("Last Count is: "+count);
        }
        stringa = "ACAB";
        stringb = "AAAAACABACABAAAACABACABAA";
        count= howMany(stringa,stringb);
        if (howMany(stringa,stringb) == 0) {
            System.out.println("No occurrence found");
        }
        else{
            System.out.println("Last Count is: " +count);
        }
    }
}

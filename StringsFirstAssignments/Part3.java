package StringsFirstAssignments;


/**
 * Write a description of class Part3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part3
{
    // instance variables - replace the example below with your own
    public boolean twoOccurrences(String stringa, String stringb)
    {
        int occur=stringb.indexOf(stringa);
        int ctr=0;
        while(occur>=0)
        {
            ctr++;
            occur=stringb.indexOf(stringa,occur+1);
        }
        return ctr>=2?true:false;

    }
    public void testing()
    {
        String stringa = "by";
        String stringb = "A story by Abby Long";
        System.out.println("stringa = "+stringa + " and stringb = " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        
        stringa = "a";
        stringb = "banana";
        System.out.println("stringa = "+stringa + " and stringb = " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        
        stringa = "a";
        stringb = "melocoton";
        System.out.println("stringa = "+stringa + " and stringb = " + stringb);
        System.out.println(twoOccurrences(stringa, stringb));
    }
    public void testingLastPart()
    {
        String stringa = "an";
        String stringb = "banana";
        System.out.println("The part of the string after " + stringa + " in " +stringb +" is "+ lastPart(stringa, stringb));
        
        stringa = "rest";
        stringb = "deforestacion";
        System.out.println("The part of the string after " + stringa + " in " +stringb +" is "+ lastPart(stringa, stringb));
        
        stringa = "cio";
        stringb = "deforestacion";
        System.out.println("The part of the string after " + stringa + " in " +stringb +" is "+ lastPart(stringa, stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("The part of the string after " + stringa + " in " +stringb +" is "+ lastPart(stringa, stringb));
    }
    public String lastPart(String stringa, String stringb)
    {
        int firstOccur=stringb.indexOf(stringa);
        int len=stringa.length();
        String str=stringb.substring(len+firstOccur);
        if(firstOccur==-1)
        {
            return stringb;
        }
        else
        {
            return str;
        }
    }
}

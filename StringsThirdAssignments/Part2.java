package StringsThirdAssignments;
import edu.duke.*;

/**
 * Write a description of class Part2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part2
{
    public int findStopCodon(String dna,int startIndex,String stopCodon)
    {
        startIndex=dna.indexOf("ATG");
        int currIndex=dna.indexOf(stopCodon,startIndex+3);
        while(currIndex!=-1)
        {
            int diff=(currIndex-startIndex)%3;
            if(diff==0)
            {
                return currIndex;
            }
            else
            {
                currIndex=dna.indexOf(stopCodon,currIndex+1);
            }
        }
        return dna.length();
    }
    public void testFindStopCodon()
    {
               //    01234567890123456789012345
        String dna= "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex=findStopCodon(dna,0,"TAA");
        if(dex!=9)
            System.out.println("error on 9");
        dex=findStopCodon(dna,9,"TAA");
        if(dex!=21)
            System.out.println("error on 21");
        dex=findStopCodon(dna,9,"TAA");
        if(dex!=26)
            System.out.println("error on 26");
        dex=findStopCodon(dna,0,"TAG");
        if(dex!=26)
            System.out.println("error on 26 TAG");
        System.out.println("Tests Finished");
    }
    public String findGene(String dna)
    {
        int startCodon=dna.indexOf("ATG");
        if(startCodon==-1)
        {
            return "";
        }
        int taaCodon=findStopCodon(dna,startCodon,"TAA");
        int tagCodon=findStopCodon(dna,startCodon,"TAG");
        int tgaCodon=findStopCodon(dna,startCodon,"TGA");
        int temp=Math.min(taaCodon,tagCodon);
        int dnaFound=Math.min(temp,tgaCodon);
        if(dnaFound==dna.length())
        {
            return "";
        }
        return dna.substring(startCodon,dnaFound+3);
    }
    public void printAllGenes(String dna)
    {
        int startIndex=0;
        while(true)
        {
            String currentGene=findGene(dna);
            if(currentGene.isEmpty())
            {
                break;
            }
            System.out.println(currentGene);
            startIndex=dna.indexOf(currentGene, startIndex)+currentGene.length();
        }
    }
    public StorageResource getAllGenes(String dna)
    {
        StorageResource geneList=new StorageResource();
        int startIndex=0;
        while(true)
        {
            String currentGene=findGene(dna);
            if(currentGene.isEmpty())
            {
                break;
            }
            geneList.add(currentGene);
            startIndex=dna.indexOf(currentGene, startIndex)+currentGene.length();
        }
        return geneList;
    }
    public void testGetAllGenes(String dna)
    {
        System.out.println("Testing printAllGenes on "+dna);
        StorageResource sr=getAllGenes(dna);
        for(String gene:sr.data())
        {
            System.out.println("This is the list of genes: "+gene);
        }
    }
     public void test(){
        //      ATGv  TAAV  ATGvvv      TGA
        testGetAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testGetAllGenes("");
        testGetAllGenes("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    public float cgRatio(String dna)
    {
        int firstOccurC=dna.indexOf("C");
        int firstOccurG=dna.indexOf("G");
        int countC=0,countG=0;
        if(firstOccurC>-1)
        {
            countC=countC+1;
            while(dna.indexOf("C",firstOccurC)!=-1 && firstOccurC!=-1)
            {
                countC=countC+1;
                firstOccurC=dna.indexOf("C",firstOccurC+1);
            }
            countC=countC-1;
        }
        else
        {
            countC=0;          
        }
        if(firstOccurG>-1)
        {
            countG=countG+1;
            while(dna.indexOf("G",firstOccurG)!=-1 && firstOccurG!=-1)
            {
                countG=countG+1;
                firstOccurG=dna.indexOf("G",firstOccurG+1);
            }
            countG=countG-1;
        }
        else
        {
            countG=0;          
        }
        float finalResult=(float)countC/countG;
        return finalResult;
    }
    public int countCTG(String dna)
    {
         dna = dna.toLowerCase();
        int count = dna.length() - dna.replace("ctg", "").length();
        System.out.println("CTG Count: " + count / 3);
        return count;
    }
    public void testCountCTG()
    {
        String dna="ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println("Count: "+countCTG(dna));
    }
}

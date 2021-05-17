package StringsThirdAssignments;
import edu.duke.*;

/**
 * Write a description of class Part2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part3
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
     public String findGene(String dna, int start) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int temp = Math.min(taaIndex,tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if(minIndex == dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    public void printAllGenes(String dna)
    {
        int startIndex=0;
        while(true)
        {
            String currentGene=findGene(dna,startIndex);
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
            String currentGene=findGene(dna,startIndex);
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
        StorageResource genes=getAllGenes(dna);
        for(String g:genes.data())
        {
            System.out.println(g);
        }
    }
    public void testProcessGenes()
    {
        processGenes(CreationofSR());
    }
     public StorageResource CreationofSR()
     {
        StorageResource sr = new StorageResource();
        sr.add("ATGCCCCGGTAA");
        sr.add("ATGCCCGGGTTTTTTTTTTTTTTTTAA");
        sr.add("ATGTTTTTTTTTTTTTTTTAA");
        for(String s : sr.data()){
            System.out.println("this is my list of genes: " + s);
        }
        return sr;
    }
     public void test(){
        //      ATGv  TAAV  ATGvvv      TGA
        testGetAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testGetAllGenes("");
        testGetAllGenes("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    public float cgRatio (String dna){
        int indexC = dna.indexOf("C");
        int countC = 0;
        while( indexC != -1) {
            countC +=1;
            indexC=dna.indexOf("C", indexC + 1);
        }
        int indexG = dna.indexOf("G");
        int countG = 0;
        while( indexG != -1) {
            countG +=1;
            indexG=dna.indexOf("G", indexG + 1);
        }
        return (float)(countC+ countG)/ dna.length();
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
    public void processGenes(StorageResource sr)
    {
        int count_1 = 0;
        int count_2 = 0;
        int max = 0;
        String maxStr ="";
        for (String str : sr.data()){
            if(str.length()> max){
                max = str.length();
                maxStr =str;
            }
            if( str.length() > 60){
                System.out.println(str);
                count_1++;
            }
            if (cgRatio (str) > 0.35) {
                System.out.println(str);
                count_2++;
            }
        }
        System.out.println("Longest gene is : " + max + " is " +maxStr);
        System.out.println("Gene greater than 60 nucleotides : "+ count_1);
        System.out.println("Gene greater than 0.35 CG ration :" + count_2);
    }
    public StorageResource srIsFile()
    {
        FileResource fr = new FileResource("brca1line.fa");
        String Newdna = fr.asString();
        StorageResource sr = new StorageResource();
        sr.add(Newdna);
        for(String s : sr.data()){
            System.out.println("this is my list of genes: " + s);
        }
        return sr;
    }
    public void processGenes2(StorageResource sr)
    {
        for (String getGenes : sr.data()){
            System.out.println("this is what you see " +getGenes);
            printAllGenes(getGenes);
            //String takeString = getAllGenes(getGenes);
            /*System.out.println(takeString+ " length = " + takeString.length() );
            while (longer60.length() > 60) {
                if (takeString.length() == -1){
                    break;
                }
                
            }
            System.out.println(takeString+ " length = " + takeString.length() );
            takeString = longer60.substring(takeString.length(),60);*/
            }
    }
        public void testProcessGenes2(){
        processGenes2(srIsFile());
    }
     public void testcgRatio(){
        String dna="CCCCAAGCCC";
        System.out.println("Result of all should be 7 and is: "+cgRatio(dna));
        dna="CCCGGGGGAAAGGG";
        System.out.println("Number of all should be 0.37 and is: "+cgRatio(dna));
        dna = "AAACCCCAAGG";
        System.out.println("Number of all should be 2 and is: "+cgRatio(dna));
    }
}

package StringsFirstAssignments;
public class Part2
{
    // instance variables - replace the example below with your ow

    /**
     * Constructor for objects of class Part1
     */
        // initialise instance variables
        public String findSimpleGene(String dna,String startCodon,String stopCodon)
        {
            String result="",result1="";
            int atg=dna.indexOf("ATG");
            if(atg==-1)
              {  
                  return "";
               }
            int taa=dna.indexOf("TAA",atg+3);
            if(taa==-1)
               { 
                   return "";
                }
            else{
                result1=dna.substring(atg,taa+3);
                result= result1.toLowerCase();
                int compare=result.length()%3;
                if(compare==0)
                {
                   return result;
                }            
            }
            return result;
        }
        public void testSimpleGene()
        {
            String startCodon = "ATG";
            String stopCodon = "TAA";
           String gene = "";
           String dna = "AAAATACCAGTACCACTAAGGA";
           System.out.println("DNA Strand is = " + dna);
           gene = findSimpleGene(dna,startCodon,stopCodon);
           System.out.println("Gene is = " + gene);
      
          dna = "ATCATGAACAACGGA";
          System.out.println("There is a Strand = " + dna);
          gene = findSimpleGene(dna,startCodon,stopCodon);
          System.out.println("Gene is = " + gene);
      
          dna = "ATCGAATCCAAT";
          System.out.println("There is a Strand = " + dna);
          gene = findSimpleGene(dna,startCodon,stopCodon);
          System.out.println("Gene is = " + gene);
      
          dna = "ATCATCATGGTGGTTTAAGAC";
           System.out.println("There is a Strand = " + dna);
           gene = findSimpleGene(dna,startCodon,stopCodon);
           System.out.println("Gene is = " + gene);
      
           dna = "ATGCGCCGTAA";
           System.out.println("There is a Strand = " + dna);
           gene = findSimpleGene(dna,startCodon,stopCodon);
           System.out.println("Gene is = " + gene);
        }
 }

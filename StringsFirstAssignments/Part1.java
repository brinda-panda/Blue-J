package StringsFirstAssignments;


/**
 * Write a description of class Part1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part1
{
    // instance variables - replace the example below with your ow

    /**
     * Constructor for objects of class Part1
     */
        // initialise instance variables
        public String findSimpleGene(String dna)
        {
            String result="";
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
                result=dna.substring(atg,taa+3);
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
           String gene = "";
           String dna = "AAAATACCAGTACCACTAAGGA";
           System.out.println("DNA Strand is = " + dna);
           gene = findSimpleGene(dna);
           System.out.println("Gene is = " + gene);
      
          dna = "ATCATGAACAACGGA";
          System.out.println("There is a Strand = " + dna);
          gene = findSimpleGene(dna);
          System.out.println("Gene is = " + gene);
      
          dna = "ATCGAATCCAAT";
          System.out.println("There is a Strand = " + dna);
          gene = findSimpleGene(dna);
          System.out.println("Gene is = " + gene);
      
          dna = "ATCATCATGGTGGTTTAAGAC";
           System.out.println("There is a Strand = " + dna);
           gene = findSimpleGene(dna);
           System.out.println("Gene is = " + gene);
      
           dna = "ATGCGCCGTAA";
           System.out.println("There is a Strand = " + dna);
           gene = findSimpleGene(dna);
           System.out.println("Gene is = " + gene);
        }
 }


    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
   


package LoadStore;

import LoadStore.CandidateFunction.CandidateSolution;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.xml.stream.events.Comment;



public class HillClimbing {
    public CandidateSolution candidate;
    String analysisString="";
    double stalenessFactor=15;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    int temperature= 1000;
    /*C:\Users\marti\git\SoftWare-Engineering\src\LoadStore\DataHandling.java
     * constructor
     * @param candidate
    */
    public HillClimbing(CandidateSolution candidate)
    {
        this.candidate=candidate;
    }
     /*
     * Hill Climbing method
     * @param candidate
    */ 
    
    public void startHillClimbing(){
        Scanner myObj = new Scanner(System.in);
    int numImprovements=0;
    Double improvementAmount= (double) 0;
    Double worseningAmount= (double) 0;
    Random rand=new Random();
    
    int numWorsening=0;
        int numberOfEpochs=1000000;
        double changeInEnergy;
        double previousEnergy=0;
        double minFound=1000;
        for(int i=0;i<numberOfEpochs;i++){
            CandidateSolution testCandidate=new CandidateSolution();
            testCandidate.setStudentProjectAllocations(candidate.getStudentProjectAllocations());
            testCandidate.randomChange(2);
            
            double testEnergy=testCandidate.checkEnergy();
            double cadidateEnergy=candidate.checkEnergy();
            //System.out.println("testEnergy= "+testEnergy+"      ,   "+"initialEnergy= "+candidate.checkEnergy() );
            double energyDiff=testEnergy-cadidateEnergy;
           // System.out.println(energyDiff );
           temperatureIncrement();

            if(i%100000==0){
                changeInEnergy=previousEnergy-cadidateEnergy;
                previousEnergy=cadidateEnergy;
                calculateStaleness(changeInEnergy);
                analysisString+=i +" - "+(i-10000) +"Change in energy:"+df2.format(changeInEnergy)  +"\n Total improvements: " +df2.format(improvementAmount) + "     worseningAmount= " + df2.format(worseningAmount);
                System.out.println("\ncurrentEnergy="+cadidateEnergy +"   epochNum=" +i +"   min Found:"+minFound);
                improvementAmount= (double) 0;
                worseningAmount= (double) 0;
            }
           


            if(Double.compare(testEnergy, cadidateEnergy)==-1){
                candidate.setStudentProjectAllocations(testCandidate.getStudentProjectAllocations());
                numImprovements++;
                improvementAmount+=energyDiff;
                minFound=(cadidateEnergy<minFound)?(cadidateEnergy):(minFound);

            }
            else{
                if(checkAcceptance(energyDiff,i)){
                   candidate.setStudentProjectAllocations(testCandidate.getStudentProjectAllocations());
                    numWorsening++ ;
                    worseningAmount+=energyDiff;
                    stalenessFactor=15;

                }
                else{



                }
                
            }
            



        }
        candidate.toEnergyString();
        System.out.println("num Improvements"+numImprovements +"\nnum Worsening: " +numWorsening );
        System.out.println("average improvement"+ improvementAmount/numImprovements +"\naverage Worsening: " +worseningAmount/numWorsening );
        System.out.println(analysisString );

    }
    
    public boolean checkAcceptance(double energyDiff,int epochNum){
        double random=Math.random();
        double e=2.71828;
        double boltzman=1-(1/(Math.pow(e, (energyDiff/temperature))));
        if(epochNum%100000==0){
           // System.out.println("energy diff "+energyDiff+" boltzman="+boltzman +" staleness= " +stalenessFactor +" temperature= " + temperature);
        }
        return ((random<boltzman)?(true):(false));

    }

    public void temperatureIncrement(){
{
temperature=  (int) Math.pow(3, stalenessFactor);
}
    }
    public void calculateStaleness(double energyDiff){
        if(energyDiff==0 && stalenessFactor>=1.5){
        System.out.println("incrementing staleness");
       stalenessFactor-=.5;
        }
        if(energyDiff>0 && stalenessFactor<15){
        
            stalenessFactor+=.5;
             }
    }

}
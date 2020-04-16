package LoadStore;

import java.util.Random;
import java.util.Scanner;

import LoadStore.CandidateFunction.CandidateSolution;

public class HillClimbing {
    public int temperature=100;
    public CandidateSolution candidate;
    HillClimbing(CandidateSolution candidate){
        this.candidate=candidate;
    }
    public void startHillClimbing(){
     

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter number of epochs");
        int numberOfEpochs=Integer.parseInt(myObj.nextLine());
        double currentEnergy=candidate.checkEnergy();
        System.out.println("Starting energy of solution:"+currentEnergy);
        for(int i=0;i<numberOfEpochs;i++){
            CandidateSolution testCandidate=new CandidateSolution();
            testCandidate.setStudentProjectAllocations(candidate.getStudentProjectAllocations());
            testCandidate.randomChange();

            //System.out.println("testEnergy= "+testCandidate.checkEnergy()+"      ,   "+"initialEnergy= "+candidate.checkEnergy() );
            double energyDiff=testCandidate.checkEnergy()-candidate.checkEnergy();
           // System.out.println(energyDiff );

            

            System.out.println("testEnergy= "+testCandidate.checkEnergy()+"      ,   "+"initialEnergy= "+candidate.checkEnergy() );

            if(Double.compare(testCandidate.checkEnergy(), candidate.checkEnergy())==-1){
                candidate.setStudentProjectAllocations(testCandidate.getStudentProjectAllocations());
                System.out.println("Accepting better Solution with energyDiff:"+energyDiff);

            }
            else{
                if(checkAcceptance(energyDiff)){
                   candidate.setStudentProjectAllocations(testCandidate.getStudentProjectAllocations());
                    System.out.println("Accepting worse Solution with energyDiff:"+energyDiff);
                    temperature=temperature-(temperature/numberOfEpochs);
                }    
                else{

                    System.out.println("Declining worse Solution with energyDiff:"+energyDiff);

                }
            }
            



        }
        candidate.toEnergyString();


    }
    public boolean checkAcceptance(double energyDiff){
        double random=Math.random();
        double e=2.71828;
        double boltzman=(1/(Math.pow(e, (energyDiff/temperature))));
        System.out.println("boltzman:" +boltzman +"\nrandom: "+random);
        return ((random<boltzman)?(true):(false));

    }

}
package LoadStore;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import LoadStore.CandidateFunction.CandidateSet;
import LoadStore.CandidateFunction.CandidateSolution;

public class geneticAlgorithem {
    CandidateSet candidateSet;
    geneticAlgorithem(CandidateSet candidateSet)
    {
    this.candidateSet=candidateSet;
    }
    public void mateCandidateMutation()
{
	Random rand=new Random();
	for(int i=0;i<100;i+=2)
	{
	int cutOne=rand.nextInt(500);
	int cutTwo=rand.nextInt(500-cutOne) +cutOne;

	CandidateSolution firstCandidate=candidateSet.get(i);
	CandidateSolution secondCandidate=candidateSet.get(i+1);
	firstCandidate.randomChange(5);
	secondCandidate.randomChange(5);
	swapSections(firstCandidate,secondCandidate,cutOne,cutTwo);

	}

}
public void swapSections(CandidateSolution candidate1,CandidateSolution candidate2,int cut1,int cut2){
    CandidateSolution child1=new CandidateSolution();
    CandidateSolution child2=new CandidateSolution();
    child1.setStudentProjectAllocations(candidate1.getStudentProjectAllocations());
    child2.setStudentProjectAllocations(candidate2.getStudentProjectAllocations());
    for(int index=cut1;index<cut2;index++)
    {
        child1.getStudentProjectAllocations().set(index, candidate2.getStudentProjectAllocations().get(index));
        child2.getStudentProjectAllocations().set(index, candidate1.getStudentProjectAllocations().get(index));
    }
    candidateSet.add(child1);
    candidateSet.add(child2);


}
public void mateCandidateNormal()
	{
		Random rand=new Random();
		for(int i=0;i<100;i+=2)
		{
		int cutOne=rand.nextInt(500);
		int cutTwo=rand.nextInt(500-cutOne) +cutOne;
		CandidateSolution firstCandidate=candidateSet.get(i);
		CandidateSolution secondCandidate=candidateSet.get(i+1);
		swapSections(firstCandidate,secondCandidate,cutOne,cutTwo);

		}
    }
    public void mateCandidates(){
		int numEpochs=getNumberOfIterations();
		candidateSet.outputTop10();
        for(int i=0;i<numEpochs;i++){
		if(checkForMutation())
		{
			mateCandidateMutation();
		}
		else{
			mateCandidateNormal();			
		}
        candidateSet.sort();
        candidateSet.removeBottom100();
        if(i%1000==0) candidateSet.outputTop10();
    }    
    }
    public boolean checkForMutation(){
		
		double rand=Math.random();
		if(Double.compare(rand, .95)==1){
			return true;
		}
		else return false;
	}
    public int getNumberOfIterations(){

          Scanner myObj = new Scanner(System.in);
		System.out.println("Enter number of epochs");
		int numberOfEpochs=Integer.parseInt(myObj.nextLine());
		return numberOfEpochs;
    }
}
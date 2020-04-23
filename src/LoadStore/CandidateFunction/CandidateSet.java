package LoadStore.CandidateFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import LoadStore.DataHandling;
import LoadStore.Student;

public class CandidateSet {
    ArrayList<CandidateSolution> candidateSet=new ArrayList<>();
    
    public CandidateSet(List<Student> students){
    	for(int i=0;i<1000;i++){
    		CandidateSolution candidateSolution=new CandidateSolution(students);
    		candidateSet.add(candidateSolution);
    	}
    }
    
    public CandidateSet(ArrayList<CandidateSolution> candidateSet)
    {
    this.candidateSet=candidateSet;
    }


	public List<CandidateSolution> findBestCandidates()
	{
	    List<CandidateSolution> top100=new ArrayList<>();
	    Collections.sort(candidateSet);
	    for(int i=0;i<100;i++){
	    	top100.add(candidateSet.get(i));
	    	System.out.println("energy:"+candidateSet.get(i).getEnergyValue());
	    }
	    return top100;
	}
	public void mateCandidates(){
		Collections.sort(candidateSet);
		if(checkForMutation())
		{
			mateCandidateMutation();
		}
		else{
			mateCandidateNormal();			
		}
		Collections.sort(candidateSet);
		removeBottom100();
		outputTop10();	    
	}
	public boolean checkForMutation(){
		
		double rand=Math.random();
		if(Double.compare(rand, .95)==1){
			return true;
		}
		else return false;
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
	public void removeBottom100()
	{
		for(int i=0;i<100;i++)
		{
		candidateSet.remove(candidateSet.size()-i);
		}
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
public void outputTop10(){
	Collections.sort(candidateSet);
	for(int index=0;index<10;index++){
		candidateSet.get(index).toEnergyString();
	}
}

}
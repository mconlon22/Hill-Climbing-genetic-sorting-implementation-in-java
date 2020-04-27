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
	    }
	    return top100;
	}
	
	
	
	
	public void removeBottom100()
	{
		for(int i=100;i>0;i--)
		{
		candidateSet.remove(candidateSet.size()-i);
		}
	}

public void outputTop10(){
	sort();
	String topString="";
	for(int index=0;index<10;index++){
		topString+="\n#"+index+"  energy of candidate:"+candidateSet.get(index).getEnergyValue();
	}
	System.out.println(topString);
}


	public void add(CandidateSolution candidate){
		candidateSet.add(candidate);

	}
	public void sort(){
		Collections.sort(this.candidateSet);
	}
	public CandidateSolution get(int i){
		return candidateSet.get(i);
	}
	public void candidateSetTest()
	{
		int numCandidates=candidateSet.size();

		System.out.println("size:" +numCandidates);
	
	}

}
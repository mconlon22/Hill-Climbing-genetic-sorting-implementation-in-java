package LoadStore.CandidateFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import LoadStore.DataHandling;
import LoadStore.Student;

public class CandidateSet {
    ArrayList<CandidateSolution> candidateSet=new ArrayList<>();
    
    public CandidateSet(List<Student> students){
    	for(int i=0;i<20;i++){
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
	    List<CandidateSolution> top4=new ArrayList<>();
	    Collections.sort(candidateSet);
	    for(int i=0;i<4;i++){
	    	top4.add(candidateSet.get(i));
	    	System.out.println("energy:"+candidateSet.get(i).getEnergyValue());
	    }
	    return top4;
	}
	public void mateCandidates(){
	    List<CandidateSolution> bestCandidates=findBestCandidates();
	    
	}
}
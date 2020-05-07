package LoadStore;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JLabel;

import LoadStore.CandidateFunction.CandidateSet;
import LoadStore.CandidateFunction.CandidateSolution;

public class geneticAlgorithem {
	CandidateSet candidateSet;
	int numProjects;
	public geneticAlgorithem(CandidateSet candidateSet,int numProjects) {
		this.candidateSet = candidateSet;
		this.numProjects=numProjects;
	}

	private void mateCandidateMutation(List <CandidateSolution> matingPool) {
		Random rand = new Random();
		for (int i = 0; i < 100; i += 2) {
			int cutOne = rand.nextInt(numProjects);
			int cutTwo = rand.nextInt(numProjects - cutOne) + cutOne;

			CandidateSolution firstCandidate = matingPool.remove(rand.nextInt(matingPool.size()));
			CandidateSolution secondCandidate =matingPool.remove(rand.nextInt(matingPool.size()));
			firstCandidate.randomChange(4);
			secondCandidate.randomChange(4);
			swapSections(firstCandidate, secondCandidate, cutOne, cutTwo);

		}

	}

	private void swapSections(CandidateSolution candidate1, CandidateSolution candidate2, int cut1, int cut2) {
		CandidateSolution child1 = new CandidateSolution();
		CandidateSolution child2 = new CandidateSolution();
		child1.setStudentProjectAllocations(candidate1.getStudentProjectAllocations());
		child2.setStudentProjectAllocations(candidate2.getStudentProjectAllocations());
		for (int index = cut1; index < cut2; index++) {
			child1.getStudentProjectAllocations().set(index, candidate2.getStudentProjectAllocations().get(index));
			child2.getStudentProjectAllocations().set(index, candidate1.getStudentProjectAllocations().get(index));
		}
		candidateSet.add(child1);
		candidateSet.add(child2);

	}

	private void mateCandidateNormal(List<CandidateSolution> matingPool) {
		Random rand = new Random();
		for (int i = 0; i < 100; i += 2) {
			int cutOne = rand.nextInt(numProjects);
			int cutTwo = rand.nextInt(numProjects - cutOne) + cutOne;
			CandidateSolution firstCandidate = matingPool.remove(rand.nextInt(matingPool.size()));
			CandidateSolution secondCandidate =matingPool.remove(rand.nextInt(matingPool.size()));
			swapSections(firstCandidate, secondCandidate, cutOne, cutTwo);

		}
	}

	
	
	
	public void mateCandidates(){
		int numEpochs=1000000;
		List<CandidateSolution> matingPool=new LinkedList();
		candidateSet.outputTop10();
        for(int i=0;i<numEpochs;i++){
		matingPool=candidateSet.findBestCandidates();
		if(checkForMutation())
		{
			mateCandidateMutation(matingPool);
		}
		else{
			mateCandidateNormal(matingPool);			
		}
        candidateSet.sort();
		candidateSet.removeBottom100();
		matingPool.clear();
        if(i%1000==0) candidateSet.outputTop10();
    }    
    }
    private boolean checkForMutation(){
		
		double rand=Math.random();
		if(Double.compare(rand, .9)==1){
			return true;
		}
		else return false;
	}
    private int getNumberOfIterations(){

          Scanner myObj = new Scanner(System.in);
		System.out.println("Enter number of epochs");
		int numberOfEpochs=Integer.parseInt(myObj.nextLine());
		return numberOfEpochs;
	}
	public CandidateSolution getBeSolution(){
		return candidateSet.get(0);
	}
}
package LoadStore;

import java.io.IOException;
import java.util.List;

import LoadStore.CandidateFunction.CandidateSet;
import LoadStore.CandidateFunction.CandidateSolution;

public class main {
	public static void main(String[] argv) throws IOException {
	 DataHandling data=new DataHandling();
	 data.load();
		CandidateSet candidateSet=new CandidateSet(data.students);
		while(true){
		candidateSet.mateCandidates();
		}
		
		
	}
	

}

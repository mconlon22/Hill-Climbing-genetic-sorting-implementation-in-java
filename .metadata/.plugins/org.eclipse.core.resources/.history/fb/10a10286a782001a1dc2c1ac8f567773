package LoadStore;

import java.io.IOException;
import java.util.List;

import LoadStore.CandidateFunction.CandidateSolution;

public class main {
	public static void main(String[] argv) throws IOException {
	 DataHandling data=new DataHandling();
	 data.load();
	 CandidateSolution candidate=new CandidateSolution(data.students);
	 
		HillClimbing hillClimbing=new HillClimbing(candidate);
		hillClimbing.startHillClimbing();
	}
	

}

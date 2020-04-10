package LoadStore;

import java.io.IOException;
import java.util.List;

import LoadStore.CandidateFuncation.CandidateSet;

public class main {
	public static void main(String[] argv) throws IOException {
	 DataHandling data=new DataHandling();
data.load();
System.out.println(data.getStudents().size());
CandidateSet candidate=new CandidateSet(data.getStudents());
candidate.findBestCandidates();
	}
	

}

package LoadStore;

import java.io.IOException;
import java.util.List;

public class main {
	public static void main(String[] argv) throws IOException {
	 DataHandling data=new DataHandling();
	 CandidateGenerator generator=new CandidateGenerator();
	 data.load();
	 List candidates = generator.generateCandidates(data.getStudents());
	 data.candidates=candidates;
	 data.save();
	}

}

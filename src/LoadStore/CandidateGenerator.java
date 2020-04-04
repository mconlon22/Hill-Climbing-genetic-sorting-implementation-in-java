package LoadStore;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CandidateGenerator {
 Random rand=new Random();

	public List<CandidateSolution> generateCandidates(List<Student> students)
	{
		List<CandidateSolution> candidates= new LinkedList<>(); 
		for(Student student : students) {
		    int random = rand.nextInt(10);
		Project randomProject=student.getProjectPreferences().get(random);
		CandidateSolution candidate=new CandidateSolution(student,randomProject);
		candidates.add(candidate);
		}
		return candidates;
	}
	
}
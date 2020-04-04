package LoadStore;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class CandidateSolution {
	int candidateId;
	Student student;
	Project project;
	
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

	CandidateSolution(Student student,Project project)
	{
		this.student=student;
		this.project=project;
        this.candidateId=ID_GENERATOR.getAndIncrement();

	}
	
	CandidateSolution(int id,Student student,Project project)
	{
		this.student=student;
		this.project=project;
		this.candidateId=id;

	}
	public String toCsvString()
	{
		return candidateId+","+student.getId()+"," +project.getId()+"\n";
	}

}

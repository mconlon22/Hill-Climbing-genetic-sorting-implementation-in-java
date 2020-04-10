package LoadStore.CandidateFuncation;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import LoadStore.Project;
import LoadStore.Student;


public class StudentProjectAllocation {
	int candidateId;
	Student student;
	public Project project;

	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

	StudentProjectAllocation(Student student, Project project) {
		this.student = student;
		this.project = project;
		this.candidateId = ID_GENERATOR.getAndIncrement();

	}

	public StudentProjectAllocation(int id, Student student, Project project)
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

package LoadStore.CandidateFunction;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import LoadStore.Project;
import LoadStore.Student;


public class StudentProjectAllocation {
	int candidateId;
	private Student student;
	private Project project;
	Random rand=new Random();
	

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

	public int getCandidateId() {
		return candidateId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	public void changeProjectAllocation(){
		this.project=student.getProjectPreferences().get(rand.nextInt(10));
	}

	
}

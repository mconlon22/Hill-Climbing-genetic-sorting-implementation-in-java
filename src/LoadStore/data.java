package LoadStore;

import java.util.List;

public interface data {
	int numStudents();
	int numProjects();
	int numStaff();
	void load();
	void save();
	List<Student> getStudents();
	List<Project> getProjects();
	List<StaffMember> getStaff();

	
	


}

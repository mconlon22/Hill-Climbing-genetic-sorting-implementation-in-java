package LoadStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import LoadStore.CandidateFunction.CandidateSolution;
import LoadStore.CandidateFunction.StudentProjectAllocation;

public class DataHandling implements data {

	List<Student> students=new ArrayList<Student>();
	public List<StudentProjectAllocation> candidates=new ArrayList<StudentProjectAllocation>();

	List<Project> projects=new ArrayList<Project>();
	List<StaffMember> staffMembers=new ArrayList<StaffMember>();
	String projectPath="src/LoadStore/csvdata/Projects.csv";
	String studentsPath="src/LoadStore/csvdata/Students.csv";
	String staffPath="src/LoadStore/csvdata/StaffMembers.csv";
	String candidatePath="src/LoadStore/csvdata/Candidates.csv";
	
	/*
	Storing Students to csv
	*/

	private  void storeStudents()  {
		File file=new File(studentsPath);
		FileWriter studentCsvWriter=null;
		try {
			studentCsvWriter = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Student student : students) {
			
			
			try {
				studentCsvWriter.append(student.toCsvString());
				studentCsvWriter.flush(); 


			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			studentCsvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void testLoadStore() throws IOException {
		load();
		save();
	}
	
	public void testLoadSave() throws IOException
	{

		  StaffMember staffMember=new StaffMember(Integer.parseInt("10"),"hi","activity", "CS");
		    StaffMember staffMember1=new StaffMember(Integer.parseInt("11"),"gsdg","sgds", "CS");
		    StaffMember staffMember2=new StaffMember(Integer.parseInt("13"),"vng","sasd", "CS");
		    staffMembers.add(staffMember1); 
		    staffMembers.add(staffMember2);

		    staffMembers.add(staffMember); 

		    storeStaffMembers();
		    
		    Project project=new Project("hello",staffMember,ProjectArea.valueOf("CS"));
		    Project project1=new Project("hello",staffMember1,ProjectArea.valueOf("CS"));

		    projects.add(project);
		    projects.add(project1);
		    storeProjects();
		    List prefrences=new LinkedList();
		    prefrences.add(project);
		    Student student=new Student("James",10,SpecialFocus.CS,prefrences);
		    students.add(student);
		    storeStudents();
		    
		    loadStaffMembers();
		    
	}
	/*
	Storing Projects to csv
	*/
	private void storeProjects() throws IOException  {
		File file=new File(projectPath);
		FileWriter projectsCsvWriter=null;
		try {
			projectsCsvWriter = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Project project : projects) {

			try {
				projectsCsvWriter.append(project.toCsvString());

				projectsCsvWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		projectsCsvWriter.close(); 
		
	}
	/*
	Storing Staff to csv
	*/
	private void storeStaffMembers() {
		System.out.println("saving");
		FileWriter staffMembersCsvWriter=null;
		try {
			File file=new File(staffPath);

			staffMembersCsvWriter = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < staffMembers.size(); i++) {
			
			try {
				staffMembersCsvWriter.append(staffMembers.get(i).toCsvString());
				staffMembersCsvWriter.flush();


			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/*
	Loading Staff from csv
	*/
	private void loadStaffMembers() throws IOException {
		File file=new File(staffPath);

		BufferedReader staffCsvReader = new BufferedReader(new FileReader(file));
		String row;
		while ((row = staffCsvReader.readLine()) != null) {
		    String[] data = row.split(",");
		    StaffMember staffMember=new StaffMember(Integer.parseInt(data[0]),data[1],data[2],data[3]);
		    staffMembers.add(staffMember);
		    staffMember.toString();
		}
		staffCsvReader.close();
	}
	/*
	Loading Projects from csv
	*/
private void loadProjects() throws IOException {
	File file=new File(projectPath);

		BufferedReader projectCsvReader = new BufferedReader(new FileReader(file));
		String row; 
		while ((row = projectCsvReader.readLine()) != null) {
		    String[] data = row.split(",");
		    int projectId=Integer.parseInt(data[0]);
		    String projectTitle=data[1];
		    StaffMember supervisorId = findStaffMember(Integer.parseInt(data[2]));
		    String projectArea = data[3];
		    Project project=new Project(projectId,projectTitle,supervisorId,projectArea);
		    projects.add(project);
		}
		projectCsvReader.close();
	}
	/*
	Loading Students from csv
	*/
private void loadStudents() throws IOException {
	File file=new File(studentsPath);

	BufferedReader studentCsvReader = new BufferedReader(new FileReader(file));
	String row;
	while ((row = studentCsvReader.readLine()) != null) {
	    String[] data = row.split(",");
	    List<Project> projectPreferences = new LinkedList<>();

	    for(int i=3;i<data.length;i++) 
	    {
	    	projectPreferences.add(findProject(Integer.parseInt(data[i])));
	    }
	    Student student=new Student(data[0],Integer.parseInt(data[1]),SpecialFocus.valueOf(data[2]),projectPreferences);
	    students.add(student);
	}
	studentCsvReader.close();
}
	/*
Helper functions to find staff member ect with a specific id
	*/
private StaffMember findStaffMember(int id) {
	 for (int i = 0; i < staffMembers.size(); i++) {
        if(staffMembers.get(i).getId()==id) {
        	return staffMembers.get(i);
        }
     }
	return null;
}

private Project findProject(int id) {
	 for (int i = 0; i < projects.size(); i++) {
       if(projects.get(i).getId()==id) {
       	return projects.get(i);
       }
    }
	return null;
}
private Student findStudent(int id) {
	 for (Student student:students) {
      if(student.getId()==id) {
      	return student;
      	}
   }
	return null;
}
	/*
Loads in all data
	*/
public void load() {
	try {
		loadStaffMembers();
		loadProjects();
		loadStudents();
		loadCandidates();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
}
	/*
	saves all data to csv 
	*/
public void save() {

	try {
		storeStaffMembers();
		storeProjects();
		storeStudents();
		
	} catch (IOException e) {
		e.printStackTrace();
	}

	
}
@Override
public int numStudents() {
	return students.size();
}
@Override
public int numProjects() {
	return projects.size();
}
@Override
public int numStaff() {
	return staffMembers.size();
}
@Override
public List<Student> getStudents() {
	return students;
}
@Override
public List<Project> getProjects() {
	return projects;
}
@Override
public List<StaffMember> getStaff() {
	return staffMembers;
}
/*
Save candidates function 
*/
public void saveCandidate(CandidateSolution candidateSolution) throws IOException
{
	String fileSeparator = System.getProperty("file.separator");
	String relativePath="";
     for (int i=0;i<10;i++){
		relativePath = "tmp"+fileSeparator+"file"+i+".txt";
		File file = new File(relativePath);
        if(file.createNewFile()){
			System.out.println(relativePath+" File Created in Project root directory");
			break;
        }else System.out.println("File "+relativePath+" already exists in the project root directory");
	 }
	FileWriter candidateCsvWriter= new FileWriter(relativePath);

	for(StudentProjectAllocation candidate: candidateSolution.getStudentProjectAllocations()) {

		try {
			candidateCsvWriter.append(candidate.toCsvString());

			candidateCsvWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	candidateCsvWriter.close(); 
		
	}
	/*
	Load candidates function
	*/

private void loadCandidates() throws IOException {
	File file=new File(candidatePath);

	BufferedReader candidateCsvReader = new BufferedReader(new FileReader(file));
	String row;
	while ((row = candidateCsvReader.readLine()) != null) {
	    String[] data = row.split(",");

	    LoadStore.CandidateFunction.StudentProjectAllocation candidate = new StudentProjectAllocation(
				Integer.parseInt(data[0]), findStudent(Integer.parseInt(data[1])),
				findProject(Integer.parseInt(data[2])));
	    candidates.add(candidate);
	}
	candidateCsvReader.close();
}
	

}



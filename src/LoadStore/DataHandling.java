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

public class DataHandling implements data {

	List<Student> students=new ArrayList<Student>();
	public List<CandidateSolution> candidates=new ArrayList<CandidateSolution>();

	List<Project> projects=new ArrayList<Project>();
	List<StaffMember> staffMembers=new ArrayList<StaffMember>();
	String projectPath="C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\LoadStore\\Projects.csv";
	String studentsPath="C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\LoadStore\\Students.csv";
	String staffPath="C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\LoadStore\\StaffMembers.csv";
	String candidatePath="C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\LoadStore\\Candidates.csv";
	
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
				System.out.println("Store student String:"+student.toString());


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
		System.out.println("saving");

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
		    for (int i=0; i<staffMembers.size(); i++) 
		    { 
		       System.out.println(staffMembers.get(i).toCsvString());
		    }
		    loadProjects();
		    for (int i=0; i<projects.size(); i++) 
		    { 
		       System.out.println(projects.get(i).toCsvString());
		    }
		    loadStudents();
		    for (int i=0; i<students.size(); i++) 
		    { 
		       System.out.println(students.get(i).toCsvString());
		    }
	}
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
			System.out.println(project.toCsvString());

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
				System.out.println(staffMembers.get(i).toCsvString());
				staffMembersCsvWriter.append(staffMembers.get(i).toCsvString());
				staffMembersCsvWriter.flush();


			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
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
	    System.out.println("Load Student String:"+student.toString());
	}
	studentCsvReader.close();
}
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
public void load() {
	try {
		loadStaffMembers();
		loadProjects();
		loadStudents();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
public void save() {

	try {
		storeStaffMembers();
		storeProjects();
		storeStudents();
		saveCandidates();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
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
	// TODO Auto-generated method stub
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
	// TODO Auto-generated method stub
	return staffMembers;
}

public void saveCandidates() throws IOException
{
	File file=new File(candidatePath);
	FileWriter candidateCsvWriter= new FileWriter(file);
;

	for(CandidateSolution candidate: candidates) {
		System.out.println(candidate.toCsvString());

		try {
			candidateCsvWriter.append(candidate.toCsvString());

			candidateCsvWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	candidateCsvWriter.close(); 
		
	}
private void loadCandidates() throws IOException {
	File file=new File(candidatePath);

	BufferedReader candidateCsvReader = new BufferedReader(new FileReader(file));
	String row;
	while ((row = candidateCsvReader.readLine()) != null) {
	    String[] data = row.split(",");

	    CandidateSolution candidate=new CandidateSolution(Integer.parseInt(data[0]),findStudent(Integer.parseInt(data[1])),findProject(Integer.parseInt(data[2])));
	    candidates.add(candidate);
	}
	candidateCsvReader.close();
}
	

}



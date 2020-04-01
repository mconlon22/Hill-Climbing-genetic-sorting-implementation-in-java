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

public class DataHandling {

	List<Student> students=new ArrayList<Student>();
	List<Project> projects=new ArrayList<Project>();
	List<StaffMember> staffMembers=new ArrayList<StaffMember>();
	
	public  void storeStudents()  {
		File file=new File("/Users/martinconlon/git/SoftWare-Engineering/src/LoadStore/Students.txt");
		FileWriter studentCsvWriter=null;
		try {
			studentCsvWriter = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < students.size(); i++) {
			
			
			try {
				studentCsvWriter.append(students.get(i).toCsvString());
				studentCsvWriter.flush(); 

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			studentCsvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void testLoadStore() {
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void addMembers() throws IOException
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
	public void storeProjects() throws IOException  {
		File file=new File("/Users/martinconlon/git/SoftWare-Engineering/src/LoadStore/Projects.txt");
		FileWriter projectsCsvWriter=null;
		try {
			projectsCsvWriter = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < projects.size(); i++) {
			System.out.println(projects.get(i).toCsvString());

			try {
				projectsCsvWriter.append(projects.get(i).toCsvString());

				projectsCsvWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		projectsCsvWriter.close(); 
		
	}
	public void storeStaffMembers() {
		System.out.println("saving");
		FileWriter staffMembersCsvWriter=null;
		try {
			File file=new File("/Users/martinconlon/git/SoftWare-Engineering/src/LoadStore/StaffMembers.txt");

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
	public void loadStaffMembers() throws IOException {
		File file=new File("/Users/martinconlon/git/SoftWare-Engineering/src/LoadStore/StaffMembers.txt");

		BufferedReader staffCsvReader = new BufferedReader(new FileReader(file));
		String row;
		while ((row = staffCsvReader.readLine()) != null) {
		    String[] data = row.split(",");
		    System.out.println(row);
		    StaffMember staffMember=new StaffMember(Integer.parseInt(data[0]),data[1],data[2],data[3]);
		    staffMembers.add(staffMember);
		}
		staffCsvReader.close();
	}
public void loadProjects() throws IOException {
	File file=new File("/Users/martinconlon/git/SoftWare-Engineering/src/LoadStore/Projects.txt");

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
public void loadStudents() throws IOException {
	File file=new File("/Users/martinconlon/git/SoftWare-Engineering/src/LoadStore/Students.txt");

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
public StaffMember findStaffMember(int id) {
	 for (int i = 0; i < staffMembers.size(); i++) {
        if(staffMembers.get(i).getId()==id) {
        	return staffMembers.get(i);
        }
     }
	return null;
}
public Project findProject(int id) {
	 for (int i = 0; i < projects.size(); i++) {
       if(projects.get(i).getId()==id) {
       	return projects.get(i);
       }
    }
	return null;
}
public void load() throws IOException {
	loadStaffMembers();
	loadProjects();
	loadStudents();
	
}
public void save() throws IOException {
	storeStaffMembers();
	storeProjects();
	storeStudents();
	
}

}



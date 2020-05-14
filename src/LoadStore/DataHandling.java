package LoadStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import LoadStore.CandidateFunction.CandidateSolution;
import LoadStore.CandidateFunction.StudentProjectAllocation;

public class DataHandling implements data {

	List<Student> students = new ArrayList<Student>();
	public List<StudentProjectAllocation> candidates = new ArrayList<StudentProjectAllocation>();

	List<Project> projects = new ArrayList<Project>();
	List<StaffMember> staffMembers = new ArrayList<StaffMember>();
	String projectPath = "C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\LoadStore\\csvdata\\Projects.csv";
	String studentsPath = "C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\LoadStore\\csvdata\\Students.csv";
	String staffPath = "C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\LoadStore\\csvdata\\StaffMembers.csv";
	String candidatePath = "C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\LoadStore\\csvdata\\Candidates.csv";

	/*
	 * Storing Students to csv
	 */

	private void storeStudents() {
		File file = new File(studentsPath);
		FileWriter studentCsvWriter = null;
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

	public void testLoadSave() throws IOException {

		StaffMember staffMember = new StaffMember(Integer.parseInt("10"), "hi", "activity", "CS");
		StaffMember staffMember1 = new StaffMember(Integer.parseInt("11"), "gsdg", "sgds", "CS");
		StaffMember staffMember2 = new StaffMember(Integer.parseInt("13"), "vng", "sasd", "CS");
		staffMembers.add(staffMember1);
		staffMembers.add(staffMember2);

		staffMembers.add(staffMember);

		storeStaffMembers();

		Project project = new Project("hello", staffMember, ProjectArea.valueOf("CS"));
		Project project1 = new Project("hello", staffMember1, ProjectArea.valueOf("CS"));

		projects.add(project);
		projects.add(project1);
		storeProjects();
		List prefrences = new LinkedList();
		prefrences.add(project);
		Student student = new Student("James", 10, SpecialFocus.CS, prefrences);
		students.add(student);
		storeStudents();

		loadStaffMembers();

	}

	/*
	 * Storing Projects to csv
	 */
	private void storeProjects() throws IOException {
		File file = new File(projectPath);
		FileWriter projectsCsvWriter = null;
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
	 * Storing Staff to csv
	 */
	private void storeStaffMembers() {
		System.out.println("saving");
		FileWriter staffMembersCsvWriter = null;
		try {
			File file = new File(staffPath);

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
	 * Loading Projects from csv
	 */
	private void loadProjects() throws IOException {
		File file = new File(projectPath);

		BufferedReader projectCsvReader = new BufferedReader(new FileReader(file));
		String row;
		while ((row = projectCsvReader.readLine()) != null) {
			String[] data = row.split(",");
			int projectId = Integer.parseInt(data[0]);
			String projectTitle = data[1];
			StaffMember supervisorId = findStaffMember(Integer.parseInt(data[2]));
			String projectArea = data[3];
			Project project = new Project(projectId, projectTitle, supervisorId, projectArea);
			projects.add(project);
		}
		projectCsvReader.close();
	}

	public List loadProjects(File projectFile)  {
	

		BufferedReader projectCsvReader;
		try {
			projectCsvReader = new BufferedReader(new FileReader(projectFile));
			String row;
			while ((row = projectCsvReader.readLine()) != null) {
				String[] data = row.split(",");
				int projectId = Integer.parseInt(data[0]);
				String projectTitle = data[1];
				StaffMember supervisorId = findStaffMember(Integer.parseInt(data[2]));
				String projectArea = data[3];
				Project project = new Project(projectId, projectTitle, supervisorId, projectArea);
				projects.add(project);
			}
			projectCsvReader.close();
		} 
		 
		catch (IOException e) {
			e.printStackTrace();

			return null;
		}
		return projects;
	}

	/*
	 * Loading Staff from csv
	 */
	public List<StaffMember> loadStaffMembers(File staffFile) {
		
		try {
			BufferedReader staffCsvReader = new BufferedReader(new FileReader(staffFile));
		String row;
		while ((row = staffCsvReader.readLine()) != null) {
			String[] data = row.split(",");
			StaffMember staffMember = new StaffMember(Integer.parseInt(data[0]), data[1], data[2], data[3]);
			staffMembers.add(staffMember);
			staffMember.toString();
			
		}
		staffCsvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;

		} catch (IOException e) {
			e.printStackTrace();
			return null;

		} 
		return staffMembers;
	}
	/*
	 * Loading Students from csv
	 */
	private void loadStudents() throws IOException {
		File file = new File(studentsPath);

		BufferedReader studentCsvReader = new BufferedReader(new FileReader(file));
		String row;
		while ((row = studentCsvReader.readLine()) != null) {
			String[] data = row.split(",");
			List<Project> projectPreferences = new LinkedList<>();

			for (int i = 3; i < data.length; i++) {
				projectPreferences.add(findProject(Integer.parseInt(data[i])));
			}
			Student student = new Student(data[0], Integer.parseInt(data[1]), SpecialFocus.valueOf(data[2]),
					projectPreferences);
			students.add(student);
		}
		studentCsvReader.close();
	}

	public List<Student> loadStudents(File studentsFile) {
		BufferedReader studentCsvReader;
		System.out.println("File:"+studentsFile.toString());
		try {
			studentCsvReader = new BufferedReader(new FileReader(studentsFile)); 
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
 catch (IOException e) 
 {
	e.printStackTrace();
	return null;
}
	
	return students;
}
	public void saveCandidateSolution(CandidateSolution candidate,String path) 
	{
		
		
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
public void loadStaffMembers() throws IOException {
	File file = new File(staffPath);

	BufferedReader staffCsvReader = new BufferedReader(new FileReader(file));
	String row;
	while ((row = staffCsvReader.readLine()) != null) {
		String[] data = row.split(",");
		StaffMember staffMember = new StaffMember(Integer.parseInt(data[0]), data[1], data[2], data[3]);
		staffMembers.add(staffMember);
		staffMember.toString();
	}
	staffCsvReader.close();
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
public String saveCandidate(CandidateSolution candidateSolution) throws IOException
{
	File file = null;
	String fileSeparator = System.getProperty("file.separator");
	String relativePath="";
     for (int i=0;i<10;i++){
		relativePath = "C:\\Users\\marti\\git\\SoftWare-Engineering\\src\\SavedCandidates\\"+"file"+i+".csv";
		 file = new File(relativePath);
        if(file.createNewFile()){
			System.out.println(relativePath+" File Created in Project root directory");
			break;
        }
        else System.out.println("File "+relativePath+" already exists in the project root directory");
	 }
	FileWriter candidateCsvWriter= new FileWriter(relativePath);

	for(StudentProjectAllocation candidate: candidateSolution.getStudentProjectAllocations()) {

		try {
			candidateCsvWriter.append(candidate.toUsableString());

			candidateCsvWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	candidateCsvWriter.close(); 
		return file.getPath();
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

@Override
public int loadStudents(String filePath) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int loadProjects(String filePath) {
	// TODO Auto-generated method stub
	return 0;
}
public void loadCsv(File file) throws NumberFormatException, IOException 
{
	
	
		
	
	BufferedReader studentCsvReader = new BufferedReader(new FileReader(file));
	String row;
	studentCsvReader.readLine();
	while ((row = studentCsvReader.readLine()) != null) {
		String[] data = row.split(",");
		List<Project> projectPreferences = new LinkedList<>();
		proposer proposer=toProposer(data[3]);
		for (int i = 4; i < data.length; i++) {
			
			projectPreferences.add(checkForProject(data[i], proposer));
		}
		Student student = new Student(data[0], Integer.parseInt(data[1]),Double.parseDouble(data[2]) ,projectPreferences);
		
		students.add(student);
	}
	studentCsvReader.close();
	
}
public Project checkForProject(String projectName,proposer proposer)
{
	for(Project project:projects) 
	{
	
		if(project.getTitle().equals(projectName)) {
			return project;
			
		}
	}
	Project newProject=new Project(projectName,proposer);
	projects.add(newProject);
	return newProject;

}
public proposer toProposer(String pro) 
{
	if(pro=="student")
	{
		
		return proposer.student;
	}
	else return proposer.supervisor;
}
public void loadXls(File file) throws IOException 
{

	FileInputStream fis=new FileInputStream(file);  
	HSSFWorkbook wb=new HSSFWorkbook(fis);   
	HSSFSheet sheet= wb.getSheetAt(0);  
	DataFormatter formatter = new DataFormatter();
	Boolean skipFirst=true;
	
	for(Row row: sheet)     
	{  
		if(skipFirst) {
			skipFirst=false;
			continue;
		}
		int iterate=0;
		Student student=new Student();
		List<Project> projectPreferences = new LinkedList<>();

	for(Cell cell: row)    //iteration over cell using for each loop  
	{  
		proposer proposer = null;

		String val = formatter.formatCellValue(cell);

		if(val==""&&iterate==1)continue;
		if(iterate==0) student.setFullName(val);
		if(iterate==1) student.setId(Integer.parseInt(val));
		if(iterate==2) student.setGpa(Double.parseDouble(val));
		if(iterate==3)  proposer=toProposer(val);
	
		if(iterate>3&&val!="")  projectPreferences.add(checkForProject(val, proposer));
		

	

		if(val==""&&iterate==25) 
		{
			
			student.setProjectPreferences(projectPreferences);
			students.add(student);
		}
		iterate++;

		  
	
	

}}
}
public fileType getFileType(File file) 
{
	String extension = "";

	int i = file.getPath().lastIndexOf('.');
 extension = file.getPath().substring(i+1);
	System.out.println(extension);

	
	if(extension.compareTo("xls")==0) {
		System.out.println("xls");
		return LoadStore.fileType.xls;
	}
	if(extension.compareTo("csv")==0) {
		return LoadStore.fileType.csv;
	}
if(extension.compareTo("tsv")==0) {
		return LoadStore.fileType.tsv;
	}
return null;
}
public void readFile(File file) throws NumberFormatException, IOException
{
	if(getFileType(file)==fileType.csv) loadCsv(file);
	if(getFileType(file)==fileType.xls) loadXls(file);

}

}

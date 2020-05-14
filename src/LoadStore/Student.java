package LoadStore;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Student {

    private String fullName;
    private int id;
    private SpecialFocus specialFocus;
    private List<Project> projectPreferences = new LinkedList<>();
    private double gpa;

    public Student(String fullName, int id, SpecialFocus specialFocus, List<Project> preferences) {
        this.fullName = fullName; 
        this.id = id;
        this.specialFocus = specialFocus;
        this.projectPreferences = preferences;
        this.gpa =0;
    }
    public Student(String fullName, int id,double GPA, List<Project> preferences) {
        this.fullName = fullName; 
        this.id = id;
        this.projectPreferences = preferences;
        this.gpa =GPA;
    }
    public Student() {
      
    }
    
    

    public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getGpa() {
		return gpa;
	}


	public void setGpa(double gpa) {
		this.gpa = gpa;
	}


	public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public SpecialFocus getSpecialFocus() {
        return specialFocus;
    }

    public List<Project> getProjectPreferences() {
        return projectPreferences;
    }

    public void setProjectPreferences(List<Project> projectPreferences) {
        this.projectPreferences = projectPreferences;
    }
    public String toCsvString() {
    	String preferenceString="";
    	for (int i = 0; i < projectPreferences.size(); i++) {
    		preferenceString+=projectPreferences.get(i).getId()+",";
    	}
    	return fullName+","+id+","+preferenceString+"\n";
    }
    public String toString() {
    	String preferenceString="";
    	for (int i = 0; i < projectPreferences.size(); i++) {
    		preferenceString+=projectPreferences.get(i).getTitle()+",";
    	}
    	
    	return fullName+","+id+","+preferenceString+"\n";
    }
}
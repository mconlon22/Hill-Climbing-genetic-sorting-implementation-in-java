package LoadStore;

import java.util.concurrent.atomic.AtomicInteger;

public class Project {
	private int id;
	private String title;
    private StaffMember supervisor;
    private ProjectArea projectArea;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSupervisor(StaffMember supervisor) {
		this.supervisor = supervisor;
	} 

	public void setProjectArea(ProjectArea projectArea) {
		this.projectArea = projectArea;
	}

	
    public Project(String title, StaffMember supervisor, ProjectArea projectArea) {
        this.id=ID_GENERATOR.getAndIncrement();
    	this.title = title;
        this.supervisor = supervisor;
        this.projectArea = projectArea;
    }
    public Project(int id,String title, StaffMember supervisor, String projectArea) {
        this.id=id;
    	this.title = title;
        this.supervisor = supervisor;
        this.projectArea = ProjectArea.valueOf(projectArea);
    }
   

    public String getTitle() {
        return title;
    }

    public StaffMember getSupervisor() {
        return supervisor;
    }

    public ProjectArea getProjectArea() {
        return projectArea;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", supervisor=" + supervisor +
                ", projectArea=" + projectArea +
                '}';
    }
    public String toCsvString() {
        return id+","+ title+","+supervisor.getId()+","+projectArea.toString()+"\n";
    }

	
}
import java.util.ArrayList;

public class ProjectList {
    private int maxSize;
    private ArrayList<Project> projects;

    public ProjectList(int maxSize) {
        this.maxSize = maxSize;
        projects = new ArrayLIst<Project>();
    }

    public void addProject(Project project) {
        if (projects.size < maxSize) {
            projects.add(project);
        }
    }

    public int size() { return projects.size(); }

    public String toString() {
        str = "supervisor, project_name, special_focus\n"
        for (Project p : projects) {
            str += p.toString;
            str += "\n";
        }
        return str;
    }
}
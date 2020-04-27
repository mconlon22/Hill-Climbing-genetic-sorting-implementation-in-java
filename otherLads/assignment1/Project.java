public class Project {
    private String name;
    private String supervisor;
    private String specialFocus;

    public Project (String name, String supervisor, String specialFocus) {
        setName(name);
        setSupervisor(supervisor);
        setSpecialFocus(specialFocus);
    }

    public String getName() { return name; }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSupervisor() { return supervisor; }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getspecialFocus() { return specialFocus; }
    
    public void setSpecialFocus(String specialFocus) {
        this.specialFocus = specialFocus;
    }

    public String toString() {
        return name + ", " + supervisor + ", " + specialFocus;
    }
}
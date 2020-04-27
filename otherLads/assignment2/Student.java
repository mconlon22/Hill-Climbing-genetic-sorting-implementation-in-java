package assignment2;

import java.util.ArrayList;
import java.util.Objects;

public class Student {
    final private int ID;
    final private String name;
    final private Stream stream;
    final private ArrayList<Project> preferedProjects;

    public Student(int ID, String name, Stream stream, ArrayList<Project> preferedProjects) {
        this.ID = ID;
        this.name = name;
        this.stream = stream;
        this.preferedProjects = preferedProjects;
    }

    public int getID() {
        return this.ID;
    }


    public String getName() {
        return this.name;
    }


    public Stream getStream() {
        return this.stream;
    }


    public ArrayList<Project> getPreferedProjects() {
        return this.preferedProjects;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return ID == student.ID && Objects.equals(name, student.name) && Objects.equals(stream, student.stream) && Objects.equals(preferedProjects, student.preferedProjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, stream, preferedProjects);
    }

    @Override
    public String toString() {
        return "{" +
            " ID='" + getID() + "'" +
            ", name='" + getName() + "'" +
            ", stream='" + getStream() + "'" +
            ", preferedProjects='" + getPreferedProjects() + "'" +
            "}";
    }

}
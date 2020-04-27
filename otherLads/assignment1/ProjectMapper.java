/**
 * @author: Oisín Redmond  
 * @version: 1.0
 * Generates a list of generic students and projects, and then maps student preferences for projects 
 * using a normal distribution. 
*/

import java.util.ArrayList;
import java.util.Random;

public class ProjectMapper {
    public static void main(String [] args) { 
        ArrayList<Project> projects = new ArrayList<Project>();
        ArrayList<Student> students = new ArrayList<Student>();
        Random r = new Random();
        final int DATA_SIZE = 120;
        final int MEAN = DATA_SIZE/2;
        final int S_DIV = DATA_SIZE/15;
        for(int i = 0; i < DATA_SIZE; i++){
            projects.add(new Project("P" + i, i));
        }
        for(int i = 0; i < DATA_SIZE; i++){
            Student s = new Student("s" + (i + 1), new ArrayList<Project>());
            int j = 0;
            while(j < 10) {        
                int prob = (int) Math.round(r.nextGaussian()*S_DIV + MEAN);
                if(!s.preferences.contains(projects.get(prob))){
                    s.addPreference(projects.get(prob));
                    j++;
                }    
            }
            students.add(s);
        }
        for(int i = 0; i < DATA_SIZE; i++){
            System.out.println(students.get(i).to_s());
        }
    }

    static class Project {
        final String name;
        final int popularity;
        private Project(String name, int popularity){
            this.name = name;
            this.popularity = popularity;
        }
        public String to_s(){
            return this.name;
        }
    }

    static class Student {
        final String name;
        ArrayList<Project> preferences;
        private Student(String name, ArrayList<Project> preferences){
            this.name = name;
            this.preferences = preferences;
        }
        void addPreference(Project project){
            this.preferences.add(project);
        }
        public String to_s() {
            String output = "";
            output += this.name;
            output += " has preferences ";
            for(int i = 0; i < 10; i++){
                output += this.preferences.get(i).to_s();
                output += ", ";
            }
            return output;
        }
    }
}
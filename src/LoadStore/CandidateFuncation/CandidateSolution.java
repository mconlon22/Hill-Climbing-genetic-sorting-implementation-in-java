package LoadStore.CandidateFuncation;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

import LoadStore.Project;
import LoadStore.Student;
public class CandidateSolution implements Comparable< CandidateSolution >{
    private List<StudentProjectAllocation> candidateSolution= new ArrayList<>();
    private Double energyValue;
    Random rand=new Random();
    EnergyFunction energy=new EnergyFunction();
    FitnessFunction fitness=new FitnessFunction();
    
    public CandidateSolution(List<Student> students){
		for(Student student : students) {
            System.out.println("id"+student.getId());
		    Project randomProject=student.getProjectPreferences().get(rand.nextInt(10));
		    StudentProjectAllocation candidate=new StudentProjectAllocation(student,randomProject);
		    System.out.println(candidate.candidateId);

		    candidateSolution.add(candidate);
        }
        energyValue=energy.checkEnergy(candidateSolution);
        System.out.println("Fitness: "+ fitness.checkFitness(candidateSolution));
    }

    @Override
    public int compareTo(CandidateSolution o) {
        return this.energyValue.compareTo(o.energyValue);
    }

    public double getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(Double energyValue) {
        this.energyValue = energyValue;
    }
    
    public void change(List<Project> projects) {
    	for (int i = 0; i < projects.size()/10; i++) {
    		Project randomProject=projects.get(rand.nextInt(projects.size()));
        	this.candidateSolution.get(i).setProject(randomProject);
        	setEnergyValue(energy.checkEnergy(candidateSolution));
		}
    }
    
}
	



    


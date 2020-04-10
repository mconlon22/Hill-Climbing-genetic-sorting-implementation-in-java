package LoadStore.CandidateFuncation;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

import LoadStore.Project;
import LoadStore.Student;
public class CandidateSolution implements Comparable< CandidateSolution >{
    private List<StudentProjectAllocation> candidateSolution= new ArrayList<>();
    private Integer energyValue;
    Random rand=new Random();
    EnergyFunction energy=new EnergyFunction();
    public CandidateSolution(List<Student> students){
		for(Student student : students) {
            System.out.println("id"+student.getId());
		    int random = rand.nextInt(10);
		Project randomProject=student.getProjectPreferences().get(random);
        StudentProjectAllocation candidate=new StudentProjectAllocation(student,randomProject);
        System.out.println(candidate.candidateId);

		candidateSolution.add(candidate);
        }
        energyValue=energy.checkHardconstraints(candidateSolution);
    }

    @Override
    public int compareTo(CandidateSolution o) {
        return this.energyValue.compareTo(o.energyValue);
    }

    public Integer getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(Integer energyValue) {
        this.energyValue = energyValue;
    }
    
    }
	



    


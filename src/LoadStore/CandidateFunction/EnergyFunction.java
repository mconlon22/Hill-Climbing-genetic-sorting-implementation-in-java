package LoadStore.CandidateFunction;

import java.util.HashSet;
import java.util.List;

import javax.swing.JLabel;

import java.lang.Math;

import LoadStore.ProjectArea;
import LoadStore.SpecialFocus;

public class EnergyFunction {


/*
checking for duplicate projects
*/
	public Double checkEnergy(List<StudentProjectAllocation> candidates) {
		double energy = 0;
		energy += checkNumOfDuplicates(candidates)*2;
		energy += checkForMismatchingStream(candidates);
		energy += checkNumOfProjectsNotPreferred(candidates);
		energy += checkAveragePreferenceForProject(candidates);
		return energy;
	}
	public void energyString(List<StudentProjectAllocation> candidates) {
		double energy = 0;
		System.out.println("numDuplicates:" + checkNumOfDuplicates(candidates)+"\n MismatchingStream:"+ checkForMismatchingStream(candidates)+"\n NumOfProjectsNotPreferred"+ checkNumOfProjectsNotPreferred(candidates)+"\n checkAveragePreferenceForProject"+checkAveragePreferenceForProject(candidates));
		
	}

	
	public int checkNumOfDuplicates(List<StudentProjectAllocation> candidates){
	    HashSet<Integer> set = new HashSet<>(); 
	    int numDuplicates=0;
	    int num=0;
	    for(StudentProjectAllocation candidate : candidates){ 
	 
	        if (set.add(candidate.getProject().getId()) == false) { 
	            numDuplicates++;
	        }
	    } 
	
	    return numDuplicates;
	}
	/*
	 *  checking if the project stream matches the student stream
	 */
	public int checkForMismatchingStream(List<StudentProjectAllocation> candidates) {
		int numMismatches = 0;
		for(StudentProjectAllocation candidate : candidates){   
	        if (candidate.getStudent().getSpecialFocus() == SpecialFocus.CS) {
				if (candidate.getProject().getProjectArea() == ProjectArea.DS) {
					numMismatches++;
				}
			} else {
				if (candidate.getProject().getProjectArea() == ProjectArea.CS) {
					numMismatches++;
				}
			}
	    }
		return numMismatches;
	}
	
	public int checkNumOfProjectsNotPreferred(List<StudentProjectAllocation> candidates) {
		int numProjects = 0;
		int index;
		
		for(StudentProjectAllocation candidate : candidates){   
	        index = candidate.getStudent().getProjectPreferences().indexOf(candidate.getProject());
	        if (index == -1) {
	        	numProjects++;
	        }
	    }
		return numProjects;
	}
	
	/*
	 * checking how how much preference a student has for a project
	 */
	public double checkAveragePreferenceForProject(List<StudentProjectAllocation> candidates) {
		int preferenceSum = 0;
		int index;
		
		for(StudentProjectAllocation candidate : candidates){   
	        index = candidate.getStudent().getProjectPreferences().indexOf(candidate.getProject());
	        if (index >= 0) {
	        	preferenceSum += index;
	        }
	    }
		
		double avg = (double) preferenceSum/candidates.size();
		return avg;
	}
	
}
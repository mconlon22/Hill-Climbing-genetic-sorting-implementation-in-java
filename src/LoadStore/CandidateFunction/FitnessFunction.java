package LoadStore.CandidateFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JLabel;

import LoadStore.ProjectArea;
import LoadStore.SpecialFocus;

public class FitnessFunction {
		Double Numduplicateprojects;
		Double AveragePrefrence; 
		Double ProjectsNotPreferred;
		Double mismatchingStreams;
	public double checkFitness(List<StudentProjectAllocation> candidates) {
		double fitness = 0;
		
		
		fitness += checkAveragePreferenceForProject(candidates);
		return fitness;
	}
	
	public int checkNumWithoutDuplicates(List<StudentProjectAllocation> candidates){
	    HashMap map = new HashMap();
	    int numWithoutDuplicates=0;
	    for(StudentProjectAllocation candidate : candidates){   
	        if (map.putIfAbsent(candidate.getProject(), 1) != null) { 
	            map.put(candidate.getProject(), 0);
	        }
	    }
	    for(Object val : map.values()) {
	    	numWithoutDuplicates += (int) val;
	    }
	   

	    
	
	    return numWithoutDuplicates;
	}
	
	public int checkForMatchingStream(List<StudentProjectAllocation> candidates) {
		int numMatches = 0;
		for(StudentProjectAllocation candidate : candidates){   
	        if (candidate.getStudent().getSpecialFocus() == SpecialFocus.CS) {
				if (candidate.getProject().getProjectArea() != ProjectArea.DS) {
					numMatches++;
				}
			} else {
				if (candidate.getProject().getProjectArea() != ProjectArea.CS) {
					numMatches++;
				}
			}
	    }
		return numMatches;
	}
	
	public int checkNumOfProjectsPreferred(List<StudentProjectAllocation> candidates) {
		int numProjects = 0;
		int index;
		
		for(StudentProjectAllocation candidate : candidates){   
	        index = candidate.getStudent().getProjectPreferences().indexOf(candidate.getProject());
	        if (index != -1) {
	        	numProjects++;
	        }
	    }
		return numProjects;
	}
	
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
		avg = 9-avg;
		return avg;
	}
	
}

package LoadStore.CandidateFuncation;

import java.util.HashSet;
import java.util.List;

import LoadStore.CandidateFuncation.StudentProjectAllocation;

public class EnergyFunction {


/*
checking for duplicate projects
*/
public int checkHardconstraints(List<StudentProjectAllocation> candidates){
    HashSet set = new HashSet<>(); 
    int numDuplicates=0;
    for(StudentProjectAllocation candidate : candidates){
        
        if (set.add(candidate.project.getId()) == false) { 
            numDuplicates++;
    }
} 
System.out.println("num Duplicates:" +numDuplicates);

return numDuplicates;
}


}
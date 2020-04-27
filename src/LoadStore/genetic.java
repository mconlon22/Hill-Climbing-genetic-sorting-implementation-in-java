package LoadStore;

import java.util.List;

import LoadStore.CandidateFunction.CandidateSet;
import LoadStore.CandidateFunction.CandidateSolution;


public interface genetic {
    public void mateCandidates();
    public void getBestSolution();
}

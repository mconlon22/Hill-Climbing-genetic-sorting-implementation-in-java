package GA;

class Population {

    int popSize = 10;
    Individual[] indi = new Individual[10];
    int fittest = 0;

    //Initialize population
    public void initializePopulation(int size) {
        for (int i = 0; i < indi.length; i++) {
            indi[i] = new Individual();
        }
    }

    //Calculate fitness of each individual
    public void calculateFitness() {

        for (int i = 0; i < indi.length; i++) {
            indi[i].calcFitness();
        }
        getFittest();
    }
    
    //Get the fittest individual
    public Individual getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < indi.length; i++) {
            if (maxFit <= indi[i].fitness) {
                maxFit = indi[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = indi[maxFitIndex].fitness;
        try {
            return (Individual) indi[maxFitIndex].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Get the second fittest individual
    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int x = 0; x < indi.length; x++) {
            if (indi[x].fitness > indi[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = x;
            } else if (indi[x].fitness > indi[maxFit2].fitness) {
                maxFit2 = x;
            }
        }
        try {
            return (Individual) indi[maxFit2].clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < indi.length; i++) {
            if (minFitVal >= indi[i].fitness) {
                minFitVal = indi[i].fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

}
package GA;

import java.util.Random;

public class AlgoGA {

    Population pop = new Population();
    Individual fittest;
    Individual secFittest;
    int generationCount = 0;

    public static void main(String[] args) {

        Random rn = new Random();

        AlgoGA demo = new AlgoGA();

        demo.pop.initializePopulation(10);

        demo.pop.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.pop.fittest);

        //While population gets an individual with maximum fitness
        while (demo.pop.fittest < 5) {
            ++demo.generationCount;

            demo.selection();
            demo.crossover();

            if (rn.nextInt()%7 < 5) {
                demo.mutation();
            }

            //Add fittest offspring to population
            demo.addFittestOffspring();

            //Calculate new fitness value
            demo.pop.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.pop.fittest);
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+demo.pop.getFittest().fitness);
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(demo.pop.getFittest().genes[i]);
        }

        System.out.println("");

    }

    
    void selection() {

        //Select the most fittest individual
        fittest = pop.getFittest();

        //Select the second most fittest individual
        secFittest = pop.getSecondFittest();
    }

    void crossover() {
        Random rand = new Random();

        //Select a random crossover point
        int crossOverPoint = rand.nextInt(pop.indi[0].geneLen);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secFittest.genes[i];
            secFittest.genes[i] = temp;

        }

    }

    
    void mutation() {
        Random randi = new Random();
        int mutationPoint = randi.nextInt(pop.indi[0].geneLen);

        //Flip values at the mutation point
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = randi.nextInt(pop.indi[0].geneLen);

        if (secFittest.genes[mutationPoint] == 0) {
            secFittest.genes[mutationPoint] = 1;
        } else {
            secFittest.genes[mutationPoint] = 0;
        }
    }

    //Get fittest offspring
    Individual getFittestOffspring() {
        if (fittest.fitness > secFittest.fitness) {
            return fittest;
        }
        return secFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.calcFitness();
        secFittest.calcFitness();

        //Get index of least fit individual
        int leastFittestIndex = pop.getLeastFittestIndex();

        pop.indi[leastFittestIndex] = getFittestOffspring();
    }

}

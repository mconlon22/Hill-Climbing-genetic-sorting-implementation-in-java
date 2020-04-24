package GA;

import java.util.Random;

class Individual implements Cloneable{

    int fitness = 0;
    int[] genes = new int[5];
    int geneLen = 5;

    public Individual() {
        Random rand = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rand.nextInt() % 2);
        }

        fitness = 0;
    }

    //Calculate fitness
    public void calcFitness() {

        fitness = 0;
        for (int i = 0; i < 5; i++) {
            if (genes[i] == 1) {
                ++fitness;
            }
        }
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Individual in = (Individual)super.clone();
        in.genes = new int[5];
        for(int i = 0; i < in.genes.length; i++){
            in.genes[i] = this.genes[i];
        }
        return in;
    }
}
package org.example;

import java.util.Random;

public class
Individual implements Comparable{
    public static int probMutatie = 5;
    public static int interval = 200;
    private double[] chromosome;
    private double fitness;

    /**
     * Constructor of an individual
     */
    public Individual() {
        this.chromosome = new double[2];
    }

    /**
     * Method that creates a random individual that has genes between a certain limit
     */
    public void generareRandom() {
        chromosome[0] = Main.rnd.nextDouble() * interval - 100;
        chromosome[1] = Main.rnd.nextDouble() * interval - 100;
        calcFitness();
    }

    /**
     * Method that calculates the fitness of an individual
     */
    public void calcFitness(){
        double x = this.getGene(0);
        double y = this.getGene(1);
        Ackley ackley = new Ackley(x, y);
        ackley.calculateResult();
        setFitness(ackley.getResult());
    }

    /**
     * Method that mutates the genes of an individual
     */
    public void mutatie(){
        if(Main.rnd.nextDouble() <= probMutatie){
            chromosome[0] += Main.rnd.nextDouble()*2-1;
            chromosome[1] += Main.rnd.nextDouble()*2-1;
        }
    }
    public double[] getChromosome(){
        return chromosome;
    }
    public double getGene(int index){
        return chromosome[index];
    }
    public void setGene(double geneValue, int index){
        chromosome[index] = geneValue;
    }
    public double getFitness(){
        return fitness;
    }
    public void setFitness(double value){
        fitness = value;
    }
    public int compareTo(Object obj){
        Individual another = (Individual)obj;
        return Double.compare(this.fitness, another.fitness);
    }
}

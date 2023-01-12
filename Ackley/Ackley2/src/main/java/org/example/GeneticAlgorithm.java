package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {
    private int maxGeneration = 1000;
    private int maxIndividuals = 50;
    private ArrayList<Individual> population = new ArrayList();
    private ArrayList<Individual> populationTemp = new ArrayList();
    private double populationFitness;
    private static Random rnd = new Random();

    /**
     * Method that runs the genetic algorithm
     */
    public void run() {
        this.generateIntialPopulation();
        this.evaluatePopulation();
        Individual globalFittest = new Individual();
        globalFittest.setFitness(Double.MAX_VALUE);
        int index = -1;
        for (int generation = 1; generation <= maxGeneration; generation++) {
            for (int i = 0; i < maxIndividuals / 2; i++) {
                Individual p1 = this.tournament();
                Individual p2 = this.tournament();
                this.doCrossover(p1, p2);
            }
            this.doSurvive();
            Collections.sort(population);
            Individual fittest = population.get(0);
            if(fittest.getFitness() < globalFittest.getFitness()){
                globalFittest.setFitness(fittest.getFitness());
                globalFittest.setGene(fittest.getGene(0),0);
                globalFittest.setGene(fittest.getGene(1),1);
                index = generation;
            }
            System.out.println(generation +"  Fittest: "+ fittest.getFitness() +"         "+ fittest.getGene(0) + "         " +fittest.getGene(1));
            //System.out.println(fittest.getFitness());
        }
        System.out.println("Best Solution: " + globalFittest.getFitness()+"    Genaration: "+index);
    }

    /**
     * Method that generates initial population
     */
    private void generateIntialPopulation() {
        for (int i = 0; i < maxIndividuals; i++) {
            Individual newIndividual = new Individual();
            newIndividual.generareRandom();
            population.add(newIndividual);
        }
        Collections.sort(population);
    }

    /**
     * Method that uses tournament selection to select parents for crossover
     * @return best individual of tournament
     */
    private Individual tournament() {
        ArrayList<Individual> tournament = new ArrayList();
        for (int i = 0; i <= 4; i++) {
            int index = rnd.nextInt(maxIndividuals - 1);
            tournament.add(population.get(index));
        }
        Collections.sort(tournament);
        return tournament.get(0);
    }

    /**
     *Method that creates to children and creates a temporary population
     * @param p1 first parent
     * @param p2 second parent
     */
    private void doCrossover(Individual p1, Individual p2) {
        Individual c1 = new Individual();   // copil 1
        Individual c2 = new Individual();   // copil 2

        double p = rnd.nextDouble();        // pondere

        double c1g0 = p * p1.getGene(0) + (1 - p) * p2.getGene(0);
        double c1g1 = p * p1.getGene(1) + (1 - p) * p2.getGene(1);
        double c2g0 = p * p1.getGene(0) + (1 - p) * p2.getGene(0);
        double c2g1 = p * p1.getGene(1) + (1 - p) * p2.getGene(1);

        c1.setGene(c1g0, 0);
        c1.setGene(c1g1, 1);
        c2.setGene(c2g0, 0);
        c2.setGene(c2g1, 1);
        c1.mutatie();
        c2.mutatie();
        c1.calcFitness();
        c2.calcFitness();
        populationTemp.add(c1);
        populationTemp.add(c2);
    }

    /**
     * Method that finds the fitness of the population
     */
    public void evaluatePopulation(){
        double populationFitness = 0;
        for(int i=0; i<maxIndividuals;i++){
            populationFitness += population.get(i).getFitness();
        }
        this.populationFitness = populationFitness;
    }

    /**
     * Method that adds the Individuals from the temporary population in the main one
     */
    private void doSurvive() {
        population.clear();
        population.addAll(populationTemp);
        populationTemp.clear();
    }
}

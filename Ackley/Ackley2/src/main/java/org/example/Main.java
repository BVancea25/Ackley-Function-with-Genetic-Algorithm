package org.example;

import java.util.Random;

public class Main {
    public static Random rnd = new Random();
    public static void main(String[] args) {
        GeneticAlgorithm alg = new GeneticAlgorithm();
        alg.run();
    }
}
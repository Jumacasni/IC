package com.qap;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Reader r = new Reader("tai256c.dat");

        // PARAMETROS
        int POPULATION_SIZE = 100;
        int N_ITERATIONS = 20;
        int TOURNAMENT_SIZE = 10;
        double MUTATION_PROBABILITY = 0.1;
        String version = "Baldwinian";
        System.out.println("PARÁMETROS:");
        System.out.println("Versión: "+version);
        System.out.println("Tamaño de la población: "+String.valueOf(POPULATION_SIZE));
        System.out.println("Número de iteraciones: "+String.valueOf(N_ITERATIONS));
        System.out.println("Tamaño del torneo: "+String.valueOf(TOURNAMENT_SIZE));
        System.out.println("Probabilidad de mutación: "+String.valueOf(MUTATION_PROBABILITY));
        System.out.println("\n");

        // Creación del algoritmo
        GeneticAlgorithm algorithm = new GeneticAlgorithm(version, POPULATION_SIZE, N_ITERATIONS, TOURNAMENT_SIZE, MUTATION_PROBABILITY);

        long start = System.nanoTime();

        // Inicio del algoritmo
        Individual solution = algorithm.start();

        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("\nRESULTADOS:");
        System.out.println("Vector solución: "+ Arrays.toString(solution.getChromosomes()));
        solution.calculateFitness(); // Para la versión baldwiniana
        System.out.println("Fitness: "+String.valueOf(solution.getFitness()));
        float nota = (float) (5-100*((solution.getFitness()-44759294.0)/44759294.0));
        System.out.println("Nota obtenida: "+String.valueOf(nota));
        System.out.println("Tiempo transcurrido: "+String.valueOf(timeElapsed/1000000000.0));
    }

}

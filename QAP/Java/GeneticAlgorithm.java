package com.qap;

import java.util.*;

public class GeneticAlgorithm {

    /**
     * Tamaño de la población
     */
    private int POPULATION_SIZE;

    /**
     * Número de iteraciones
     */
    private int N_ITERATIONS;

    /**
     * Tamaño del torneo
     */
    private int TOURNAMENT_SIZE;

    /**
     * Probabilidad de mutación
     */
    private double MUTATION_PROBABILITY;

    /**
     * Versión a ejecutar
     */
    private String version;

    /**
     * Constructor
     *
     * @param t versión a ejecutar
     * @param p tamaño de la población
     * @param i número de iteraciones
     * @param ts tamaño del torneo
     * @param m probabilidad de mutación
     * @return algoritmo a ejecutar
     */
    public GeneticAlgorithm(String t, int p, int i, int ts, double m){
        this.version = t;
        this.POPULATION_SIZE = p;
        this.N_ITERATIONS = i;
        this.TOURNAMENT_SIZE = ts;
        this.MUTATION_PROBABILITY = m;
    }

    /**
     * Este método es el que contiene el proceso del algoritmo evolutivo
     *
     * @return individuo solución del problema
     */
    public Individual start(){
        Population currentPopulation = createPopulation(POPULATION_SIZE);

        for(int i=0; i<N_ITERATIONS; ++i){
            Population newPopulation = new Population(POPULATION_SIZE*2);
            System.out.println("Iteración "+String.valueOf(i+1+"/"+String.valueOf(N_ITERATIONS)));

            for(int j=0; j<POPULATION_SIZE; ++j){
                Individual parent1 = tournamentSelection(currentPopulation);
                Individual parent2 = tournamentSelection(currentPopulation);
                // Para evitar que se escoja el mismo padre
                while(parent1.equals(parent2.getChromosomes())){
                    parent2 = tournamentSelection(currentPopulation);
                }

                Individual[] children = uniformLikeCrossover(parent1, parent2);

                swapMutation(children[0]);
                swapMutation(children[1]);

                newPopulation.addIndividual(children[0], 2*j);
                newPopulation.addIndividual(children[1], 2*j+1);
            }

            calculateAllFitness(newPopulation);

            Individual worstIndividual = newPopulation.getWorst();
            int indexWorst = Arrays.asList(newPopulation.getIndividuals()).indexOf(worstIndividual);
            Individual bestIndividual = currentPopulation.getFittest();

            newPopulation.addIndividual(bestIndividual, indexWorst);

            currentPopulation = newPopulation;
        }

        return currentPopulation.getFittest();
    }

    /**
     * Crea una población y calcula el fitness para cada individuo
     *
     * @param size tamaño de la población
     * @return población creada
     */
    private Population createPopulation(int size){
        Population population = new Population(size);

        for(int i=0; i<size; ++i){
            Individual individual = new Individual(Reader.getSize(), false);
            individual.calculateFitness();
            population.addIndividual(individual,i);
        }

        return population;
    }

    /**
     * Implementación de la selección por torneo
     *
     * @param population población
     * @return individuo seleccionado mediante el torneo
     */
    private Individual tournamentSelection(Population population){
        Population tournament = new Population(TOURNAMENT_SIZE);
        Random rnd = new Random();
        Individual pickIndividual = null;
        ArrayList<Integer> pickedNumbers = new ArrayList<Integer>();
        for(int i=0; i<TOURNAMENT_SIZE; ++i){
            int intRandom = rnd.nextInt(population.getIndividuals().length);
            pickIndividual= population.getIndividual(intRandom);
            // Para evitar que un individuo esté repetido
            while (pickedNumbers.contains(intRandom)){
                intRandom = rnd.nextInt(population.getIndividuals().length);
                pickIndividual = population.getIndividual(intRandom);
            }
            tournament.addIndividual(pickIndividual, i);
            pickedNumbers.add(intRandom);
        }

        return tournament.getFittest();
    }

    /**
     * Crea dos hijos a partir de dos padres
     *
     * @param parent1 primer padre
     * @param parent2 segundo padre
     * @return dos hijos
     */
    private Individual[] uniformLikeCrossover(Individual parent1, Individual parent2){

        Individual[] children = new Individual[2];

        children[0] = generateChild(parent1, parent2);
        children[1] = generateChild(parent1, parent2);

        return children;
    }

    /**
     * Genera un hijo aplicando el algoritmo ULX
     *
     * @param parent1 primer padre
     * @param parent2 segundo padre
     * @return individuo generado
     */
    private Individual generateChild(Individual parent1, Individual parent2){
        Individual child = new Individual(Reader.getSize(), true);
        ArrayList<Integer> unassignedChromosomes = new ArrayList<Integer>();
        ArrayList<Integer> unassignedIndex = new ArrayList<Integer>();

        for(int i=0; i<parent1.getChromosomes().length; ++i){
            if(parent1.getChromosomes()[i] == parent2.getChromosomes()[i]){
                child.setChromosome(parent1.getChromosomes()[i], i);
            }

            else if(!child.containsChromosome(parent1.getChromosomes()[i]) && !child.containsChromosome(parent2.getChromosomes()[i])){
                Random rnd = new Random();
                if(rnd.nextBoolean()){
                    child.setChromosome(parent1.getChromosomes()[i], i);
                }
                else{
                    child.setChromosome(parent2.getChromosomes()[i], i);
                }
            }

            else{
                unassignedIndex.add(i);
            }
        }

        for(int i=0; i<parent1.getChromosomes().length; ++i){
            if(!child.containsChromosome(i)){
                unassignedChromosomes.add(i);
            }
        }

        Collections.shuffle(unassignedChromosomes);
        Collections.shuffle(unassignedIndex);

        if(unassignedChromosomes.size() != unassignedIndex.size()){
            System.out.println("unassignedChromosomes != unassignedIndex");
        }

        for(int i=0; i<unassignedChromosomes.size(); ++i){
            child.setChromosome(unassignedChromosomes.get(i), unassignedIndex.get(i));
        }

        return child;
    }

    /**
     * Operador de mutación
     *
     * @param individual individuo a mutar
     */
    private void swapMutation(Individual individual){
        double prob = Math.random();
        Random rnd = new Random();

        if(prob < MUTATION_PROBABILITY){
            int indexChromosome1 = rnd.nextInt(individual.getChromosomes().length);
            int indexChromosome2 = rnd.nextInt(individual.getChromosomes().length);
            while(indexChromosome1 == indexChromosome2){
                indexChromosome2 = rnd.nextInt(individual.getChromosomes().length);
            }

            individual.swap(indexChromosome1, indexChromosome2);
        }
    }

    /**
     * Calcula el fitness de todos los individuos de una población.
     * Se calcula dependiendo de la versión elegida.
     *
     * @param population población
     */
    private void calculateAllFitness(Population population){

        if(version == "Standard"){
            for(int i=0; i<population.getIndividuals().length; ++i){
                population.getIndividual(i).calculateFitness();
            }
        }
        else if(version == "Baldwinian"){
            for(int i=0; i<population.getIndividuals().length; ++i){
                population.getIndividual(i).calculateFitnessBaldwinian();
            }
        }
        else if(version == "Lamarckian"){
            for(int i=0; i<population.getIndividuals().length; ++i){
                population.getIndividual(i).calculateFitnessLamarckian();
            }
        }
        else{
            System.out.println("Error en la versión deseada");
        }

    }
}

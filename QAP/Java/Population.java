package com.qap;

public class Population {
    /**
     * Vector de individuos de la población
     */
    private Individual[] individuals;

    /**
     * Constructor
     *
     * @param size tamaño de la población
     * @return población de individuos
     */
    public Population(int size){
        this.individuals = new Individual[size];
    }

    /**
     * Inserta un individuo en la población
     *
     * @param individual individuo a insertar
     * @param index índice del vector donde se va a insertar el individuo
     */
    public void addIndividual(Individual individual, int index){
        this.individuals[index] = individual;
    }

    /**
     * Devuelve el individuo de la población con mejor fitness
     *
     * @return individuo con mejor fitness
     */
    public Individual getFittest(){
        Individual fittest = this.individuals[0];
        int currentFitness = fittest.getFitness();

        for(int i=1; i<this.individuals.length; ++i){
            if(this.individuals[i].getFitness() < currentFitness){
                currentFitness = this.individuals[i].getFitness();
                fittest = this.individuals[i];
            }
        }

        return fittest;
    }

    /**
     * Devuelve el individuo de la población con peor fitness
     *
     * @return individuo con peor fitness
     */
    public Individual getWorst(){
        Individual worst = this.individuals[0];
        int currentFitness = worst.getFitness();

        for(int i=1; i<this.individuals.length; ++i){
            if(this.individuals[i].getFitness() > currentFitness){
                currentFitness = this.individuals[i].getFitness();
                worst = this.individuals[i];
            }
        }

        return worst;
    }

    /**
     * Devuelve un individuo de la población
     *
     * @param index índice
     * @return individuo
     */
    public Individual getIndividual(int index) {
        return this.individuals[index];
    }

    /**
     * Devuelve los individuos de la población
     *
     * @return vector de individuos
     */
    public Individual[] getIndividuals(){
        return this.individuals;
    }
}

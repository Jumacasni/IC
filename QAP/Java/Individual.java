package com.qap;

import java.util.Random;

public class Individual {
    /**
     * Vector de cromosomas cuyos elementos no se repiten
     */
    private int[] chromosomes;

    /**
     * Fitness del individuo
     */
    private int fitness;

    /**
     * Constructor
     *
     * @param size tamaño del vector de cromosomas del individuo
     * @param empty true si el vector de cromosomas se inicializa a -1, false si se genera una
     *              solución aleatoria
     * @return individuo
     */
    public Individual(int size, boolean empty){
        this.fitness = Integer.MAX_VALUE;
        this.chromosomes = new int[size];

        if(!empty){
            for(int i=0; i<size; ++i){
                chromosomes[i] = i;
            }

            shuffleChromosomes();
        }

        else{
            for(int i=0; i<size; ++i){
                chromosomes[i] = -1;
            }
        }
    }

    /**
     * Constructor de copia
     *
     * @param i individuo a copiar
     * @return copia del individuo
     */
    public Individual(Individual i){
        this.fitness = i.fitness;
        this.chromosomes = new int[i.chromosomes.length];
        System.arraycopy( i.chromosomes, 0, this.chromosomes, 0, i.chromosomes.length );
    }

    /**
     * Genera una solución aleatoria del vector de cromosomas del individuo
     *
     */
    private void shuffleChromosomes(){
        Random rnd = new Random();
        for(int i=this.chromosomes.length-1; i>0; --i){
            int index = rnd.nextInt(i+1);
            int aux = this.chromosomes[index];
            this.chromosomes[index] = this.chromosomes[i];
            this.chromosomes[i] = aux;
        }
    }

    /**
     * Calcula el fitness del individuo
     *
     */
    public void calculateFitness(){
        int totalFitness = 0;
        for(int i=0; i<Reader.getFlowMatrix().length; ++i){
            for(int j=0; j<Reader.getFlowMatrix().length; ++j){
                int chromosomeI = this.chromosomes[i];
                int chromosomeJ = this.chromosomes[j];

                totalFitness += Reader.getFlowMatrix()[i][j] * Reader.getDistanceMatrix()[chromosomeI][chromosomeJ];
            }
        }

        if(totalFitness < 44759294){
            System.out.println("Error al calcular el fitness");
        }

        this.fitness = totalFitness;
    }

    /**
     * Comprueba si un individuo es igual a otro
     *
     * @param c cromosomas del individuo a evaluar
     * @return true si los cromosomas del individuo son iguales, false en caso contrario
     */
    public void calculateFitnessBaldwinian(){
        Individual best = new Individual(this);
        int bestFitness = best.getFitness();

        for(int i=0; i<best.getChromosomes().length; ++i) {
            for (int j = i + 1; j < best.getChromosomes().length; ++j) {
                Individual T = new Individual(best);
                T.setChromosome(best.getChromosomes()[j], i);
                T.setChromosome(best.getChromosomes()[i], j);
                int optFitness = T.calculateOptFitness(T.getChromosomes());

                if (optFitness < best.getFitness()) {
                    best.fitness = optFitness;
                    best.chromosomes = T.getChromosomes();
                    this.fitness = optFitness;
                }
            }
        }
    }

    /**
     * Búsqueda de óptimo local para la versión Lamarckiana
     *
     */
    public void calculateFitnessLamarckian(){
        Individual best = new Individual(this);

        for(int i=0; i<best.getChromosomes().length; ++i){
            for(int j=i+1; j<best.getChromosomes().length; ++j){
                Individual T = new Individual(best);
                T.setChromosome(best.getChromosomes()[j],i);
                T.setChromosome(best.getChromosomes()[i],j);
                int optFitness = T.calculateOptFitness(T.getChromosomes());

                if(optFitness < best.getFitness()){
                    best.fitness = optFitness;
                    best.chromosomes = T.getChromosomes();
                    this.fitness = optFitness;
                    this.chromosomes = best.getChromosomes();
                }
            }
        }
    }

    /**
     * Calcula el fitness de un vector de cromosomas
     *
     * @param c vector de cromosomas
     * @return fitness calculado del vector
     */
    private int calculateOptFitness(int[] c){
        int totalFitness = 0;
        for(int i=0; i<Reader.getFlowMatrix().length; ++i){
            for(int j=0; j<Reader.getFlowMatrix().length; ++j){
                int chromosomeI = c[i];
                int chromosomeJ = c[j];

                totalFitness += Reader.getFlowMatrix()[i][j] * Reader.getDistanceMatrix()[chromosomeI][chromosomeJ];
            }
        }

        if(totalFitness < 44759294){
            System.out.println("Error al calcular el fitness");
        }

        return totalFitness;
    }

    /**
     * Comprueba si un individuo es igual a otro
     *
     * @param c cromosomas del individuo a evaluar
     * @return true si los cromosomas del individuo son iguales, false en caso contrario
     */
    public boolean equals(int[] c){
        for(int i=0; i<this.chromosomes.length; ++i){
            if(this.chromosomes[i] != c[i])
                return false;
        }
        return true;
    }

    /**
     * Intercambia dos cromosomas
     *
     * @param index1 índice del cromosoma
     * @param index2 índice de cromosoma
     */
    public void swap(int index1, int index2){
        int aux = this.chromosomes[index1];
        this.chromosomes[index1] = this.chromosomes[index2];
        this.chromosomes[index2] = aux;
    }

    /**
     * Devuelve los cromosomas del individuo
     *
     * @return cromosomas del individuo
     */
    public int[] getChromosomes() {
        return chromosomes;
    }

    /**
     * Sustituye un cromosoma en el individuo
     *
     * @param value valor del cromosoma
     * @param index índice en el que se va a insertar
     */
    public void setChromosome(int value, int index){
        this.chromosomes[index] = value;
    }

    /**
     * Devuelve el fitness de un individuo
     *
     * @return fitness del individuo
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * Comprueba si un individuo contiene un cromosoma
     *
     * @param chromosome cromosoma
     * @return true si el individuo contiene el cromosoma, false en caso contrario
     */
    public boolean containsChromosome(int chromosome){
        for(int c : this.chromosomes){
            if(c == chromosome){
                return true;
            }
        }

        return false;
    }
}

package com.qap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    /**
     * Matriz de flujos
     */
    private static int flow_matrix[][];

    /**
     * Matriz de distancias
     */
    private static int distance_matrix[][];

    /**
     * Tamaño de matrices
     */
    private static int size;

    /**
     * Constructor
     *
     * @param file nombre del fichero que se va a parsear
     * @return objeto con las matrices de flujo y distancia
     */
    public Reader(String file) {

        try {
            File myFile = new File(file);
            Scanner myReader = new Scanner(myFile);
            ArrayList<String> lines = new ArrayList<String>();

            this.size = myReader.nextInt();

            this.flow_matrix = new int[size][size];
            for (int i=0; i<size; ++i) {
                for (int j=0; j<size; ++j) {
                    this.flow_matrix[i][j] = myReader.nextInt();
                }
            }

            this.distance_matrix = new int[size][size];
            for (int i=0; i<size; ++i) {
                for (int j=0; j<size; ++j) {
                    this.distance_matrix[i][j] = myReader.nextInt();
                }
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve la matriz de flujo
     *
     * @return la matriz de flujos
     */
    public static int[][] getFlowMatrix() {
        return flow_matrix;
    }

    /**
     * Devuelve la matriz de distancias
     *
     * @return la matriz de distancias
     */
    public static int[][] getDistanceMatrix() {
        return distance_matrix;
    }

    /**
     * Constructor
     *
     * @return el tamaño de las matrices
     */
    public static int getSize() {
        return size;
    }
}

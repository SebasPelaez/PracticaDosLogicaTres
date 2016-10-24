package Logica;

import java.util.ArrayList;

/**
 *
 * @author Sebastian y Juan
 */
public class Grafo {
    
    private int tamaño;
    private int grafo[][];
    private ArrayList<String> palabras;
    
    public Grafo(int n){
        tamaño= n;
        grafo = new int [n][n];
        palabras = new ArrayList<String>();
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int[][] getGrafo() {
        return grafo;
    }

    public void setGrafo(int[][] grafo) {
        this.grafo = grafo;
    }
    
    public int getElementoGrafo(int i, int j){
        return grafo[i][j];
    }
    
    public void setElementoGrafo(int i, int j, int valor){
        grafo[i][j]=valor;
    }

    public ArrayList getPalabras() {
        return palabras;
    }

    public void setPalabras(String palabra) {
        palabras.add(palabra);
    } 
    
    
}

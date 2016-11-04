package Logica;

import java.util.ArrayList;

/**
 *
 * @author Sebastian y Juan
 */
public class Grafo {

    private int tamano;
    private int adya[][];
    private int costos[][];
    private ArrayList<String> palabras;
    private int visitados[];

    /**
     * Constructor de la clase
     *
     * @param n El parámetro n define el número de vértices con el que se
     * construira el grafo
     */
    public Grafo(int n) {
        tamano = n;
        adya = new int[n][n];
        palabras = new ArrayList<String>();
        visitados = new int[n];
        costos = new int[n][n];
    }

    /**
     * Método que retorna el número de vérties del grafo
     *
     * @return Número de vértices del grafo
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Método que modifica el número de vérties del grafo
     *
     * @param tamano El parámetro tamano define el nuevo número de vértices que
     * tendrá el grafo
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    /**
     * Método que retorna la matriz de adyancencia con la cual se está
     * representando el grafo
     *
     * @return Matriz de adyacencia
     */
    public int[][] getGrafoAdya() {
        return adya;
    }

    /**
     * Método que modifica la matriz de adyacencia con la que está representada
     * el grafo
     *
     * @param grafo El parámetro grafo define la nueva matriz de adyacencia que
     * representará el grafo
     */
    public void setGrafoAdya(int[][] grafo) {
        this.adya = grafo;
    }

    /**
     * Método que retorna un elemento ubicado en la posición (i,j) de la matriz
     * de adyacencia con la que está representado el grafo
     *
     * @param i El parámetro i define la fila donde está ubicado el elemento que
     * se desea retornar de la matriz de adyacencia
     * @param j El parámetro j define la columna donde está ubicado el elemento
     * que se desea retornar de la matriz de adyacencia
     * @return Elemento ubicado en la posición (i,j) de la matriz de adyacencia
     */
    public int getElementoGrafoAdya(int i, int j) {
        return adya[i][j];
    }

    /**
     * Método que modifica el valor del elemento ubicado en la posición (i,j) de
     * la matriz de adyacencia
     *
     *
     * @param i El parámetro i define la fila donde está ubicado el elemento que
     * se le va a modificar el valor
     * @param j El parámetro j define la columna donde está ubicado el elemento
     * que se le va a modificar el valor
     * @param valor El valor nuevo a asignar al elemento (i,j) de la matriz de
     * adyacencia
     */
    public void setElementoGrafoAdya(int i, int j, int valor) {
        adya[i][j] = valor;
    }

    /**
     * Método que retorna un ArrayList de Strings con las palabras con las que
     * está compuesto el grafo
     *
     * @return ArrayList con las palabras del grafo
     */
    public ArrayList getPalabras() {
        return palabras;
    }
    
    public String getElementoPalabras(int i){
        return palabras.get(i);
    }

    /**
     * Método que añade una nueva palabra al ArrayList que contiene las palabras
     * con las que está compuesto el grafo
     *
     * @param palabra El parámetro palabra define la nueva palabra que será
     * agregada al ArrayList de palabras con las que está compuesto el grafo
     */
    public void setPalabras(String palabra) {
        palabras.add(palabra);
    }

    /**
     * Método que retorna del vector visitados[i] 1 si el vértice i ya fue
     * visitado anteriormente, 0 si no ha sido visitado
     *
     * @param i El parámetro i define el vértice del grafo al cual se desea
     * conocer si ya fue visitado
     */
    public int getValorVisitados(int i) {
        return visitados[i];
    }

    /**
     * Método que modifica el estado "visitado" del vértice i en el vector
     * visitados.
     *
     * @param i El parámetro i define el vértice del grafo al cual se desea
     * modificar su estado visitado
     * @param val El parámetro val define el valor por el cual será modificado
     * el elemento i del vector visitados
     */
    public void setValorVisitados(int i, int val) {
        visitados[i] = val;
    }

    /**
     * Método que retorna el vector visitados
     *
     * @return El vector visitados
     */
    public int[] getVisitados() {
        return visitados;
    }

    /**
     * Método que modifica la matriz de costos
     *
     * @param costos El parámetro costos define la nueva matriz de costos
     */
    public void setMatCostos(int costos[][]) {
        this.costos = costos;
    }

    /**
     * Método que retorna la matriz de costos
     *
     * @return Matriz de costos
     */
    public int[][] getMatCostos() {
        return costos;
    }

    /**
     * Método que retorna el elemento (i,j) de la matriz de costos
     *
     * @param i El parámetro i define la fila donde està ubicado el elemento
     * @param j El parámetro j define la columna donde està ubicado el elemento
     * @return Elemento (i,j) de la matriz costos
     */
    public int getElementoMatCostos(int i, int j) {
        return costos[i][j];
    }

    /**
     * Método que modifica el valor del elemento (i,j) de la matriz costos
     *
     * @param i El parámetro i define la fila donde està ubicado el elemento
     * @param j El parámetro j define la columna donde està ubicado el elemento
     * @param valor El paràmetro valor defino el nuevo valor que tomarà el
     * elemento (i,j) de la matriz costos
     */
    public void setElementoMatCostos(int i, int j, int valor) {
        costos[i][j] = valor;
    }
}

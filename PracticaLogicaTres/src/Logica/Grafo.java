package Logica;

import java.util.ArrayList;

/**
 *
 * @author Sebastian y Juan
 */
public class Grafo {
    
    private int tamaño;
    private int adya[][];
    private int costos[][];
    private ArrayList<String> palabras;
    private int visitados[];
    
    public Grafo(int n){
        tamaño= n;
        adya = new int [n][n];
        palabras = new ArrayList<String>();
        visitados = new int [n];
        costos = new int[n][n];
    }
       
    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int[][] getGrafoAdya() {
        return adya;
    }

    public void setGrafoAdya(int[][] grafo) {
        this.adya = grafo;
    }
    
    public int getElementoGrafoAdya(int i, int j){
        return adya[i][j];
    }
    
    public void setElementoGrafoAdya(int i, int j, int valor){
        adya[i][j]=valor;
    }

    public ArrayList getPalabras() {
        return palabras;
    }

    public void setPalabras(String palabra) {
        palabras.add(palabra);
    } 
    
    public int getValorVisitados(int i){
        return visitados[i];
    }
    
    public void setValorVisitados(int i,int val){
        visitados[i]=val;
    }
    
    public int[] getVisitados(){
        return visitados;
    }
    
    public void setMatCostos(int costos[][]){
        this.costos=costos;
    }
    
    public int[][] getMatCostos(){
        return costos;
    }
    
    public int getElementoMatCostos(int i,int j){
        return costos[i][j];
    }
    
    public void setElementoMatCostos(int i,int j,int valor){
        costos[i][j]=valor;
    }
}

package Logica;

import java.util.ArrayList;

/**
 *
 * @author Sebastian y Juan
 */
public class Grafo {
    
    private int tamaño;
    private int adya[][];
    private int inci[][];
    private ArrayList<String> palabras;
    private int visitados[];
    
    public Grafo(int n){
        tamaño= n;
        adya = new int [n][n];
        palabras = new ArrayList<String>();
        visitados = new int [n];
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
    
    public void setGrafoInci(int inci[][]){
        this.inci=inci;
    }
    
    public int[][] getGrafoInci(){
        return inci;
    }
    
    public int getElementoGrafoInci(int i,int j){
        return inci[i][j];
    }
}

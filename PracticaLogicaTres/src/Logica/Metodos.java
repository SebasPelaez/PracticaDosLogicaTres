/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author aux10
 */
public class Metodos {
    
    private Grafo grafo;

    public DefaultTreeModel construirDiccionario(DefaultMutableTreeNode raiz,String ruta) {
        DefaultMutableTreeNode diccionario = raiz;
        DefaultTreeModel modelo = new DefaultTreeModel(diccionario);
        Vector<String> words = cargarConPalabras(ruta);
        DefaultMutableTreeNode valorLetra = null;
        DefaultMutableTreeNode dato;
        int i = 0;
        int j = 0;
        char letra = '.';
        for (String palabra : words) {
            if (letra != palabra.charAt(0)) {
                letra = palabra.charAt(0);
                valorLetra = new DefaultMutableTreeNode(String.valueOf(letra));
                modelo.insertNodeInto(valorLetra, diccionario, j++);
                i = 0;
            }
            dato = new DefaultMutableTreeNode(palabra);
            modelo.insertNodeInto(dato, valorLetra, i++);
        }
        return modelo;
    }

    public Vector<String> cargarConPalabras(String ruta) {
        Vector<String> words = new Vector<String>();
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            // Lectura del fichero
            while ((linea = br.readLine()) != null) {
                if (!words.contains(linea)) {
                    linea = quitarAcentos(linea);
                    linea = linea.toLowerCase().replaceAll("\\p{Punct}+", "");
                    words.add(linea);
                }
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(words);
        ordenarPalabrasPorLetra(words);
        return words;
    }

    public String quitarAcentos(String palabra) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = palabra;
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }

    public void ordenarPalabrasPorLetra(Vector<String> words) {
        Hashtable<Integer, char[]> hash = new Hashtable<Integer, char[]>();
        int i = 0;
        for (String palabra : words) {
            hash.put(i, palabra.toCharArray());
            i++;
        }
        instanciarGrafo(i);
        conectarGrafo(hash);
    }

    public void instanciarGrafo(int n) {
        grafo = new Grafo(n);
    }

    public void conectarGrafo(Hashtable<Integer, char[]> datos) {
        int n = grafo.getTamaño();
        char a[];
        char b[];
        for (int i = 0; i < n ; i++) {
            a = datos.get(i);
            grafo.setPalabras(String.valueOf(datos.get(i)));
            for (int j = i + 1; j < n; j++) {
                b = datos.get(j);
                if (evaluarConexiones(a, b)) {
                    grafo.setElementoGrafoAdya(i, j, 1);
                    grafo.setElementoGrafoAdya(j, i, 1);
                    grafo.setElementoMatCostos(i, j, 1);
                    grafo.setElementoMatCostos(j, i, 1);
                }else{
                    grafo.setElementoMatCostos(i, j, 99999999);
                    grafo.setElementoMatCostos(j, i, 99999999);
                }
            }
        }
    }

    public boolean evaluarConexiones(char[] a, char b[]) {
        int aLengt = a.length;
        int bLengt = b.length;
        int i = 0;
        int contDiferencias = 0;
        while (i < aLengt && i < bLengt) {
            if (a[i] != b[i]) {
                contDiferencias++;
                if (contDiferencias == 2) {
                    return false;
                }
            }
            i++;
        }
        if ((contDiferencias == 1 && aLengt == bLengt)) {
            return true;
        }
        if ((contDiferencias == 1 && aLengt != bLengt)) {
            return false;
        } else if (contDiferencias == 0 && ((aLengt + 1) == bLengt || (bLengt + 1) == aLengt)) {
            return true;
        } else{
            return false;
        }
    }

    public void imprimirGrafoAdyacencia(JTextArea txt) {
        System.out.print("Datos\t||\t");
        String cadena="Datos\t||\t";
        for (int i = 0; i < grafo.getPalabras().size(); i++) {
            cadena += String.valueOf(grafo.getPalabras().get(i)) + "\t||\t";
            System.out.print(String.valueOf(grafo.getPalabras().get(i)) + "\t||\t");
        }
        System.out.println();
        cadena+="\n";
        for (int i = 0; i < grafo.getTamaño(); i++) {
            cadena += String.valueOf(grafo.getPalabras().get(i)) + "\t||\t";
            System.out.print(String.valueOf(grafo.getPalabras().get(i)) + "\t||\t");
            for (int j = 0; j < grafo.getTamaño(); j++) {
                cadena+=grafo.getElementoGrafoAdya(i, j) + "\t||\t";
                System.out.print(grafo.getElementoGrafoAdya(i, j) + "\t||\t");
            }
            System.out.println();
            cadena+="\n";
        }
        txt.setText(cadena);
    }
            
    public int imprimirVisitados(){
        int cont=0;
        for(int i=0;i<grafo.getVisitados().length;i++){
            if(grafo.getValorVisitados(i)==1){
                System.out.println(grafo.getPalabras().get(i)+", ");
                //System.out.print(i+", ");
                cont++;
            }
        }
        System.out.println();
        return cont;
    }
    
    public void dikestra(int v){
        int costoMinino[] = new int [grafo.getTamaño()];
        int ruta[] = new int [grafo.getTamaño()];
        for(int i=0;i<grafo.getTamaño();i++){
            grafo.setValorVisitados(i, 0);
            costoMinino[i]=grafo.getElementoMatCostos(v, i);
            ruta[i]=i;
        }
        int i=0;
        int w;
        grafo.setValorVisitados(v, 1);
        while(i<grafo.getTamaño()-1){
            w=0;
            while(grafo.getValorVisitados(w)==1){
                w++;
            }
            for(int j=w+1;j<grafo.getTamaño();j++){
                if(grafo.getValorVisitados(j)==0){
                    if(costoMinino[j]<costoMinino[w]){
                        w=j;
                    }
                }
            }
            grafo.setValorVisitados(w, 1);
            i++;
            for(int j=0;j<grafo.getTamaño();j++){
                if(grafo.getValorVisitados(j)==0){
                    int aux = costoMinino[w]+grafo.getElementoMatCostos(w, j);
                    if(aux<costoMinino[j]){
                        costoMinino[j]=aux;
                        ruta[j]=w;
                    }
                }
            }
        }
        for(int k=0;k<ruta.length;k++){
            System.out.println(k+": "+ruta[k]);
        }
    }
}

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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author aux10
 */
public class Metodos {

    private Object modelo;
    private Grafo grafo;

    public DefaultTreeModel construirDiccionario(DefaultMutableTreeNode raiz) {
        DefaultMutableTreeNode diccionario = raiz;
        DefaultTreeModel modelo = new DefaultTreeModel(diccionario);
        Vector<String> words = cargarConPalabras();
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

    public Vector<String> cargarConPalabras() {
        Vector<String> words = new Vector<String>();
        try {
            FileReader fr = new FileReader("src/diccionario.txt");
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
        //ordenarPalabrasPorLetra(words);
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
        Hashtable<Integer, char[]> prueba = new Hashtable<Integer, char[]>();
        int i = 1;
        for (String palabra : words) {
            prueba.put(i, palabra.toCharArray());
            i++;
        }
        instanciarGrafo(i - 1);
        conectarGrafo(prueba);
    }

    public void instanciarGrafo(int n) {
        grafo = new Grafo(n);
    }

    public void conectarGrafo(Hashtable<Integer, char[]> datos) {
        int n = grafo.getTamaño();
        char a[];
        char b[];
        for (int i = 0; i < n - 1; i++) {
            a = datos.get(i + 1);
            for (int j = i + 1; j < n; j++) {
                b = datos.get(j + 1);
                if (evaluarConexiones(a, b)) {
                    grafo.setElementoGrafo(i, j, 1);
                    grafo.setElementoGrafo(i, j, 1);
                }
            }
        }
        imprimirGrafo(datos);
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
        } else /* if(contDiferencias==0 && (aLengt+1>bLengt || bLengt+1>aLengt))*/ {
            return false;
        }
    }

    public void imprimirGrafo(Hashtable<Integer, char[]> datos) {
        System.out.print("Datos\t||\t");
        for (int i = 1; i <= datos.size(); i++) {
            System.out.print(String.valueOf(datos.get(i)) + "\t||\t");
        }
        System.out.println();
        for (int i = 0; i < grafo.getTamaño(); i++) {
            System.out.print(String.valueOf(datos.get(i + 1)) + "\t||\t");
            for (int j = 0; j < grafo.getTamaño(); j++) {
                System.out.print(grafo.getElementoGrafo(i, j) + "\t||\t");
            }
            System.out.println();
        }
    }

}

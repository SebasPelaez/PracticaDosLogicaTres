package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Juan Sebastian Pelaez V. y Juan Esteban Marín G.
 */
public class Metodos {

    private Grafo grafo;
    private int indice;
    private int inicioT;
    private ArrayList<String> trayectos = new ArrayList();
    private int menorPasos = 100000000;

    /**
     * Método para construir el JTree(diccionario que es visualizado en el panel
     * de diccionario)
     *
     * @param raiz El parámetro raiz es la raíz del JTree
     * @param ruta El parámetro ruta define la ruta del archivo diccionario que
     * se utilizará para la construcción y visualización del mismo.
     * @return modelo del Jtree
     */
    public DefaultTreeModel construirDiccionario(DefaultMutableTreeNode raiz, String ruta) {
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

    /**
     * Método que lee el archivo externo del diccionario y almacena cada linea
     * en un vector de palabras.
     *
     * @param ruta El parámetro ruta define la ruta del archivo diccionario.
     * @return Vector con las palabras del diccionario.
     */
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

    /**
     * Método que quita los acentos y caracteres especiales o mayúsculas de la
     * plabra leida del archivo externo del diccionario de plabras.
     *
     * @param palabra El parámetro palabra define la palabra a la cual se le
     * quitarán los acentos o mayúsculas.
     * @return Palabra sin acentos o mayúsculas.
     */
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

    /**
     * Método que ordena alfabéticamente las palabras contenidas en el vector de
     * palabras.
     *
     * @param words El parámetro words define el vector de palabras a ser
     * ordenado.
     */
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

    /**
     * Método que crea un grafo con n vértices.
     *
     * @param n El parámetro n define el tamañano(número de vértices) del grafo.
     */
    public void instanciarGrafo(int n) {
        grafo = new Grafo(n);
    }

    /**
     * Método que conecta los vértices del grafo de acuerdo a las
     * especificaciones dadas en el enunciado.
     *
     * @param datos El parámetro datos define una colección con las plabras que
     * pertenecen al grafo.
     */
    public void conectarGrafo(Hashtable<Integer, char[]> datos) {
        int n = grafo.getTamano();
        char a[];
        char b[];
        for (int i = 0; i < n; i++) {
            a = datos.get(i);
            grafo.setPalabras(String.valueOf(datos.get(i)));
            for (int j = i + 1; j < n; j++) {
                b = datos.get(j);
                if (evaluarConexiones(a, b)) {
                    grafo.setElementoGrafoAdya(i, j, 1);
                    grafo.setElementoGrafoAdya(j, i, 1);
                    grafo.setElementoMatCostos(i, j, 1);
                    grafo.setElementoMatCostos(j, i, 1);
                } else {
                    grafo.setElementoMatCostos(i, j, 99999999);
                    grafo.setElementoMatCostos(j, i, 99999999);
                }
            }
        }
    }

    /**
     * Método que evalua si existe 1 o más cambio entre las plabras parámetro
     * para evaluar si conecta o no sus respectivos vértices
     *
     * @param a El parámetro a define una de las palabras a evaluar
     * @param b El parámetro b define una de la otra palabra a evaluar
     */
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
        } else {
            return false;
        }
    }

    /**
     * Método que calcula todas las rutas posibles para ir de un vèrtice v hacia
     * otro vèrtice w
     *
     * @param v El parámetro v define el vèrtice de partida de todas las
     * posibles trayectoria para ir del vèrtice v hacia el vèrtice w
     * @param w El parámetro w define el vèrtice de llegada de todas las
     * posibles trayectoria para ir del vèrtice v hacìa el vèrtice w
     */
    public void todasTrayectoria(int v, int w, int cola[], JTextArea txt) {
        if (v == w) {
            imprimirCola(cola, indice, txt);//Imprimir la cola
            guardarMenorTrayecto(cola, indice);
            grafo.setValorVisitados(v, 0);
            indice--;//desencolar
        } else {
            for (int i = 0; i < grafo.getTamano(); i++) {
                if (grafo.getElementoGrafoAdya(v, i) == 1 && grafo.getValorVisitados(i) == 0) {
                    grafo.setValorVisitados(v, 1);
                    cola[indice++] = i;//encolar;
                    todasTrayectoria(i, w, cola, txt);
                }
            }
            grafo.setValorVisitados(v, 0);
            indice--;//desencolar
        }
    }

    /**
     * Método que inicializa los valores correspondientes para el llamado del
     * método todasTrayectoria
     *
     * @param inicio El parámetro inicio define el vèrtice de partida de todas
     * las posibles trayectoria para ir del vèrtice v hacia el vèrtice w
     * @param fin El parámetro fin define el vèrtice de llegada de todas las
     * posibles trayectoria para ir del vèrtice v hacìa el vèrtice w
     * @param txt El parámetro txt define el área donde será escritas todas las
     * rutas para su visualización.
     */
    public void trayectorias(int inicio, int fin, JTextArea txt) {
        setInicioT(inicio);
        for (int i = 0; i < grafo.getTamano(); i++) {
            grafo.setValorVisitados(i, 0);
        }
        int cola[] = new int[grafo.getTamano()];
        indice = 0;
        todasTrayectoria(inicio, fin, cola, txt);
    }

    /**
     * Método que imprime la ruta actualmente almacena en la cola en el
     * JTextArea para su posterior visualización.
     *
     * @param cola El parámetro cola define la esctructura que almacena la ruta
     * actual que se tiene guardada para su posterior impresión.
     * @param indice El parámetro indice define el final de la cola.
     * @param txt El parámetro txt define el área donde será escrita la ruta
     * para su visualización.
     */
    public void imprimirCola(int cola[], int indice, JTextArea txt) {
        String cadena = txt.getText();
        if (!cadena.isEmpty()) {
            cadena += "\n";
        }
        String ruta = "*"+grafo.getElementoPalabras(getInicioT());
        for (int i = 0; i < indice; i++) {
            ruta += "-->" + grafo.getElementoPalabras(cola[i]);
        }
        cadena += ruta;
        txt.setText(cadena);
    }

    /**
     * Método que calcula y guarda la menor de las rutas procesadas hasta el
     * momento
     *
     * @param cola El parámetro cola define la esctructura que almacena la ruta
     * actual que se tiene guardada.
     * @param indice El parámetro indice define el final de la cola.
     */
    public void guardarMenorTrayecto(int cola[], int indice) {
        int c = 0;
        String ruta = "";
        for (int i = 0; i < indice; i++) {
            c = c + 1;
        }
        if (c < menorPasos) {
            menorPasos = c;
            trayectos.removeAll(trayectos);
            for (int i = 0; i < indice; i++) {
                ruta += "-->" + grafo.getElementoPalabras(cola[i]);
            }
            trayectos.add(ruta);
        } else if (c == menorPasos) {
            for (int i = 0; i < indice; i++) {
                ruta += "-->" + grafo.getElementoPalabras(cola[i]);
            }
            trayectos.add(ruta);
        }
    }

    /**
     * Método imprime el menor trayecto de todas las rutas posibles
     *
     * @param txt El parámetro txt define el área donde serán escritas las rutas
     * para su visualización.
     * @param verticeUno El parámetro verticeUno el primer vértice de cada
     * trayecto.
     */
    public void imprimirTrayectos(JTextArea txt, String verticeUno) {
        String ruta = "";
        for (int i = 0; i < trayectos.size(); i++) {
            ruta += "*"+verticeUno + trayectos.get(i) + "\n";
        }
        txt.setText(ruta);
    }

    /**
     * Método que genera el archivo para dibujar el grafo
     */
    public void generarArchivoGrafo() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src//archivos//grafo.txt", false);
            pw = new PrintWriter(fichero);
            pw.println("digraph G{");
            pw.println("node [shape=circle];");
            pw.println("node [style=filled];");
            pw.println("node [fillcolor=\"#EEEEEE\"];");
            pw.println("node [color=\"#EEEEEE\"];");
            pw.println("edge [color=\"#31CEF0\", dir=\"none\"];");
            for (int i = 0; i < grafo.getTamano(); i++) {
                for (int j = i + 1; j < grafo.getTamano(); j++) {
                    if (grafo.getElementoGrafoAdya(i, j) == 1) {
                        pw.println(grafo.getElementoPalabras(i) + " -> " + grafo.getElementoPalabras(j) + ";");
                    }
                }
            }
            pw.print("rankdir=LR;}");
            fichero.close();
        } catch (Exception e) {
            System.out.println("Error Creando Archivo");
            System.out.println(e);
        }
    }

    /**
     * Método que genera la imagen del grafo
     */
    public void generarImagen() {
        try {
            ProcessBuilder pbuilder;
            /*
             * Realiza la construccion del comando    
             * en la linea de comandos esto es: 
             * dot -Tpng -o archivo.png archivo.dot
             */
            pbuilder = new ProcessBuilder("Graphviz2.38//bin//dot.exe", "-Tpng", "-o", "src//archivos//grafo.jpg", "src//archivos//grafo.txt");
            pbuilder.redirectErrorStream(true);
            //Ejecuta el proceso
            pbuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método devuelve el número del vértice que contiene dicha palabra.
     *
     * @param palabra El parámetro palabra define la palabra a la cual se le
     * desea obtener el vértice
     */
    public int getVerticePalabra(String palabra) {
        for (int i = 0; i < grafo.getPalabras().size(); i++) {
            if (grafo.getPalabras().get(i).equals(palabra)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método modifica el inicio de cada trayecto.
     *
     * @param i El parámetro i define el vértice que es el inicio del trayecto
     */
    public void setInicioT(int i) {
        inicioT = i;
    }

    /**
     * Método que retorna el inicio de cada trayecto.
     *
     * @return Vértice que es el inicio de cada trayecto.
     */
    public int getInicioT() {
        return inicioT;
    }

}

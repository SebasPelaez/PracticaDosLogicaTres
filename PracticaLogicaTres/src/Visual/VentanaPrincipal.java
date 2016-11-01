/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Logica.Metodos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Lis
 */
public class VentanaPrincipal extends JFrame implements ActionListener {

    private JPanel panelDiccionario;
    private JPanel panelGrafo;
    private JPanel panelOpciones;

    private JButton btnCargarArchivo;
    private JButton btnGenerarRecorridos;

    private JLabel lblVerticeOrigen;
    private JLabel lblVerticeFin;

    private JTextField txtVeticeOrigen;
    private JTextField txtVerticeFin;
    private JTextArea txtGrafoP;

    private JScrollPane scrollDiccionario;
    private JScrollPane scrollGrafoP;

    private DefaultMutableTreeNode raizDiccionario;
    private DefaultTreeModel modelo;
    private JTree tree;

    private Metodos metodos;

    public VentanaPrincipal() {
        setTitle("Practica Dos Lógica 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(815, 610);
        setLayout(null);
        setLocationRelativeTo(null);

        metodos = new Metodos();

        panelDiccionario = new JPanel();
        panelDiccionario.setLayout(null);
        panelDiccionario.setBounds(10, 10, 200, 550);
        panelDiccionario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(panelDiccionario);

        panelGrafo = new JPanel();
        panelGrafo.setLayout(null);
        panelGrafo.setBounds(230, 10, 560, 430);
        panelGrafo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(panelGrafo);

        panelOpciones = new JPanel();
        panelOpciones.setLayout(null);
        panelOpciones.setBounds(230, 450, 560, 110);
        panelOpciones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(panelOpciones);

        btnCargarArchivo = new JButton("Cargar Archivo");
        btnCargarArchivo.setBounds(10, 10, 170, 40);
        btnCargarArchivo.addActionListener(this);
        panelOpciones.add(btnCargarArchivo);

        btnGenerarRecorridos = new JButton("Generar Recorrido");
        btnGenerarRecorridos.setBounds(10, 60, 170, 40);
        btnGenerarRecorridos.addActionListener(this);
        panelOpciones.add(btnGenerarRecorridos);

        lblVerticeOrigen = new JLabel("Palabra origen");
        lblVerticeOrigen.setBounds(220, 30, 120, 25);
        panelOpciones.add(lblVerticeOrigen);

        lblVerticeFin = new JLabel("Palabra final");
        lblVerticeFin.setBounds(220, 60, 120, 25);
        panelOpciones.add(lblVerticeFin);

        txtVeticeOrigen = new JTextField();
        txtVeticeOrigen.setBounds(340, 30, 150, 25);
        panelOpciones.add(txtVeticeOrigen);

        txtVerticeFin = new JTextField();
        txtVerticeFin.setBounds(340, 60, 150, 25);
        panelOpciones.add(txtVerticeFin);

        txtGrafoP = new JTextArea();
        scrollGrafoP = new JScrollPane(txtGrafoP);
        scrollGrafoP.setBounds(10, 10, 530, 400);
        panelGrafo.add(scrollGrafoP);
        
        //cargarDiccionario();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCargarArchivo) {
            JFileChooser jfc = new JFileChooser();
            jfc.setCurrentDirectory(new File("src"));
            jfc.showOpenDialog(this);
            File abre = jfc.getSelectedFile();
            if (abre != null) {
                cargarDiccionario(abre.getAbsolutePath());
                repaint();
            }
        }
        if(e.getSource() == btnGenerarRecorridos){
            metodos.imprimirGrafoAdyacencia(txtGrafoP);
            metodos.dikestra(2);
            metodos.trayectorias(2,3 );
        }
    }

    public void cargarDiccionario(String ruta) {
        raizDiccionario = new DefaultMutableTreeNode("Diccionario");
        modelo = metodos.construirDiccionario(raizDiccionario, ruta); //Manda a la clase metodos para que se encargue de retornar el modelo hecho
        tree = new JTree(modelo);
        scrollDiccionario = new JScrollPane(tree);
        tree.setBounds(7, 10, 185, 530);
        panelDiccionario.add(tree);
    }
}

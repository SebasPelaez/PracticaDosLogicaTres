package Visual;

import Logica.Metodos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Juan Sebastian Pelaez V. y Juan Esteban Marín G.
 */
public class VentanaPrincipal extends JFrame implements ActionListener {

    private JPanel panelDiccionario;
    private JPanel panelGrafo;
    private JPanel panelOpciones;
    private PanelRecorridos panelRoutes;

    private JButton btnCargarArchivo;
    private JButton btnGenerarRecorridos;

    private JLabel lblVerticeOrigen;
    private JLabel lblVerticeFin;

    private JTextField txtVeticeOrigen;
    private JTextField txtVerticeFin;

    private JScrollPane scrollDiccionario;
    private JScrollPane scrImagen;
    
    private DefaultMutableTreeNode raizDiccionario;
    private DefaultTreeModel modelo;
    private JTree tree;

    private Metodos metodos;

    /**
     * Constructor de la ventana principal
     */
    public VentanaPrincipal() {
        setTitle("Practica Dos Lógica 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(595, 610);
        setLayout(null);
        setLocationRelativeTo(null);

        metodos = new Metodos();

        panelRoutes = new PanelRecorridos();
        panelRoutes.setLocation(800, 10);
        getContentPane().add(panelRoutes);

        panelGrafo = new JPanel();
        panelGrafo.setLayout(null);
        panelGrafo.setBounds(10, 10, 560, 430);
        panelGrafo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(panelGrafo);
        
        scrImagen = new JScrollPane();
        scrImagen.setBounds(10, 10, 535, 410);
        scrImagen.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrImagen.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        panelGrafo.add(scrImagen);
        
        scrollDiccionario = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDiccionario.setBounds(10, 10, 200, 550);
        scrollDiccionario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        panelOpciones = new JPanel();
        panelOpciones.setLayout(null);
        panelOpciones.setBounds(10, 450, 560, 110);
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

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCargarArchivo) {
            JFileChooser jfc = new JFileChooser();
            jfc.setCurrentDirectory(new File("src//archivos"));
            jfc.showOpenDialog(this);
            File abre = jfc.getSelectedFile();
            if (abre != null) {
                /*Reedimensionando la ventana*/
                setSize(1025, 610);
                panelGrafo.setBounds(230, 10, 560, 430);
                panelOpciones.setBounds(230, 450, 560, 110);
                setLocationRelativeTo(null);
                /*Reedimensionando la ventana*/
                cargarDiccionario(abre.getAbsolutePath());
                
                metodos.generarArchivoGrafo();
                metodos.generarImagen();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                colocarImagenPanel();
                colocarImagenPanel();
            }

        }
        if (e.getSource() == btnGenerarRecorridos) {
            if (!"".equals(txtVeticeOrigen.getText()) && !"".equals(txtVerticeFin.getText())) {
                String inicio = txtVeticeOrigen.getText().toLowerCase();
                String fin = txtVerticeFin.getText().toLowerCase();
                int vi = metodos.getVerticePalabra(inicio);
                int vf = metodos.getVerticePalabra(fin);
                if (vi != -1 && vf != -1) {
                    panelRoutes.getTxtAllRoutes().setText("");
                    panelRoutes.getTxtShortRoutes().setText("");
                    metodos.trayectorias(vi, vf, panelRoutes.getTxtAllRoutes());
                    metodos.imprimirTrayectos(panelRoutes.getTxtShortRoutes(), txtVeticeOrigen.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Uno de las palabras no está en el grafo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor llene los campos de 'Palabra origen' y 'Palabra final'");
            }
        }
    }

    /**
     * Método que se encarga de construir el diccionario y añadirlo al panel de
     * visualización del diccionario
     *
     * @param ruta El parámetro ruta define la ruta del archivo que contiene el
     * diccionario
     */
    public void cargarDiccionario(String ruta) {
        raizDiccionario = new DefaultMutableTreeNode("Diccionario");
        modelo = metodos.construirDiccionario(raizDiccionario, ruta); //Manda a la clase metodos para que se encargue de retornar el modelo hecho
        tree = new JTree(modelo);
        scrollDiccionario.setViewportView(tree);
        tree.setBounds(7, 10, 185, 530);
        getContentPane().add(scrollDiccionario);
    }

    /**
     * Método que se encarga de añadir la imagen del grafo al panel de
     * visualización del grafo.
     */
    public void colocarImagenPanel() {
        ImageIcon icnImagen = new ImageIcon("src//archivos//grafo.jpg");
        icnImagen.getImage().flush();
        JLabel lblImagen = new JLabel(icnImagen);
        scrImagen.setViewportView(lblImagen);
    }
}

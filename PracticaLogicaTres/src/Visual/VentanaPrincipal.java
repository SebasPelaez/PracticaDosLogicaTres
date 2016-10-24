/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lis
 */
public class VentanaPrincipal extends JFrame {
    private JPanel panelDiccionario;
    private JPanel panelGrafo;
    private JPanel panelResultados;
    
    public VentanaPrincipal(){
        setTitle("Practica Dos Lógica 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        
        panelDiccionario = new JPanel();
        panelDiccionario.setLayout(null);
        panelDiccionario.setBounds(10, 10, 300, 500);
        panelDiccionario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(panelDiccionario);
        
        panelGrafo = new JPanel();
        panelGrafo.setLayout(null);
        panelGrafo.setBounds(350, 10, 400, 400);
        panelGrafo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(panelGrafo);
        
        panelResultados = new JPanel();
        panelResultados.setLayout(null);
        panelResultados.setBounds(350, 450, 100, 100);
        panelResultados.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(panelResultados);
        
        setVisible(true);
    }
}

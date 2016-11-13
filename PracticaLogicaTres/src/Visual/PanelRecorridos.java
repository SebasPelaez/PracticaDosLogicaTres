/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Juan Sebastian Pelaez V. y Juan Esteban Marín G.
 */
public class PanelRecorridos extends JPanel {

    private JTextArea txtAllRoutes;
    private JScrollPane scrAllRoutes;
    private JLabel lblAllRoutes;
    private JTextArea txtShortRoutes;
    private JScrollPane scrShortRoutes;
    private JLabel lblShortRoutes;

    /**
     * Constructor del panel de visualización de los recorridos
     */
    public PanelRecorridos() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(null);
        setSize(200, 550);

        txtAllRoutes = new JTextArea();
        scrAllRoutes = new JScrollPane(txtAllRoutes);
        scrAllRoutes.setBounds(10, 30, 180, 235);
        txtAllRoutes.setWrapStyleWord(true);
        txtAllRoutes.setLineWrap(true);
        txtAllRoutes.setEditable(false);
        txtAllRoutes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrAllRoutes);

        txtShortRoutes = new JTextArea();
        scrShortRoutes = new JScrollPane(txtShortRoutes);
        scrShortRoutes.setBounds(10, 305, 180, 235);
        txtShortRoutes.setWrapStyleWord(true);
        txtShortRoutes.setLineWrap(true);
        txtShortRoutes.setEditable(false);
        txtShortRoutes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrShortRoutes);

        lblAllRoutes = new JLabel("Todas las rutas");
        lblAllRoutes.setBounds(10, 10, 100, 25);
        add(lblAllRoutes);

        lblShortRoutes = new JLabel("Rutas más cortas");
        lblShortRoutes.setBounds(10, 285, 120, 25);
        add(lblShortRoutes);

    }

    /**
     * Método que obtiene el panel de todas las rutas
     *
     * @return Panel de todas las rutas
     */
    public JTextArea getTxtAllRoutes() {
        return txtAllRoutes;
    }

    /**
     * Método que obtiene el panel de la ruta más corta
     *
     * @return Panel de la ruta más corta
     */
    public JTextArea getTxtShortRoutes() {
        return txtShortRoutes;
    }
}

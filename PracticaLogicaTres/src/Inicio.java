
import Visual.VentanaPrincipal;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.NebulaSkin;
import org.jvnet.substance.skin.SubstanceNebulaLookAndFeel;

/**
 * 
 * @author Juan Sebastian Pelaez V. y Juan Esteban Marín G. 
 */
public class Inicio {
    /**
    *Método main que marca la entrada al progama.
    *
    */
    public static void main(String[] args) throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel.setSkin(new NebulaSkin());
		SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel");
		try {			
			UIManager.setLookAndFeel(new SubstanceNebulaLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
		}	
        new VentanaPrincipal();
    }  
}

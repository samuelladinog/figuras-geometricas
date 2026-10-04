package geometria.vista;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/** Punto de entrada de la interfaz gráfica. Debe ejecutarse desde IntelliJ (usa el GUI Designer). */
public class MainInterfaz {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            JFrame marco = new JFrame("Manejo de Figuras Geométricas");
            marco.setContentPane(ventana.getPanelPrincipal());
            marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            marco.pack();
            marco.setLocationRelativeTo(null);
            marco.setVisible(true);
        });
    }
}

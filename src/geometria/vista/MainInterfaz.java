package geometria.vista;

import javax.swing.SwingUtilities;

public class MainInterfaz {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
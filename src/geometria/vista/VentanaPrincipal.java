package geometria.vista;

import geometria.modelo.Figura;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Ventana principal. Los componentes se diseñan visualmente (arrastrando y soltando)
 * en VentanaPrincipal.form con el GUI Designer de IntelliJ.
 */
public class VentanaPrincipal {

    private JPanel panelPrincipal;
    private JComboBox<TipoFigura> comboTipo;
    private JTextField campoX;
    private JTextField campoY;
    private JLabel etiquetaDimension1;
    private JLabel etiquetaDimension2;
    private JLabel etiquetaDimension3;
    private JTextField campoDimension1;
    private JTextField campoDimension2;
    private JTextField campoDimension3;
    private JButton botonCrear;
    private JList<Figura> listaFiguras;
    private JTextField campoDx;
    private JTextField campoDy;
    private JTextField campoFactor;
    private JButton botonDesplazar;
    private JButton botonEscalar;
    private JButton botonComparar;
    private JButton botonMostrar;
    private JButton botonEliminar;
    private JEditorPane panelInformacion;

    public VentanaPrincipal() {
        comboTipo.setModel(new DefaultComboBoxModel<>(TipoFigura.values()));
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
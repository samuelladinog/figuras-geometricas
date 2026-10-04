package geometria.vista;

import geometria.modelo.Figura;
import geometria.modelo.figuras.Circulo;
import geometria.modelo.figuras.Cuadrilatero;
import geometria.modelo.figuras.Pentagono;
import geometria.modelo.figuras.Triangulo;
import geometria.presentacion.InformacionFigura;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.util.List;

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

    private final DefaultListModel<Figura> modeloLista =
            new DefaultListModel<>();

    public VentanaPrincipal() {

        comboTipo.setModel(
                new DefaultComboBoxModel<>(TipoFigura.values())
        );

        listaFiguras.setModel(modeloLista);
        listaFiguras.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        );

        actualizarEtiquetas();

        comboTipo.addActionListener(e -> actualizarEtiquetas());

        botonCrear.addActionListener(e -> crearFigura());

        botonDesplazar.addActionListener(e -> desplazarFigura());

        botonEscalar.addActionListener(e -> escalarFigura());

        botonComparar.addActionListener(e -> compararFiguras());

        botonMostrar.addActionListener(e -> mostrarFigura());

        botonEliminar.addActionListener(e -> eliminarFigura());
    }

    private void actualizarEtiquetas() {

        TipoFigura tipo = (TipoFigura) comboTipo.getSelectedItem();

        if (tipo == null) {
            return;
        }

        etiquetaDimension1.setVisible(true);
        campoDimension1.setVisible(true);

        etiquetaDimension2.setVisible(false);
        campoDimension2.setVisible(false);

        etiquetaDimension3.setVisible(false);
        campoDimension3.setVisible(false);

        switch (tipo) {

            case CIRCULO:
                etiquetaDimension1.setText("Radio:");
                break;

            case TRIANGULO:
                etiquetaDimension1.setText("Lado 1:");
                etiquetaDimension2.setText("Lado 2:");
                etiquetaDimension3.setText("Lado 3:");

                etiquetaDimension2.setVisible(true);
                campoDimension2.setVisible(true);

                etiquetaDimension3.setVisible(true);
                campoDimension3.setVisible(true);
                break;

            case CUADRILATERO:
                etiquetaDimension1.setText("Ancho:");
                etiquetaDimension2.setText("Alto:");

                etiquetaDimension2.setVisible(true);
                campoDimension2.setVisible(true);
                break;

            case PENTAGONO:
                etiquetaDimension1.setText("Lado:");
                break;
        }
    }

    private void crearFigura() {

        try {

            double x = Double.parseDouble(campoX.getText());
            double y = Double.parseDouble(campoY.getText());

            TipoFigura tipo =
                    (TipoFigura) comboTipo.getSelectedItem();

            Figura figura;

            switch (tipo) {

                case CIRCULO:
                    figura = new Circulo(
                            x,
                            y,
                            Double.parseDouble(
                                    campoDimension1.getText()
                            )
                    );
                    break;

                case TRIANGULO:
                    figura = new Triangulo(
                            x,
                            y,
                            Double.parseDouble(
                                    campoDimension1.getText()
                            ),
                            Double.parseDouble(
                                    campoDimension2.getText()
                            ),
                            Double.parseDouble(
                                    campoDimension3.getText()
                            )
                    );
                    break;

                case CUADRILATERO:
                    figura = new Cuadrilatero(
                            x,
                            y,
                            Double.parseDouble(
                                    campoDimension1.getText()
                            ),
                            Double.parseDouble(
                                    campoDimension2.getText()
                            )
                    );
                    break;

                case PENTAGONO:
                    figura = new Pentagono(
                            x,
                            y,
                            Double.parseDouble(
                                    campoDimension1.getText()
                            )
                    );
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Tipo de figura no válido"
                    );
            }

            modeloLista.addElement(figura);

            mostrarInformacion(figura);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Ingrese valores numéricos válidos."
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    e.getMessage()
            );
        }
    }

    private void desplazarFigura() {

        Figura figura = listaFiguras.getSelectedValue();

        if (figura == null) {
            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Seleccione una figura."
            );
            return;
        }

        try {

            double dx = Double.parseDouble(campoDx.getText());
            double dy = Double.parseDouble(campoDy.getText());

            figura.desplazar(dx, dy);

            listaFiguras.repaint();

            mostrarInformacion(figura);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Ingrese valores numéricos válidos."
            );
        }
    }

    private void escalarFigura() {

        Figura figura = listaFiguras.getSelectedValue();

        if (figura == null) {
            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Seleccione una figura."
            );
            return;
        }

        try {

            double factor =
                    Double.parseDouble(campoFactor.getText());

            figura.escalar(factor);

            listaFiguras.repaint();

            mostrarInformacion(figura);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Ingrese un factor numérico válido."
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    e.getMessage()
            );
        }
    }

    private void compararFiguras() {

        List<Figura> seleccionadas =
                listaFiguras.getSelectedValuesList();

        if (seleccionadas.size() != 2) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Seleccione exactamente 2 figuras.\n"
                            + "Use Ctrl + clic para seleccionar dos."
            );

            return;
        }

        try {

            Figura figura1 = seleccionadas.get(0);
            Figura figura2 = seleccionadas.get(1);

            int resultado =
                    figura1.compararArea(figura2);

            String mensaje;

            if (resultado > 0) {

                mensaje = figura1.obtenerTipo()
                        + " es mayor que "
                        + figura2.obtenerTipo();

            } else if (resultado < 0) {

                mensaje = figura1.obtenerTipo()
                        + " es menor que "
                        + figura2.obtenerTipo();

            } else {

                mensaje = "Las figuras tienen la misma dimensión.";
            }

            panelInformacion.setContentType("text/html");

            panelInformacion.setText(
                    "<html><h2>Comparación</h2>"
                            + mensaje
                            + "</html>"
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    e.getMessage()
            );
        }
    }

    private void mostrarFigura() {

        Figura figura = listaFiguras.getSelectedValue();

        if (figura == null) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Seleccione una figura."
            );

            return;
        }

        mostrarInformacion(figura);
    }

    private void eliminarFigura() {

        Figura figura = listaFiguras.getSelectedValue();

        if (figura == null) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Seleccione una figura."
            );

            return;
        }

        modeloLista.removeElement(figura);

        panelInformacion.setText("");
    }

    private void mostrarInformacion(Figura figura) {

        InformacionFigura informacion =
                new InformacionFigura(figura);

        panelInformacion.setContentType("text/html");

        panelInformacion.setText(
                informacion.toStringHTML()
        );
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}

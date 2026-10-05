package geometria.vista;

import geometria.modelo.Figura;
import geometria.presentacion.InformacionFigura;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.util.List;
import java.util.Locale;

/**
 * Lógica de la ventana principal. Los componentes se diseñan visualmente
 * (arrastrando y soltando) en VentanaPrincipal.form con el GUI Designer de IntelliJ;
 * aquí solo se programan los eventos.
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

    private final DefaultListModel<Figura> modeloFiguras = new DefaultListModel<>();
    private final JLabel[] etiquetasDimension;
    private final JTextField[] camposDimension;

    public VentanaPrincipal() {
        etiquetasDimension = new JLabel[]{etiquetaDimension1, etiquetaDimension2, etiquetaDimension3};
        camposDimension = new JTextField[]{campoDimension1, campoDimension2, campoDimension3};

        comboTipo.setModel(new DefaultComboBoxModel<>(TipoFigura.values()));
        comboTipo.addActionListener(evento -> actualizarCamposDimension());
        listaFiguras.setModel(modeloFiguras);
        listaFiguras.setCellRenderer(new RenderizadorFigura());

        botonCrear.addActionListener(evento -> ejecutar(this::crearFigura));
        botonMostrar.addActionListener(evento -> ejecutar(this::mostrarInformacion));
        botonDesplazar.addActionListener(evento -> ejecutar(this::desplazarSeleccionadas));
        botonEscalar.addActionListener(evento -> ejecutar(this::escalarSeleccionadas));
        botonComparar.addActionListener(evento -> ejecutar(this::compararSeleccionadas));
        botonEliminar.addActionListener(evento -> ejecutar(this::eliminarSeleccionadas));

        actualizarCamposDimension();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    private void actualizarCamposDimension() {
        String[] nombres = tipoSeleccionado().getNombresDimensiones();
        for (int i = 0; i < camposDimension.length; i++) {
            boolean usado = i < nombres.length;
            etiquetasDimension[i].setVisible(usado);
            camposDimension[i].setVisible(usado);
            if (usado) {
                etiquetasDimension[i].setText(nombres[i] + ":");
            }
        }
        panelPrincipal.revalidate();
    }

    private void crearFigura() {
        TipoFigura tipo = tipoSeleccionado();
        String[] nombres = tipo.getNombresDimensiones();
        double[] dimensiones = new double[nombres.length];
        for (int i = 0; i < nombres.length; i++) {
            dimensiones[i] = leerNumero(camposDimension[i], nombres[i]);
        }
        Figura figura = tipo.crear(leerNumero(campoX, "X"), leerNumero(campoY, "Y"), dimensiones);
        modeloFiguras.addElement(figura);
        listaFiguras.setSelectedIndex(modeloFiguras.size() - 1);
        mostrarHtml("<p><b>Figura creada correctamente.</b></p>" + new InformacionFigura(figura).toStringHTML());
    }

    private void mostrarInformacion() {
        StringBuilder html = new StringBuilder();
        for (Figura figura : obtenerSeleccionadas()) {
            html.append(new InformacionFigura(figura).toStringHTML());
        }
        mostrarHtml(html.toString());
    }

    private void desplazarSeleccionadas() {
        double dx = leerNumero(campoDx, "Desplazar X");
        double dy = leerNumero(campoDy, "Desplazar Y");
        for (Figura figura : obtenerSeleccionadas()) {
            figura.desplazar(dx, dy);
        }
        listaFiguras.repaint();
        mostrarInformacion();
    }

    private void escalarSeleccionadas() {
        double factor = leerNumero(campoFactor, "Factor de escala");
        for (Figura figura : obtenerSeleccionadas()) {
            figura.escalar(factor);
        }
        listaFiguras.repaint();
        mostrarInformacion();
    }

    private void compararSeleccionadas() {
        List<Figura> seleccionadas = obtenerSeleccionadas();
        if (seleccionadas.size() != 2) {
            throw new IllegalArgumentException("Seleccione exactamente dos figuras (Ctrl + clic) para comparar");
        }
        Figura primera = seleccionadas.get(0);
        Figura segunda = seleccionadas.get(1);
        int resultado = primera.compararArea(segunda);
        String relacion = resultado > 0 ? "MAYOR que" : resultado < 0 ? "MENOR que" : "IGUAL a";
        mostrarHtml(String.format(Locale.ROOT,
                "<h3>Comparación</h3><p>%s (dimensionar %.2f) es <b>%s</b> %s (dimensionar %.2f)</p>",
                primera.getClass().getSimpleName(), primera.dimensionar(), relacion,
                segunda.getClass().getSimpleName(), segunda.dimensionar()));
    }

    private void eliminarSeleccionadas() {
        for (Figura figura : obtenerSeleccionadas()) {
            modeloFiguras.removeElement(figura);
        }
        mostrarHtml("<p>Figuras eliminadas.</p>");
    }

    private List<Figura> obtenerSeleccionadas() {
        List<Figura> seleccionadas = listaFiguras.getSelectedValuesList();
        if (seleccionadas.isEmpty()) {
            throw new IllegalArgumentException("Seleccione al menos una figura de la lista");
        }
        return seleccionadas;
    }

    private TipoFigura tipoSeleccionado() {
        return (TipoFigura) comboTipo.getSelectedItem();
    }

    private void mostrarHtml(String contenido) {
        panelInformacion.setText("<html><body>" + contenido + "</body></html>");
        panelInformacion.setCaretPosition(0);
    }

    private static double leerNumero(JTextField campo, String nombreCampo) {
        try {
            return Double.parseDouble(campo.getText().trim().replace(',', '.'));
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("El campo '" + nombreCampo + "' debe ser un número");
        }
    }

    private void ejecutar(Runnable accion) {
        try {
            accion.run();
        } catch (IllegalArgumentException error) {
            JOptionPane.showMessageDialog(panelPrincipal, error.getMessage(), "Dato no válido",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Muestra cada figura de la lista como "#n Nombre | área | dimensionar". */
    private static class RenderizadorFigura extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> lista, Object valor, int indice,
                                                      boolean seleccionado, boolean conFoco) {
            Figura figura = (Figura) valor;
            String texto = String.format(Locale.ROOT, "#%d  %s  |  área %.2f  |  dimensionar %.2f",
                    indice + 1, figura.getClass().getSimpleName(), figura.calcularArea(), figura.dimensionar());
            return super.getListCellRendererComponent(lista, texto, indice, seleccionado, conFoco);
        }
    }
}

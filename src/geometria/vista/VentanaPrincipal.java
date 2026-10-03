package geometria.vista;

import geometria.modelo.Figura;
import geometria.modelo.figuras.Circulo;
import geometria.modelo.figuras.Cuadrilatero;
import geometria.modelo.figuras.Pentagono;
import geometria.modelo.figuras.Triangulo;
import geometria.presentacion.InformacionFigura;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JComboBox<TipoFigura> comboTipo;
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtDimension1;
    private JTextField txtDimension2;
    private JTextField txtDimension3;
    private JTextArea areaResultado;

    private Figura figuraActual;

    public VentanaPrincipal() {
        setTitle("Figuras Geométricas");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        crearInterfaz();
    }

    private void crearInterfaz() {

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(0, 2, 5, 5));

        comboTipo = new JComboBox<>(TipoFigura.values());

        txtX = new JTextField("0");
        txtY = new JTextField("0");
        txtDimension1 = new JTextField("5");
        txtDimension2 = new JTextField("5");
        txtDimension3 = new JTextField("5");

        panelDatos.add(new JLabel("Tipo de figura:"));
        panelDatos.add(comboTipo);

        panelDatos.add(new JLabel("Coordenada X:"));
        panelDatos.add(txtX);

        panelDatos.add(new JLabel("Coordenada Y:"));
        panelDatos.add(txtY);

        panelDatos.add(new JLabel("Dimensión 1:"));
        panelDatos.add(txtDimension1);

        panelDatos.add(new JLabel("Dimensión 2:"));
        panelDatos.add(txtDimension2);

        panelDatos.add(new JLabel("Dimensión 3:"));
        panelDatos.add(txtDimension3);

        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnCrear = new JButton("Crear");
        JButton btnDesplazar = new JButton("Desplazar");
        JButton btnEscalar = new JButton("Escalar");
        JButton btnComparar = new JButton("Comparar");
        JButton btnMostrar = new JButton("Mostrar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnDesplazar);
        panelBotones.add(btnEscalar);
        panelBotones.add(btnComparar);
        panelBotones.add(btnMostrar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaResultado);

        panelPrincipal.add(panelDatos, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(scroll, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);

        btnCrear.addActionListener(e -> crearFigura());
        btnDesplazar.addActionListener(e -> desplazarFigura());
        btnEscalar.addActionListener(e -> escalarFigura());
        btnComparar.addActionListener(e -> compararFigura());
        btnMostrar.addActionListener(e -> mostrarFigura());
    }

    private void crearFigura() {

        try {
            double x = Double.parseDouble(txtX.getText());
            double y = Double.parseDouble(txtY.getText());
            double d1 = Double.parseDouble(txtDimension1.getText());
            double d2 = Double.parseDouble(txtDimension2.getText());
            double d3 = Double.parseDouble(txtDimension3.getText());

            TipoFigura tipo = (TipoFigura) comboTipo.getSelectedItem();

            switch (tipo) {
                case CIRCULO:
                    figuraActual = new Circulo(x, y, d1);
                    break;

                case TRIANGULO:
                    figuraActual = new Triangulo(x, y, d1, d2, d3);
                    break;

                case CUADRILATERO:
                    figuraActual = new Cuadrilatero(x, y, d1, d2);
                    break;

                case PENTAGONO:
                    figuraActual = new Pentagono(x, y, d1);
                    break;
            }

            mostrarMensaje("Figura creada correctamente.");

        } catch (Exception ex) {
            mostrarMensaje("Error: " + ex.getMessage());
        }
    }

    private void desplazarFigura() {

        if (figuraActual == null) {
            mostrarMensaje("Primero debes crear una figura.");
            return;
        }

        try {
            double dx = Double.parseDouble(txtDimension1.getText());
            double dy = Double.parseDouble(txtDimension2.getText());

            figuraActual.desplazar(dx, dy);

            mostrarMensaje("Figura desplazada correctamente.");

        } catch (Exception ex) {
            mostrarMensaje("Error: " + ex.getMessage());
        }
    }

    private void escalarFigura() {

        if (figuraActual == null) {
            mostrarMensaje("Primero debes crear una figura.");
            return;
        }

        try {
            double factor = Double.parseDouble(txtDimension1.getText());

            figuraActual.escalar(factor);

            mostrarMensaje("Figura escalada correctamente.");

        } catch (Exception ex) {
            mostrarMensaje("Error: " + ex.getMessage());
        }
    }

    private void compararFigura() {

        if (figuraActual == null) {
            mostrarMensaje("Primero debes crear una figura.");
            return;
        }

        try {
            double x = Double.parseDouble(txtX.getText());
            double y = Double.parseDouble(txtY.getText());
            double d1 = Double.parseDouble(txtDimension1.getText());
            double d2 = Double.parseDouble(txtDimension2.getText());
            double d3 = Double.parseDouble(txtDimension3.getText());

            Figura otra;

            switch ((TipoFigura) comboTipo.getSelectedItem()) {
                case CIRCULO:
                    otra = new Circulo(x, y, d1);
                    break;

                case TRIANGULO:
                    otra = new Triangulo(x, y, d1, d2, d3);
                    break;

                case CUADRILATERO:
                    otra = new Cuadrilatero(x, y, d1, d2);
                    break;

                case PENTAGONO:
                    otra = new Pentagono(x, y, d1);
                    break;

                default:
                    return;
            }

            int resultado = figuraActual.compararArea(otra);

            if (resultado > 0) {
                mostrarMensaje("La figura actual tiene mayor dimensión.");
            } else if (resultado < 0) {
                mostrarMensaje("La segunda figura tiene mayor dimensión.");
            } else {
                mostrarMensaje("Las figuras tienen la misma dimensión.");
            }

        } catch (Exception ex) {
            mostrarMensaje("Error: " + ex.getMessage());
        }
    }

    private void mostrarFigura() {

        if (figuraActual == null) {
            mostrarMensaje("Primero debes crear una figura.");
            return;
        }

        InformacionFigura informacion = new InformacionFigura(figuraActual);
        areaResultado.setText(informacion.toString());
    }

    private void mostrarMensaje(String mensaje) {
        areaResultado.setText(mensaje);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
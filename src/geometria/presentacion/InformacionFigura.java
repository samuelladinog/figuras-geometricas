package geometria.presentacion;

import geometria.modelo.Figura;

/**
 * Presenta la información de cualquier figura geométrica.
 * Se encarga únicamente de la presentación de los datos.
 */
public class InformacionFigura {

    private final Figura figura;

    public InformacionFigura(Figura figura) {
        if (figura == null) {
            throw new IllegalArgumentException("La figura no puede ser nula");
        }
        this.figura = figura;
    }

    @Override
    public String toString() {
        return "Tipo: " + figura.obtenerTipo()
                + "\nCoordenadas: (" + figura.getX() + ", " + figura.getY() + ")"
                + "\nEscala: " + figura.getEscala()
                + "\nÁrea: " + figura.calcularArea()
                + "\nPerímetro: " + figura.calcularPerimetro()
                + "\nDimensión: " + figura.dimensionar();
    }

    public String toStringHTML() {
        return "<html>"
                + "<h2>" + figura.obtenerTipo() + "</h2>"
                + "<b>Coordenadas:</b> (" + figura.getX() + ", " + figura.getY() + ")<br>"
                + "<b>Escala:</b> " + figura.getEscala() + "<br>"
                + "<b>Área:</b> " + figura.calcularArea() + "<br>"
                + "<b>Perímetro:</b> " + figura.calcularPerimetro() + "<br>"
                + "<b>Dimensión:</b> " + figura.dimensionar()
                + "</html>";
    }
}

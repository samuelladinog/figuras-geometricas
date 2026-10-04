package geometria.presentacion;

import geometria.modelo.Figura;
import geometria.modelo.figuras.Circulo;
import geometria.modelo.figuras.Triangulo;
import geometria.modelo.figuras.Cuadrilatero;
import geometria.modelo.figuras.Pentagono;

public class InformacionFigura {

    private final Figura figura;
    private final String tipo;
    private final double x;
    private final double y;
    private final double escala;
    private final double area;
    private final double perimetro;

    public InformacionFigura(Figura figura) {
        if (figura == null) {
            throw new IllegalArgumentException("La figura no puede ser nula");
        }

        this.figura = figura;
        this.tipo = figura.obtenerTipo();
        this.x = figura.getX();
        this.y = figura.getY();
        this.escala = figura.getEscala();
        this.area = figura.calcularArea();
        this.perimetro = figura.calcularPerimetro();
    }

    public Figura getFigura() {
        return figura;
    }

    public String getTipo() {
        return tipo;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getEscala() {
        return escala;
    }

    public double getArea() {
        return area;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public String obtenerDimensiones() {

        if (figura instanceof Circulo) {
            Circulo circulo = (Circulo) figura;

            return "Radio: " + circulo.getRadio();
        }

        if (figura instanceof Triangulo) {
            Triangulo triangulo = (Triangulo) figura;

            return "Lados: figura triangular";
        }

        if (figura instanceof Cuadrilatero) {
            Cuadrilatero cuadrilatero = (Cuadrilatero) figura;

            return "Ancho: " + cuadrilatero.getAncho()
                    + "\nAlto: " + cuadrilatero.getAlto();
        }

        if (figura instanceof Pentagono) {
            Pentagono pentagono = (Pentagono) figura;

            return "Lado: " + pentagono.getLado();
        }

        return "Dimensiones no disponibles";
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo
                + "\nCoordenadas: (" + x + ", " + y + ")"
                + "\nEscala: " + escala
                + "\nÁrea: " + area
                + "\nPerímetro: " + perimetro
                + "\n" + obtenerDimensiones();
    }

    public String toStringHTML() {
        return "<html>"
                + "<h2>" + tipo + "</h2>"
                + "<b>Coordenadas:</b> (" + x + ", " + y + ")<br>"
                + "<b>Escala:</b> " + escala + "<br>"
                + "<b>Área:</b> " + area + "<br>"
                + "<b>Perímetro:</b> " + perimetro + "<br>"
                + "<b>Dimensiones:</b><br>"
                + obtenerDimensiones().replace("\n", "<br>")
                + "</html>";
    }
}

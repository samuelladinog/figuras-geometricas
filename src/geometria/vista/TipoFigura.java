package geometria.vista;

import geometria.modelo.Figura;
import geometria.modelo.figuras.Circulo;
import geometria.modelo.figuras.Cuadrilatero;
import geometria.modelo.figuras.Pentagono;
import geometria.modelo.figuras.Triangulo;

/**
 * Figuras que se pueden crear desde la interfaz. Para soportar una figura nueva
 * solo se agrega una constante aquí; la ventana no se modifica.
 */
public enum TipoFigura {

    CIRCULO("Circulo", new String[]{"Radio"},
            (x, y, d) -> new Circulo(x, y, d[0])),
    TRIANGULO("Triangulo", new String[]{"Lado 1", "Lado 2", "Lado 3"},
            (x, y, d) -> new Triangulo(x, y, d[0], d[1], d[2])),
    CUADRILATERO("Cuadrilatero", new String[]{"Ancho", "Alto"},
            (x, y, d) -> new Cuadrilatero(x, y, d[0], d[1])),
    PENTAGONO("Pentagono", new String[]{"Lado"},
            (x, y, d) -> new Pentagono(x, y, d[0]));

    /** Construye la figura a partir de su centro y sus dimensiones. */
    public interface Fabrica {
        Figura crear(double x, double y, double[] dimensiones);
    }

    private final String nombre;
    private final String[] nombresDimensiones;
    private final Fabrica fabrica;

    TipoFigura(String nombre, String[] nombresDimensiones, Fabrica fabrica) {
        this.nombre = nombre;
        this.nombresDimensiones = nombresDimensiones;
        this.fabrica = fabrica;
    }

    public String[] getNombresDimensiones() {
        return nombresDimensiones.clone();
    }

    public Figura crear(double x, double y, double[] dimensiones) {
        return fabrica.crear(x, y, dimensiones);
    }

    @Override
    public String toString() {
        return nombre;
    }
}

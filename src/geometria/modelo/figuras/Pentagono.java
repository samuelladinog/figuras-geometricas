package geometria.modelo.figuras;

import geometria.modelo.Figura;
import geometria.modelo.Punto;

/** Pentágono regular con centro en (x, y). Dimensionar = suma de las X de sus vértices. */
public class Pentagono extends Figura {

    private static final int CANTIDAD_LADOS = 5;
    private static final double ANGULO_ENTRE_VERTICES = 360.0 / CANTIDAD_LADOS;

    private final double lado;

    public Pentagono(double x, double y, double lado) {
        super(x, y);
        this.lado = validarPositivo(lado, "lado");
    }

    /** Área = 5·l² / (4·tan 36°). */
    @Override
    public double calcularArea() {
        double ladoActual = lado * escala;
        return CANTIDAD_LADOS * ladoActual * ladoActual / (4 * Math.tan(Math.PI / CANTIDAD_LADOS));
    }

    @Override
    public double calcularPerimetro() {
        return CANTIDAD_LADOS * lado * escala;
    }

    @Override
    public double dimensionar() {
        double sumaX = 0;
        for (Punto vertice : obtenerVertices()) {
            sumaX += vertice.getX();
        }
        return sumaX;
    }

    /** Calcula los cinco vértices; el primero queda justo encima del centro. */
    public Punto[] obtenerVertices() {
        double radioCircunscrito = lado * escala / (2 * Math.sin(Math.PI / CANTIDAD_LADOS));
        Punto[] vertices = new Punto[CANTIDAD_LADOS];
        for (int i = 0; i < CANTIDAD_LADOS; i++) {
            Punto vertice = new Punto(0, radioCircunscrito);
            vertice.rotar(i * ANGULO_ENTRE_VERTICES);
            vertice.trasladar(x, y);
            vertices[i] = vertice;
        }
        return vertices;
    }

    public double getLado() {
        return lado;
    }
}

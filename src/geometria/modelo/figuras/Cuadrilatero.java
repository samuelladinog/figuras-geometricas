package geometria.modelo.figuras;

import geometria.modelo.Figura;
import geometria.modelo.Punto;

/**
 * Cuadrilátero rectangular con centro en (x, y).
 * Dimensionar = suma de las distancias de sus cuatro esquinas al origen (0, 0).
 */
public class Cuadrilatero extends Figura {

    private final double ancho;
    private final double alto;

    public Cuadrilatero(double x, double y, double ancho, double alto) {
        super(x, y);
        this.ancho = validarPositivo(ancho, "ancho");
        this.alto = validarPositivo(alto, "alto");
    }

    @Override
    public double calcularArea() {
        return (ancho * escala) * (alto * escala);
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (ancho + alto) * escala;
    }

    @Override
    public double dimensionar() {
        double mitadAncho = ancho * escala / 2;
        double mitadAlto = alto * escala / 2;
        Punto origen = new Punto();
        Punto[] esquinas = {
                new Punto(x - mitadAncho, y - mitadAlto),
                new Punto(x + mitadAncho, y - mitadAlto),
                new Punto(x + mitadAncho, y + mitadAlto),
                new Punto(x - mitadAncho, y + mitadAlto)
        };
        double sumaDistancias = 0;
        for (Punto esquina : esquinas) {
            sumaDistancias += esquina.distancia(origen);
        }
        return sumaDistancias;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }
}

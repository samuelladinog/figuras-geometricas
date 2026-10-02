package geometria.modelo;

/** Punto del plano cartesiano. Representa las coordenadas de una figura. */
public class Punto {

    private double x;
    private double y;

    /** Crea el punto en el origen (0, 0). */
    public Punto() {
        this(0, 0);
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distancia(Punto otro) {
        return Math.hypot(x - otro.x, y - otro.y);
    }

    public void trasladar(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /** Rota el punto alrededor del origen, en sentido antihorario. */
    public void rotar(double grados) {
        double radianes = Math.toRadians(grados);
        double nuevoX = x * Math.cos(radianes) - y * Math.sin(radianes);
        double nuevoY = x * Math.sin(radianes) + y * Math.cos(radianes);
        x = nuevoX;
        y = nuevoY;
    }

    /** Escala las coordenadas del punto respecto al origen. */
    public void escalar(double factor) {
        x *= factor;
        y *= factor;
    }

    public Punto puntoMedio(Punto otro) {
        return new Punto((x + otro.x) / 2, (y + otro.y) / 2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

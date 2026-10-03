package geometria.modelo;

/**
 * Abstracción de cualquier figura geométrica. (x, y) es el centro de la figura y
 * escala es el factor acumulado aplicado a sus dimensiones (inicia en 1).
 * Para agregar una figura nueva basta con extender esta clase (principio abierto/cerrado).
 */
public abstract class Figura {

    protected double x;
    protected double y;
    protected double escala;

    protected Figura(double x, double y) {
        this.x = x;
        this.y = y;
        this.escala = 1;
    }

    public abstract double calcularArea();

    public abstract double calcularPerimetro();

    public abstract double dimensionar();

    public void desplazar(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void escalar(double factor) {
        escala *= validarPositivo(factor, "factor de escala");
    }

    /**
     * Compara esta figura con otra del mismo tipo según el valor de dimensionar().
     *
     * @return negativo si esta figura es menor, 0 si son iguales, positivo si es mayor
     * @throws IllegalArgumentException si las figuras no son del mismo tipo
     */
    public int compararArea(Figura otra) {
        if (otra == null || getClass() != otra.getClass()) {
            throw new IllegalArgumentException("Solo se pueden comparar figuras del mismo tipo");
        }
        return Double.compare(dimensionar(), otra.dimensionar());
    }

    /** Tipo de la figura. Las subclases pueden especializarlo (p. ej. el triángulo). */
    public String obtenerTipo() {
        return getClass().getSimpleName();
    }

    /** Coordenadas del centro de la figura (asociación "coordenadas" con Punto). */
    public Punto getCoordenadas() {
        return new Punto(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getEscala() {
        return escala;
    }

    /** Regla del enunciado: ninguna dimensión puede ser menor o igual a cero. */
    protected static double validarPositivo(double valor, String nombreDimension) {
        if (!(valor > 0) || Double.isInfinite(valor)) {
            throw new IllegalArgumentException(
                    "El valor de '" + nombreDimension + "' debe ser mayor que cero (recibido: " + valor + ")");
        }
        return valor;
    }
}

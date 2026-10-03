package geometria.modelo.figuras;

import geometria.modelo.Figura;

/** Círculo con centro en (x, y). Dimensionar = área. */
public class Circulo extends Figura {

    private double radio;

    public Circulo(double x, double y, double radio) {
        super(x, y);
        setRadio(radio);
    }

    @Override
    public double calcularArea() {
        double radioActual = radio * escala;
        return Math.PI * radioActual * radioActual;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio * escala;
    }

    @Override
    public double dimensionar() {
        return calcularArea();
    }

    public double getRadio() {
        return radio;
    }

    public final void setRadio(double r) {
        this.radio = validarPositivo(r, "radio");
    }
}

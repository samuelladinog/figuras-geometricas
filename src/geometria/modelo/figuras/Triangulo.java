package geometria.modelo.figuras;

import geometria.modelo.Figura;

/** Triángulo definido por la longitud de sus tres lados. Dimensionar = perímetro. */
public class Triangulo extends Figura {

    private static final double TOLERANCIA = 1e-9;

    private final double lado1;
    private final double lado2;
    private final double lado3;

    public Triangulo(double x, double y, double lado1, double lado2, double lado3) {
        super(x, y);
        this.lado1 = validarPositivo(lado1, "lado1");
        this.lado2 = validarPositivo(lado2, "lado2");
        this.lado3 = validarPositivo(lado3, "lado3");
        if (!esTrianguloValido()) {
            throw new IllegalArgumentException(
                    "Los lados no forman un triángulo: cada lado debe ser menor que la suma de los otros dos");
        }
    }

    /** Fórmula de Herón. */
    @Override
    public double calcularArea() {
        double a = lado1 * escala;
        double b = lado2 * escala;
        double c = lado3 * escala;
        double semiperimetro = (a + b + c) / 2;
        return Math.sqrt(semiperimetro * (semiperimetro - a) * (semiperimetro - b) * (semiperimetro - c));
    }

    @Override
    public double calcularPerimetro() {
        return (lado1 + lado2 + lado3) * escala;
    }

    @Override
    public double dimensionar() {
        return calcularPerimetro();
    }

    @Override
    public String obtenerTipo() {
        boolean igualesAB = sonIguales(lado1, lado2);
        boolean igualesBC = sonIguales(lado2, lado3);
        boolean igualesAC = sonIguales(lado1, lado3);
        if (igualesAB && igualesBC) {
            return "Triangulo equilatero";
        }
        if (igualesAB || igualesBC || igualesAC) {
            return "Triangulo isosceles";
        }
        return "Triangulo escaleno";
    }

    private boolean esTrianguloValido() {
        return lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1;
    }

    private static boolean sonIguales(double a, double b) {
        return Math.abs(a - b) < TOLERANCIA;
    }
}

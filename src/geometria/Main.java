package geometria;

import geometria.modelo.Figura;
import geometria.modelo.figuras.Circulo;
import geometria.modelo.figuras.Cuadrilatero;
import geometria.modelo.figuras.Pentagono;
import geometria.modelo.figuras.Triangulo;
import geometria.presentacion.InformacionFigura;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== PRUEBAS DE FIGURAS GEOMETRICAS =====");

        Figura circulo1 = new Circulo(0, 0, 5);
        Figura circulo2 = new Circulo(10, 10, 3);

        Figura triangulo1 = new Triangulo(2, 3, 4, 5, 6);
        Figura triangulo2 = new Triangulo(5, 5, 4, 4, 4);

        Figura cuadrilatero1 = new Cuadrilatero(1, 1, 4, 5);
        Figura cuadrilatero2 = new Cuadrilatero(5, 5, 2, 3);

        Figura pentagono1 = new Pentagono(3, 3, 4);
        Figura pentagono2 = new Pentagono(8, 8, 2);

        // =========================
        // CALCULOS
        // =========================
        System.out.println("\n--- CALCULOS ---");

        System.out.println("Circulo - Area: " + circulo1.calcularArea());
        System.out.println("Circulo - Perimetro: " + circulo1.calcularPerimetro());

        System.out.println("Triangulo - Area: " + triangulo1.calcularArea());
        System.out.println("Triangulo - Perimetro: " + triangulo1.calcularPerimetro());

        System.out.println("Cuadrilatero - Area: " + cuadrilatero1.calcularArea());
        System.out.println("Cuadrilatero - Perimetro: " + cuadrilatero1.calcularPerimetro());

        System.out.println("Pentagono - Area: " + pentagono1.calcularArea());
        System.out.println("Pentagono - Perimetro: " + pentagono1.calcularPerimetro());

        // =========================
        // COMPARACIONES
        // =========================
        System.out.println("\n--- COMPARACIONES ---");

        comparar(circulo1, circulo2);
        comparar(triangulo1, triangulo2);
        comparar(cuadrilatero1, cuadrilatero2);
        comparar(pentagono1, pentagono2);

        // =========================
        // DESPLAZAMIENTO
        // =========================
        System.out.println("\n--- DESPLAZAMIENTO ---");

        System.out.println("Antes: (" + circulo1.getX() + ", " + circulo1.getY() + ")");

        circulo1.desplazar(5, 3);

        System.out.println("Despues: (" + circulo1.getX() + ", " + circulo1.getY() + ")");

        // =========================
        // ESCALAMIENTO
        // =========================
        System.out.println("\n--- ESCALAMIENTO ---");

        System.out.println("Escala inicial: " + circulo1.getEscala());

        circulo1.escalar(2);

        System.out.println("Escala despues: " + circulo1.getEscala());
        System.out.println("Nueva area: " + circulo1.calcularArea());

        // =========================
        // INFORMACION
        // =========================
        System.out.println("\n--- INFORMACION DE LAS FIGURAS ---");

        System.out.println(new InformacionFigura(circulo1));
        System.out.println();

        System.out.println(new InformacionFigura(triangulo1));
        System.out.println();

        System.out.println(new InformacionFigura(cuadrilatero1));
        System.out.println();

        System.out.println(new InformacionFigura(pentagono1));

        // =========================
        // 8 CASOS NO VALIDOS
        // =========================
        System.out.println("\n--- 8 CASOS NO VALIDOS ---");

        probarError("Circulo con radio negativo",
                () -> new Circulo(0, 0, -5));

        probarError("Circulo con radio cero",
                () -> new Circulo(0, 0, 0));

        probarError("Triangulo con lado negativo",
                () -> new Triangulo(0, 0, -1, 2, 3));

        probarError("Triangulo con lado cero",
                () -> new Triangulo(0, 0, 0, 2, 3));

        probarError("Triangulo invalido",
                () -> new Triangulo(0, 0, 1, 2, 10));

        probarError("Cuadrilatero con ancho negativo",
                () -> new Cuadrilatero(0, 0, -4, 5));

        probarError("Pentagono con lado cero",
                () -> new Pentagono(0, 0, 0));

        probarError("Escala con valor cero",
                () -> circulo1.escalar(0));

        System.out.println("\n===== FIN DE PRUEBAS =====");
    }

    private static void comparar(Figura figura1, Figura figura2) {

        int resultado = figura1.compararArea(figura2);

        if (resultado > 0) {
            System.out.println(
                    figura1.obtenerTipo() + " es mayor que " + figura2.obtenerTipo()
            );
        } else if (resultado < 0) {
            System.out.println(
                    figura1.obtenerTipo() + " es menor que " + figura2.obtenerTipo()
            );
        } else {
            System.out.println(
                    figura1.obtenerTipo() + " y " + figura2.obtenerTipo()
                            + " tienen la misma dimension"
            );
        }
    }

    private static void probarError(String nombre, Runnable prueba) {

        try {
            prueba.run();
            System.out.println("ERROR: " + nombre + " no fue detectado");
        } catch (IllegalArgumentException e) {
            System.out.println("Correcto - " + nombre + ": " + e.getMessage());
        }
    }
}

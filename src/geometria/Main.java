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

        // Crear figuras
        Figura circulo = new Circulo(0, 0, 5);
        Figura triangulo = new Triangulo(2, 3, 4, 5, 6);
        Figura cuadrilatero = new Cuadrilatero(1, 1, 4, 5);
        Figura pentagono = new Pentagono(3, 3, 4);

        // Pruebas de calculo
        System.out.println("\n--- CALCULOS ---");
        System.out.println("Area circulo: " + circulo.calcularArea());
        System.out.println("Perimetro circulo: " + circulo.calcularPerimetro());

        System.out.println("Area triangulo: " + triangulo.calcularArea());
        System.out.println("Perimetro triangulo: " + triangulo.calcularPerimetro());

        System.out.println("Area cuadrilatero: " + cuadrilatero.calcularArea());
        System.out.println("Perimetro cuadrilatero: " + cuadrilatero.calcularPerimetro());

        System.out.println("Area pentagono: " + pentagono.calcularArea());
        System.out.println("Perimetro pentagono: " + pentagono.calcularPerimetro());

        // Prueba de desplazamiento
        System.out.println("\n--- DESPLAZAMIENTO ---");
        System.out.println("Antes: (" + circulo.getX() + ", " + circulo.getY() + ")");

        circulo.desplazar(5, 3);

        System.out.println("Despues: (" + circulo.getX() + ", " + circulo.getY() + ")");

        // Prueba de escala
        System.out.println("\n--- ESCALA ---");
        System.out.println("Escala inicial: " + circulo.getEscala());

        circulo.escalar(2);

        System.out.println("Escala despues: " + circulo.getEscala());
        System.out.println("Nueva area: " + circulo.calcularArea());

        // Prueba de comparacion
        System.out.println("\n--- COMPARACION ---");

        Figura circulo2 = new Circulo(10, 10, 3);

        int resultado = circulo.compararArea(circulo2);

        if (resultado > 0) {
            System.out.println("El primer circulo tiene mayor dimension.");
        } else if (resultado < 0) {
            System.out.println("El segundo circulo tiene mayor dimension.");
        } else {
            System.out.println("Los circulos tienen la misma dimension.");
        }

        // Prueba de InformacionFigura
        System.out.println("\n--- INFORMACION DE LA FIGURA ---");
        System.out.println(new InformacionFigura(circulo));

        System.out.println("\n--- INFORMACION HTML ---");
        System.out.println(new InformacionFigura(circulo).toStringHTML());

        // Pruebas de dimensiones no validas
        System.out.println("\n--- DIMENSIONES NO VALIDAS ---");

        try {
            new Circulo(0, 0, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error detectado correctamente: " + e.getMessage());
        }

        try {
            circulo.escalar(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error detectado correctamente: " + e.getMessage());
        }

        try {
            new Triangulo(0, 0, 1, 2, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Error detectado correctamente: " + e.getMessage());
        }

        System.out.println("\n===== FIN DE PRUEBAS =====");
    }
}

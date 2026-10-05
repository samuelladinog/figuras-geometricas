package geometria;

import geometria.modelo.Figura;
import geometria.modelo.figuras.Circulo;
import geometria.modelo.figuras.Cuadrilatero;
import geometria.modelo.figuras.Pentagono;
import geometria.modelo.figuras.Triangulo;
import geometria.presentacion.InformacionFigura;

import java.util.Locale;

/** Pruebas de funcionamiento por consola (Parte 3 del parcial). */
public class Main {

    public static void main(String[] args) {
        Circulo circulo = new Circulo(0, 0, 3);
        Triangulo triangulo = new Triangulo(0, 0, 3, 4, 5);
        Cuadrilatero cuadrilatero = new Cuadrilatero(3, 2, 4, 2);
        Pentagono pentagono = new Pentagono(2, 2, 2);
        Figura[] figuras = {circulo, triangulo, cuadrilatero, pentagono};

        imprimirTitulo("1. CREACION Y CALCULOS (area, perimetro, dimensionar)");
        for (Figura figura : figuras) {
            System.out.println(new InformacionFigura(figura));
            System.out.println();
        }

        imprimirTitulo("2. DESPLAZAMIENTO (+2 en X, -1 en Y)");
        for (Figura figura : figuras) {
            figura.desplazar(2, -1);
            System.out.printf(Locale.ROOT, "%s -> centro (%.2f, %.2f), dimensionar %.2f%n",
                    figura.getClass().getSimpleName(), figura.getX(), figura.getY(), figura.dimensionar());
        }

        imprimirTitulo("3. ESCALAMIENTO (factor 2)");
        for (Figura figura : figuras) {
            double areaAntes = figura.calcularArea();
            figura.escalar(2);
            System.out.printf(Locale.ROOT, "%s: area antes %.2f -> area despues %.2f (escala %.1f)%n",
                    figura.getClass().getSimpleName(), areaAntes, figura.calcularArea(), figura.getEscala());
        }

        imprimirTitulo("4. COMPARACION ENTRE FIGURAS DEL MISMO TIPO");
        imprimirComparacion(circulo, new Circulo(5, 5, 1));
        imprimirComparacion(triangulo, new Triangulo(0, 0, 30, 40, 50));
        imprimirComparacion(cuadrilatero, new Cuadrilatero(0, 0, 1, 1));
        imprimirComparacion(pentagono, new Pentagono(-10, 0, 1));
        System.out.println("Tipo de triangulo 3-4-5: " + triangulo.obtenerTipo());
        System.out.println("Tipo de triangulo 2-2-2: " + new Triangulo(0, 0, 2, 2, 2).obtenerTipo());

        imprimirTitulo("5. MANEJO DE DIMENSIONES NO VALIDAS");
        probarCasoInvalido("Circulo con radio 0", () -> new Circulo(0, 0, 0));
        probarCasoInvalido("setRadio negativo", () -> circulo.setRadio(-5));
        probarCasoInvalido("Triangulo con lado negativo", () -> new Triangulo(0, 0, -1, 2, 2));
        probarCasoInvalido("Triangulo imposible (1, 2, 10)", () -> new Triangulo(0, 0, 1, 2, 10));
        probarCasoInvalido("Cuadrilatero con alto 0", () -> new Cuadrilatero(0, 0, 3, 0));
        probarCasoInvalido("Pentagono con lado -4", () -> new Pentagono(0, 0, -4));
        probarCasoInvalido("Escalar con factor 0", () -> pentagono.escalar(0));
        probarCasoInvalido("Comparar Circulo con Triangulo", () -> circulo.compararArea(triangulo));
    }

    private static void imprimirComparacion(Figura primera, Figura segunda) {
        int resultado = primera.compararArea(segunda);
        String relacion = resultado > 0 ? "MAYOR que" : resultado < 0 ? "MENOR que" : "IGUAL a";
        System.out.printf(Locale.ROOT, "%s (%.2f) es %s %s (%.2f)%n",
                primera.getClass().getSimpleName(), primera.dimensionar(), relacion,
                segunda.getClass().getSimpleName(), segunda.dimensionar());
    }

    private static void probarCasoInvalido(String descripcion, Runnable caso) {
        try {
            caso.run();
            System.out.println("[FALLO] " + descripcion + ": no se detecto el error");
        } catch (IllegalArgumentException error) {
            System.out.println("[OK] " + descripcion + " -> " + error.getMessage());
        }
    }

    private static void imprimirTitulo(String titulo) {
        System.out.println();
        System.out.println("===== " + titulo + " =====");
    }
}

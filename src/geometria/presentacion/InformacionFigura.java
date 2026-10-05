package geometria.presentacion;

import geometria.modelo.Figura;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Reúne y presenta la información de cualquier figura: tipo, dimensiones, área,
 * perímetro y dimensionamiento. Los atributos propios de cada figura se leen de forma
 * genérica, así que una figura nueva se muestra sin modificar esta clase.
 */
public class InformacionFigura {

    private final String nombre;
    private final String tipo;
    private final double x;
    private final double y;
    private final double escala;
    private final double area;
    private final double perimetro;
    private final double dimensionamiento;
    private final String[] atributos;
    private final Object[] valores;

    public InformacionFigura(Figura figura) {
        this.nombre = figura.getClass().getSimpleName();
        this.tipo = figura.obtenerTipo();
        this.x = figura.getX();
        this.y = figura.getY();
        this.escala = figura.getEscala();
        this.area = figura.calcularArea();
        this.perimetro = figura.calcularPerimetro();
        this.dimensionamiento = figura.dimensionar();

        List<String> nombresAtributos = new ArrayList<>();
        List<Object> valoresAtributos = new ArrayList<>();
        for (Field campo : figura.getClass().getDeclaredFields()) {
            if (Modifier.isStatic(campo.getModifiers())) {
                continue;
            }
            nombresAtributos.add(campo.getName());
            valoresAtributos.add(leerCampo(campo, figura));
        }
        this.atributos = nombresAtributos.toArray(new String[0]);
        this.valores = valoresAtributos.toArray();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        texto.append("Figura: ").append(nombre).append('\n');
        texto.append("Tipo: ").append(tipo).append('\n');
        texto.append(formatear("Centro: (%.2f, %.2f)%n", x, y));
        texto.append(formatear("Escala: %.2f%n", escala));
        for (int i = 0; i < atributos.length; i++) {
            texto.append(atributos[i]).append(": ").append(formatearValor(valores[i])).append('\n');
        }
        texto.append(formatear("Area: %.2f%n", area));
        texto.append(formatear("Perimetro: %.2f%n", perimetro));
        texto.append(formatear("Dimensionamiento: %.2f", dimensionamiento));
        return texto.toString();
    }

    /** Misma información en formato HTML, para mostrarla en componentes Swing. */
    public String toStringHTML() {
        StringBuilder html = new StringBuilder();
        html.append("<h3>").append(nombre).append("</h3><table border='1' cellpadding='3'>");
        html.append(fila("Tipo", tipo));
        html.append(fila("Centro", formatear("(%.2f, %.2f)", x, y)));
        html.append(fila("Escala", formatear("%.2f", escala)));
        for (int i = 0; i < atributos.length; i++) {
            html.append(fila(atributos[i], formatearValor(valores[i])));
        }
        html.append(fila("Área", formatear("%.2f", area)));
        html.append(fila("Perímetro", formatear("%.2f", perimetro)));
        html.append(fila("Dimensionamiento", formatear("%.2f", dimensionamiento)));
        return html.append("</table>").toString();
    }

    private static String fila(String etiqueta, String valor) {
        return "<tr><td><b>" + etiqueta + "</b></td><td>" + valor + "</td></tr>";
    }

    private static String formatearValor(Object valor) {
        return valor instanceof Number ? formatear("%.2f", valor) : String.valueOf(valor);
    }

    private static String formatear(String patron, Object... argumentos) {
        return String.format(Locale.ROOT, patron, argumentos);
    }

    private static Object leerCampo(Field campo, Figura figura) {
        try {
            campo.setAccessible(true);
            return campo.get(figura);
        } catch (IllegalAccessException error) {
            throw new IllegalStateException("No se pudo leer el atributo " + campo.getName(), error);
        }
    }
}

# Manejo de Figuras Geométricas

Programa en Java para crear y manipular círculos, triángulos, cuadriláteros y pentágonos:
calcular área y perímetro, dimensionar, comparar figuras del mismo tipo, desplazar, escalar
y validar que ninguna dimensión sea menor o igual a cero. Incluye pruebas por consola y una
interfaz gráfica diseñada con el **GUI Designer de IntelliJ** (arrastrando componentes).

## Requisitos

- IntelliJ IDEA (Community o Ultimate)
- JDK 8 o superior

## Cómo abrirlo y ejecutarlo en IntelliJ

1. *File → Open* y seleccionar la carpeta `FigurasGeometricas`.
2. Si aparece "Project SDK is not defined": *File → Project Structure → Project → SDK* y elegir tu JDK.
3. Verificar *Settings → Editor → GUI Designer → Generate GUI into: **Binary class files***
   (es el valor por defecto; así **no se genera código** en los .java).
4. Ejecutar:
   - `src/geometria/Main.java` → pruebas de funcionamiento por consola.
   - `src/geometria/MainInterfaz.java` → interfaz gráfica.
5. Para ver o editar la interfaz: abrir `src/geometria/vista/VentanaPrincipal.form`
   (se abre en el diseñador visual).

> La interfaz debe ejecutarse desde IntelliJ: es IntelliJ quien convierte el `.form` en la
> ventana al compilar. Las pruebas de consola también se pueden compilar con `javac`:
>
> ```bash
> javac -encoding UTF-8 -d out $(find src -name "*.java")
> java -cp out geometria.Main
> ```

## Estructura (según el diagrama de clases)

```
src/geometria
├── Main.java                      Pruebas por consola (Parte 3)
├── MainInterfaz.java              Abre la ventana
├── modelo
│   ├── Figura.java                <<abstract>> x, y, escala + desplazar, escalar, compararArea
│   ├── Punto.java                 Coordenadas (distancia, trasladar, rotar, escalar, puntoMedio)
│   └── figuras/                   Circulo, Triangulo, Cuadrilatero, Pentagono
├── presentacion
│   └── InformacionFigura.java     Información de cualquier figura (toString / toStringHTML)
└── vista
    ├── VentanaPrincipal.form      Diseño visual (GUI Designer)
    ├── VentanaPrincipal.java      Eventos de los botones
    └── TipoFigura.java            Figuras que se pueden crear desde la ventana
```

## Reglas de cálculo

| Figura       | Área                 | Perímetro      | Dimensionar                                  |
|--------------|----------------------|----------------|----------------------------------------------|
| Círculo      | π·r²                 | 2·π·r          | Área                                         |
| Triángulo    | Herón                | l1 + l2 + l3   | Perímetro                                    |
| Cuadrilátero | ancho·alto           | 2·(ancho+alto) | Suma de distancias de sus 4 esquinas al (0,0)|
| Pentágono    | 5·l² / (4·tan 36°)   | 5·l            | Suma de las X de sus 5 vértices              |

`(x, y)` es el centro de cada figura. `escala` empieza en 1 y `escalar(factor)` la multiplica;
las medidas reales son dimensión × escala.

## Principios SOLID

- **S**: las figuras calculan, `InformacionFigura` presenta, la vista solo maneja eventos.
- **O**: una figura nueva = una clase que extiende `Figura` (+ una línea en `TipoFigura` para la interfaz). `InformacionFigura` la muestra sin cambios porque lee sus atributos de forma genérica.
- **L**: cualquier subclase se usa donde se espera una `Figura`.
- **I**: `Figura` solo expone lo que comparten todas las figuras.
- **D**: la vista e `InformacionFigura` dependen de la abstracción `Figura`.

## Variables sensibles

Copiar `.env.example` como `.env` y poner los valores reales. `.env` está en `.gitignore`.

## Flujo de trabajo

GitFlow (`main`, `develop`, `feature/*`, `release/*`, `hotfix/*`). Ver [GITFLOW.md](GITFLOW.md).

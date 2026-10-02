# Guía de trabajo en equipo con GitFlow

Cada integrante trabaja **con su propia cuenta de GitHub y en su propio computador**, para que
el historial muestre el trabajo colaborativo. Cada uno parte de lo que hizo el anterior.

## Reparto según el diagrama

| Integrante | Clases del diagrama | Rama |
|---|---|---|
| 1 | `Figura` (abstracta) y `Punto` + creación del repositorio | `feature/figura-y-punto` |
| 2 | `Circulo`, `Triangulo`, `Cuadrilatero`, `Pentagono` | `feature/figuras-concretas` |
| 3 | `InformacionFigura` y `Main` (pruebas) | `feature/informacion-y-pruebas` |

Interfaz gráfica (siguiente clase):

| Integrante | Qué hace | Rama |
|---|---|---|
| 1 | `TipoFigura` | `feature/gui-tipos` |
| 2 | Diseña `VentanaPrincipal.form` arrastrando componentes (crea también `VentanaPrincipal.java` con los campos) | `feature/gui-diseno` |
| 3 | Programa los eventos de los botones en `VentanaPrincipal.java` y crea `MainInterfaz` | `feature/gui-eventos` |
| 3 | Release `1.0.0` | `release/1.0.0` |
| 2 | Correcciones de la presentación | `hotfix/1.0.1-...` |

Regla: nadie hace commit directo a `main` ni a `develop`. Todo entra por Pull Request (PR)
y **otro integrante** lo revisa y lo aprueba.

> Antes de cada `git commit`, haz `git add <archivos que agregaste>`.

---

## Paso 0 — Integrante 1: crear el repositorio

1. En GitHub: *New repository* → `figuras-geometricas` (vacío, sin README ni .gitignore).
2. *Settings → Collaborators* → invitar a los integrantes 2 y 3.
3. En su computador, en la carpeta del proyecto (por ahora solo `README.md`, `.gitignore`, `.env.example`, `GITFLOW.md`):

```bash
git init
git add README.md .gitignore .env.example GITFLOW.md
git commit -m "chore: estructura inicial con README, .gitignore y ejemplo de .env"
git branch -M main
git remote add origin https://github.com/samuelladinog/figuras-geometricas.git
git push -u origin main
git checkout -b develop
git push -u origin develop
```

4. (Recomendado) *Settings → Branches*: proteger `main` y `develop` exigiendo PR.
5. Los integrantes 2 y 3 aceptan la invitación y en IntelliJ: *File → New → Project from Version Control* con la URL del repo; luego cambian a `develop` (abajo a la derecha, menú de ramas de Git).

## Paso 1 — Integrante 1: Figura y Punto

```bash
git checkout develop
git pull
git checkout -b feature/figura-y-punto
git commit -m "feat(modelo): agrega clase Punto con distancia, trasladar, rotar, escalar y puntoMedio"
git commit -m "feat(modelo): agrega clase abstracta Figura con desplazar, escalar y compararArea"
git commit -m "feat(modelo): valida que las dimensiones sean mayores que cero"
git push -u origin feature/figura-y-punto
```

PR `feature/figura-y-punto → develop`. Lo revisa el Integrante 2 y hace *Merge*.

## Paso 2 — Integrante 2: figuras concretas

```bash
git checkout develop
git pull
git checkout -b feature/figuras-concretas
git commit -m "feat(figuras): implementa Circulo (dimensionar = area)"
git commit -m "feat(figuras): implementa Triangulo con validacion de lados y obtenerTipo"
git commit -m "feat(figuras): implementa Cuadrilatero (dimensionar = distancias al origen)"
git commit -m "feat(figuras): implementa Pentagono con obtenerVertices (dimensionar = suma de X)"
git push -u origin feature/figuras-concretas
```

PR → `develop`. Lo revisa el Integrante 3.

## Paso 3 — Integrante 3: InformacionFigura y pruebas

```bash
git checkout develop
git pull
git checkout -b feature/informacion-y-pruebas
git commit -m "feat(presentacion): agrega InformacionFigura con toString y toStringHTML"
git commit -m "test: agrega Main con pruebas de calculo, desplazamiento, escala y comparacion"
git commit -m "test: agrega pruebas de dimensiones no validas"
git push -u origin feature/informacion-y-pruebas
```

PR → `develop`. Lo revisa el Integrante 1.

## Paso 4 — Interfaz gráfica (en este orden)

**Integrante 1** (`feature/gui-tipos`): agrega `vista/TipoFigura.java`.
`git commit -m "feat(vista): agrega TipoFigura con las figuras que se pueden crear"`

**Integrante 2** (`feature/gui-diseno`): en IntelliJ, clic derecho sobre el paquete
`geometria.vista` → *New → Swing UI Designer → GUI Form*, nombre `VentanaPrincipal`. Luego arrastra
desde la paleta y en *field name* pone exactamente estos nombres:

| Zona | Componentes (field name) |
|---|---|
| Panel raíz | `panelPrincipal` (JPanel, cuadrícula 2×2) |
| Arriba izquierda, borde con título "Crear figura" | `comboTipo` (JComboBox), `campoX`, `campoY` (JTextField), `etiquetaDimension1..3` (JLabel), `campoDimension1..3` (JTextField), `botonCrear` (JButton) |
| Abajo izquierda, borde "Operaciones" | `campoDx`, `campoDy`, `campoFactor` (JTextField), `botonDesplazar`, `botonEscalar`, `botonComparar`, `botonEliminar`, `botonMostrar` (JButton) |
| Arriba derecha | JScrollPane con `listaFiguras` (JList) |
| Abajo derecha | JScrollPane con `panelInformacion` (JEditorPane, contentType `text/html`, editable desmarcado) |

`git commit -m "feat(vista): disena VentanaPrincipal con el GUI Designer"`

**Integrante 3** (`feature/gui-eventos`): completa el constructor de `VentanaPrincipal.java`
(listeners de los botones) y crea `MainInterfaz.java`.
`git commit -m "feat(vista): programa eventos de crear, desplazar, escalar, comparar y mostrar"`
`git commit -m "feat: agrega MainInterfaz para abrir la ventana"`

Cada uno: `git checkout develop && git pull` antes de crear su rama, y PR a `develop` al terminar.

## Paso 5 — Integrante 3: release

```bash
git checkout develop
git pull
git checkout -b release/1.0.0
# ejecutar Main y MainInterfaz en IntelliJ; corregir detalles menores si hace falta
git commit -m "chore(release): prepara version 1.0.0"
git push -u origin release/1.0.0
```

PR `release/1.0.0 → main` y otro PR `release/1.0.0 → develop`. Después:

```bash
git checkout main
git pull
git tag -a v1.0.0 -m "Version 1.0.0"
git push origin v1.0.0
```

## Paso 6 — Integrante 2: hotfix (correcciones de la presentación)

```bash
git checkout main
git pull
git checkout -b hotfix/1.0.1-descripcion-corta
# corregir
git commit -m "fix: describe exactamente que se corrigio"
git push -u origin hotfix/1.0.1-descripcion-corta
```

PR → `main` y PR → `develop`; luego etiqueta `v1.0.1` en `main`.

## Convención de commits

`tipo(alcance): descripción` — tipos: `feat`, `fix`, `refactor`, `test`, `docs`, `chore`.

- Bien: `fix(circulo): impide asignar radio negativo en setRadio`
- Mal: `cambios`, `arreglos`, `ya funciona`

**Antes de cada push, ejecutar `Main` en IntelliJ.** Si no compila, no se sube (código que no compila = 0.0).

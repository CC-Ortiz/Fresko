# Fresko

## Descripción
Fresko es una aplicación de consola en Java para gestionar un inventario de alimentos y consultar sus fechas de vencimiento. Está dirigida principalmente a tiendas de barrio y fruvers, con el propósito de ayudar a identificar productos próximos a vencer y productos vencidos.

## Funcionalidades
- **HU-01 – Agregar un producto:** registra código, nombre, categoría, cantidad, precio y fecha de vencimiento; valida los datos y evita códigos repetidos.
- **HU-02 – Consultar y buscar productos:** permite listar el inventario y buscar por código.
- **HU-03 – Actualizar existencias:** registra entradas y salidas; rechaza cantidades no positivas, códigos inexistentes y salidas superiores a las existencias.
- **HU-04 – Consultar productos próximos a vencer:** muestra productos que vencen hoy o durante los próximos tres días, ordenados por fecha.
- **HU-05 – Consultar productos vencidos:** muestra los productos cuya fecha de vencimiento es anterior al día actual, ordenados por fecha.
- **HU-06 – Conservar el inventario:** guarda el inventario en un archivo de texto plano y lo carga al iniciar la aplicación.

## Requisitos
- Java JDK instalado.
- Terminal o consola.

## Ejecución
Desde la carpeta que contiene `Main.java`, `Producto.java`, `Inventario.java` y `ArchivoInventario.java`:

```bash
javac Main.java Producto.java Inventario.java ArchivoInventario.java
java Main
```

Al iniciar, Fresko intenta cargar el archivo de inventario guardado. Si todavía no existe, comienza con el inventario vacío. Los cambios aceptados se guardan en el archivo de texto plano administrado por `ArchivoInventario`.

## Menú
1. Listar productos
2. Buscar producto por código
3. Registrar producto
4. Registrar entrada de existencias
5. Registrar salida de existencias
6. Consultar productos próximos a vencer
7. Consultar productos vencidos
8. Salir

Al registrar la fecha de vencimiento, se utiliza el formato `AAAA-MM-DD` (por ejemplo, `2026-10-25`). Para el precio se acepta punto o coma decimal.

## Organización del código
- `Main.java`: menú, lectura de datos, mensajes y conexión de las opciones con las operaciones del inventario.
- `Producto.java`: representa cada producto y valida sus datos.
- `Inventario.java`: agrega, busca, lista, actualiza existencias y filtra productos por vencimiento.
- `ArchivoInventario.java`: carga y guarda los productos en texto plano.

## Equipo y organización Scrum
- Kevin Franco — Product Owner y equipo de desarrollo.
- Cristian Ortiz — Scrum Master y equipo de desarrollo.
- Fabian Alvarez — equipo de desarrollo.

## Seguimiento de sprints
- **Sprint 1 (16/10–18/10):** HU-01, HU-02 y HU-03 completadas. En HU-02 se corrigió un error de puntuación que impedía compilar inicialmente.
- **Sprint 2 (19/10–20/10):** HU-04 y HU-05 completadas; HU-06 continuó en desarrollo.
- **Sprint 3 (21/10):** HU-06 completada y se preparó la documentación del proyecto.

## Evidencia en GitHub
El repositorio muestra seis Pull Requests cerrados y fusionados para las historias HU-01 a HU-06, distribuidos en las etiquetas de sprint visibles en GitHub. Los Issues relacionados también aparecen cerrados. Los números y títulos deben consultarse directamente en el repositorio para ver sus detalles.

## Pruebas
Los escenarios de validación y sus resultados esperados están documentados en [`PRUEBAS.md`](PRUEBAS.md).

# PRUEBAS — Fresko

## Objetivo
Describir los casos de comprobación de las historias HU-01 a HU-06 del sistema de inventario Fresko.

## Preparación
1. Compilar los archivos Java:
   ```bash
   javac Main.java Producto.java Inventario.java ArchivoInventario.java
   ```
2. Ejecutar:
   ```bash
   java Main
   ```
3. Registrar los datos indicados en cada caso desde el menú. Para comprobar fechas de vencimiento, usar fechas relativas al día en que se ejecute la prueba.

## Casos de prueba

| ID | Historia | Acción / datos | Resultado esperado |
|---|---|---|---|
| P-01 | HU-01 | Registrar producto con código `A01`, nombre `Leche`, categoría `Lácteos`, cantidad `5`, precio `4500`, fecha válida `AAAA-MM-DD`. | El producto se registra y aparece en el inventario. |
| P-02 | HU-01 | Registrar nuevamente un producto con código `A01`. | Se rechaza el registro por código duplicado. |
| P-03 | HU-01 | Registrar producto con código, nombre o categoría vacíos; cantidad o precio negativos; o fecha inválida. | Se muestra un mensaje de error y el producto no se agrega. |
| P-04 | HU-02 | Elegir listar productos cuando hay productos registrados. | Se muestra la información de los productos. |
| P-05 | HU-02 | Buscar un código existente y luego uno inexistente. | El existente se muestra; el inexistente produce un mensaje informativo. |
| P-06 | HU-03 | Para `A01` con 5 unidades, registrar entrada de 3. | La cantidad pasa a 8 y se informa que la entrada fue aceptada. |
| P-07 | HU-03 | Para `A01` con 8 unidades, registrar salida de 2. | La cantidad pasa a 6. |
| P-08 | HU-03 | Intentar una salida mayor que las existencias, una cantidad cero/negativa, texto no numérico o código inexistente. | La operación se rechaza y las existencias no cambian. |
| P-09 | HU-04 | Registrar productos con vencimiento ayer, hoy, dentro de 2 días y dentro de 4 días. Consultar próximos a vencer. | Se muestran los que vencen hoy y dentro de 2 días; no se incluyen el vencido ni el que vence dentro de 4 días. |
| P-10 | HU-04 | Incluir varios productos próximos a vencer con fechas distintas. | Se presentan ordenados desde la fecha más cercana. |
| P-11 | HU-05 | Registrar productos con fechas de vencimiento anteriores a hoy y otros con fecha de hoy o posterior. Consultar vencidos. | Solo aparecen los productos con fecha anterior a hoy, ordenados por vencimiento. |
| P-12 | HU-06 | Registrar un producto o actualizar existencias y luego salir y volver a iniciar la aplicación. | Los cambios aceptados se recuperan al iniciar desde el archivo guardado. |
| P-13 | HU-06 | Iniciar la aplicación sin que exista un archivo de inventario guardado. | El programa informa que inicia con inventario vacío y permite usar el menú. |
| P-14 | HU-06 | En el menú, escribir letras en lugar de un número. | Se informa que la opción debe ser numérica y el menú continúa funcionando. |

## Criterios de aceptación de la revisión
- Los resultados corresponden al comportamiento esperado descrito en cada caso.
- Las operaciones rechazadas no modifican las existencias.
- La consulta de vencimientos diferencia productos vencidos de los que vencen hoy o dentro de los próximos tres días.
- Los datos aceptados se conservan al reiniciar el programa.

## Observación
Las capturas proporcionadas muestran los Issues cerrados y los Pull Requests fusionados asociados a las historias. Este archivo define los escenarios de prueba para la verificación funcional; los resultados deben corresponder a la ejecución del programa y no únicamente al estado de una tarjeta en GitHub.

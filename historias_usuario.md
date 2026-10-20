# Fresko - Historias de usuario

## HU-01 - Registrar un producto

**Como** responsable del inventario,  
**quiero** registrar los alimentos disponibles,  
**para** controlar las cantidades y fechas de vencimiento de mi negocio.

### Criterios de aceptación
- [ ] Se solicita el nombre, código, categoría, cantidad, precio y fecha de vencimiento.
- [ ] No se permiten nombres vacíos ni códigos repetidos.
- [ ] La cantidad y el precio deben ser valores válidos.
- [ ] El producto se guarda correctamente en el inventario.
- [ ] El sistema confirma que el producto fue registrado.

## HU-02 - Consultar productos

**Como** responsable del inventario,  
**quiero** consultar los productos registrados,  
**para** conocer los alimentos disponibles en el negocio.

### Criterios de aceptación
- [ ] Se muestra la lista de productos registrados.
- [ ] Cada producto muestra su nombre, código, cantidad y fecha de vencimiento.
- [ ] Se puede buscar un producto por su nombre o código.
- [ ] El sistema informa cuando no existen productos registrados.

## HU-03 - Actualizar un producto

**Como** responsable del inventario,  
**quiero** actualizar la información de un producto,  
**para** mantener los datos del inventario al día.

### Criterios de aceptación
- [ ] Se puede seleccionar un producto registrado.
- [ ] Se permite modificar la cantidad, el precio y la fecha de vencimiento.
- [ ] Se validan los datos antes de guardar los cambios.
- [ ] El sistema confirma que la información fue actualizada.

## HU-04 - Eliminar un producto

**Como** responsable del inventario,  
**quiero** eliminar un producto del inventario,  
**para** mantener organizada la información de los alimentos disponibles.

### Criterios de aceptación
- [ ] Se puede seleccionar el producto que se desea eliminar.
- [ ] El sistema solicita confirmación antes de eliminarlo.
- [ ] El producto deja de aparecer en el inventario después de eliminarlo.
- [ ] El sistema informa si la eliminación fue exitosa.

## HU-05 - Controlar fechas de vencimiento

**Como** responsable del inventario,  
**quiero** identificar los productos próximos a vencer,  
**para** evitar pérdidas de alimentos y mejorar su control.

### Criterios de aceptación
- [ ] Se identifica la fecha de vencimiento de cada producto.
- [ ] Se muestran alertas para los productos próximos a vencer.
- [ ] Se identifican los productos vencidos.
- [ ] Las alertas se actualizan según las fechas registradas.
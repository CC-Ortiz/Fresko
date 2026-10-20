import java.time.LocalDate;

public class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precio;
    private String fechaVencimiento;

    public Producto(String codigo, String nombre, String categoria,
                    int cantidad, double precio, String fechaVencimiento) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if (!Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException("El precio debe ser un número válido y no negativo.");
        }
        if (fechaVencimiento == null || fechaVencimiento.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria.");
        }

        String fechaLimpia = fechaVencimiento.trim();
        try {
            LocalDate.parse(fechaLimpia); // Formato ISO: AAAA-MM-DD
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "La fecha debe ser válida y tener el formato AAAA-MM-DD.");
        }

        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.categoria = categoria.trim();
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaVencimiento = fechaLimpia;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    // HU-03: modifica las existencias solo si la nueva cantidad es válida.
    public boolean modificarCantidad(int nuevaCantidad) {
        if (nuevaCantidad < 0) {
            return false;
        }

        this.cantidad = nuevaCantidad;
        return true;
    }

    public double getPrecio() {
        return precio;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Código: " + codigo
                + " | Nombre: " + nombre
                + " | Categoría: " + categoria
                + " | Cantidad: " + cantidad
                + " | Precio: $" + String.format(java.util.Locale.US, "%.2f", precio)
                + " | Vencimiento: " + fechaVencimiento;
    }
}

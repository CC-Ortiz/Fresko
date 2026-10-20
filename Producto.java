public class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;
    private String fechaVencimiento; // Se puede usar String o LocalDate según prefieras

    public Producto(String codigo, String nombre, int cantidad, String fechaVencimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Nombre: " + nombre + " | Cantidad: " + cantidad + " | Vencimiento: " + fechaVencimiento;
    }
}.
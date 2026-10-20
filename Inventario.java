import java.util.ArrayList;

public class Inventario {
    private final ArrayList<Producto> listaProductos;

    public Inventario() {
        listaProductos = new ArrayList<>();
    }

    // HU-01: no agrega productos nulos ni códigos repetidos.
    public boolean agregarProducto(Producto producto) {
        if (producto == null || buscarProducto(producto.getCodigo()) != null) {
            return false;
        }

        listaProductos.add(producto);
        return true;
    }

    // HU-02: devuelve una copia para separar la consulta del almacenamiento interno.
    public ArrayList<Producto> listarProductos() {
        return new ArrayList<>(listaProductos);
    }

    // HU-03: registra una entrada de unidades. Devuelve false si los datos no son válidos.
    public boolean registrarEntrada(String codigo, int cantidad) {
        if (codigo == null || codigo.trim().isEmpty() || cantidad <= 0) {
            return false;
        }

        Producto producto = buscarProducto(codigo);
        if (producto == null) {
            return false;
        }

        // Evita desbordar el tipo int al sumar existencias.
        long nuevaCantidad = (long) producto.getCantidad() + cantidad;
        if (nuevaCantidad > Integer.MAX_VALUE) {
            return false;
        }

        return producto.modificarCantidad((int) nuevaCantidad);
    }

    // HU-03: registra una salida solo si hay suficientes existencias.
    public boolean registrarSalida(String codigo, int cantidad) {
        if (codigo == null || codigo.trim().isEmpty() || cantidad <= 0) {
            return false;
        }

        Producto producto = buscarProducto(codigo);
        if (producto == null || cantidad > producto.getCantidad()) {
            return false;
        }

        int nuevaCantidad = producto.getCantidad() - cantidad;
        return producto.modificarCantidad(nuevaCantidad);
    }

    // HU-02: busca por código sin modificar el inventario.
    public Producto buscarProducto(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        for (Producto producto : listaProductos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return producto;
            }
        }

        return null;
    }
}

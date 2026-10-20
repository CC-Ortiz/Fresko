import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> listaProductos;

    public Inventario() {
        this.listaProductos = new ArrayList<>();
    }

    // Método de apoyo para registrar productos (útil para pruebas)
    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    // Tarea: Implementar listarProductos() utilizando ArrayList y separando la lógica
    public ArrayList<Producto> listarProductos() {
        return listaProductos;
    }

    // Tarea: Implementar buscarProducto() recorriendo con for-each
    public Producto buscarProducto(String codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null; // Retorna null si el código no existe
    }
}.
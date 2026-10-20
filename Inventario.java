import java.util.ArrayList;

public class Inventario {
    private final ArrayList<Producto> listaProductos;

    public Inventario() {
        listaProductos = new ArrayList<>();
    }

    //no agrega productos nulos ni códigos repetidos.
    public boolean agregarProducto(Producto producto) {
        if (producto == null || buscarProducto(producto.getCodigo()) != null) {
            return false;
        }

        listaProductos.add(producto);
        return true;
    }

    //devuelve una copia para separar la consulta del almacenamiento interno.
    public ArrayList<Producto> listarProductos() {
        return new ArrayList<>(listaProductos);
    }

    //busca por código sin modificar el inventario.
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

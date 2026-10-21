import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * HU-06: persistencia local del inventario de Fresko.
 * Los campos se guardan en texto plano 
 */
public class ArchivoInventario {
    private static final String ENCABEZADO = "FRESKO_INVENTARIO_V2";
    private final Path archivo;

    public ArchivoInventario() {
        this("inventario_fresko.txt");
    }

    public ArchivoInventario(String rutaArchivo) {
        if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacía.");
        }
        this.archivo = Paths.get(rutaArchivo);
    }

    public Inventario cargarInventario() throws IOException {
        Inventario inventario = new Inventario();

        // HU-06: si todavía no existe el archivo, se inicia vacío.
        if (!Files.exists(archivo)) {
            return inventario;
        }

        try (BufferedReader lector = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String encabezado = lector.readLine();
            if (!ENCABEZADO.equals(encabezado)) {
                throw new IOException("El archivo de inventario tiene un formato desconocido.");
            }

            String linea;
            int numeroLinea = 1;
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                if (linea.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] campos = linea.split("\\|", -1);
                    if (campos.length != 6) {
                        throw new IllegalArgumentException("Cantidad de campos incorrecta.");
                    }

                    String codigo = campos[0];
                    String nombre = campos[1];
                    String categoria = campos[2];
                    int cantidad = Integer.parseInt(campos[3]);
                    double precio = Double.parseDouble(campos[4]);
                    String fechaVencimiento = campos[5];

                    Producto producto = new Producto(
                            codigo, nombre, categoria, cantidad, precio, fechaVencimiento);
                    if (!inventario.agregarProducto(producto)) {
                        throw new IllegalArgumentException("Código de producto repetido.");
                    }
                } catch (RuntimeException e) {
                    throw new IOException("No se pudo leer la línea " + numeroLinea
                            + " del archivo de inventario: " + e.getMessage(), e);
                }
            }
        }

        return inventario;
    }

    public void guardarInventario(Inventario inventario) throws IOException {
        if (inventario == null) {
            throw new IllegalArgumentException("El inventario no puede ser nulo.");
        }

        Path destinoAbsoluto = archivo.toAbsolutePath();
        Path directorio = destinoAbsoluto.getParent();
        if (directorio != null) {
            Files.createDirectories(directorio);
        }

        Path temporal = Files.createTempFile(directorio, "fresko-inventario-", ".tmp");
        boolean movido = false;
        try {
            try (BufferedWriter escritor = Files.newBufferedWriter(
                    temporal, StandardCharsets.UTF_8)) {
                escritor.write(ENCABEZADO);
                escritor.newLine();

                ArrayList<Producto> productos = inventario.listarProductos();
                for (Producto producto : productos) {
                    escritor.write(producto.getCodigo());
                    escritor.write('|');
                    escritor.write(producto.getNombre());
                    escritor.write('|');
                    escritor.write(producto.getCategoria());
                    escritor.write('|');
                    escritor.write(Integer.toString(producto.getCantidad()));
                    escritor.write('|');
                    escritor.write(Double.toString(producto.getPrecio()));
                    escritor.write('|');
                    escritor.write(producto.getFechaVencimiento());
                    escritor.newLine();
                }
            }

            try {
                Files.move(temporal, destinoAbsoluto,
                        StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (IOException noAtomico) {
                Files.move(temporal, destinoAbsoluto, StandardCopyOption.REPLACE_EXISTING);
            }
            movido = true;
        } finally {
            if (!movido) {
                Files.deleteIfExists(temporal);
            }
        }
    }

    public Path getRutaArchivo() {
        return archivo;
    }

}

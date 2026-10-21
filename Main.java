import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArchivoInventario archivoInventario = new ArchivoInventario();
        Inventario inventario;
        try {
            inventario = archivoInventario.cargarInventario();
            if (java.nio.file.Files.exists(archivoInventario.getRutaArchivo())) {
                System.out.println("[ÉXITO] Inventario recuperado desde "
                        + archivoInventario.getRutaArchivo().toAbsolutePath());
            } else {
                System.out.println("[INFO] No existe un archivo guardado. Se inicia con inventario vacío.");
            }
        } catch (java.io.IOException e) {
            System.out.println("[ERROR] No se pudo cargar el inventario: " + e.getMessage());
            System.out.println("[INFO] Se iniciará una sesión con inventario vacío.");
            inventario = new Inventario();
        }

        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE INVENTARIO - FRESKO ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Buscar producto por código");
            System.out.println("3. Registrar producto");
            System.out.println("4. Registrar entrada de existencias");
            System.out.println("5. Registrar salida de existencias");
            System.out.println("6. Consultar productos próximos a vencer");
            System.out.println("7. Consultar productos vencidos");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        ArrayList<Producto> productos = inventario.listarProductos();

                        if (productos.isEmpty()) {
                            System.out.println("\n[INFO] No hay productos registrados en el inventario.");
                        } else {
                            System.out.println("\n--- LISTA DE PRODUCTOS ---");
                            for (Producto producto : productos) {
                                System.out.println("- " + producto);
                            }
                        }
                        break;

                    case 2:
                        System.out.print("\nIngrese el código del producto a buscar: ");
                        String codigoBuscado = scanner.nextLine().trim();

                        Producto encontrado = inventario.buscarProducto(codigoBuscado);
                        if (encontrado != null) {
                            System.out.println("\n[ÉXITO] Producto encontrado:");
                            System.out.println(encontrado);
                        } else {
                            System.out.println("\n[INFO] El código '" + codigoBuscado
                                    + "' no existe en el inventario.");
                        }
                        break;

                    case 3:
                        registrarProducto(inventario, scanner, archivoInventario);
                        break;

                    case 4:
                        registrarMovimiento(inventario, scanner, true, archivoInventario);
                        break;

                    case 5:
                        registrarMovimiento(inventario, scanner, false, archivoInventario);
                        break;

                    case 6:
                        consultarProximosAVencer(inventario);
                        break;

                    case 7:
                        consultarVencidos(inventario);
                        break;

                    case 8:
                        System.out.println("\nSaliendo del módulo de inventario...");
                        break;

                    default:
                        System.out.println("\n[ERROR] Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[ERROR] Ingrese una opción numérica válida.");
                opcion = 0;
            }
        } while (opcion != 8);

        scanner.close();
    }

    // HU-04: muestra productos con vencimiento entre hoy y los próximos 3 días.
    private static void consultarProximosAVencer(Inventario inventario) {
        ArrayList<Producto> productos = inventario.listarProductosProximosAVencer();

        System.out.println("\n--- PRODUCTOS PRÓXIMOS A VENCER - HU-04 ---");

        if (productos.isEmpty()) {
            System.out.println("[INFO] No hay productos que venzan hoy ni dentro de los próximos 3 días.");
            return;
        }

        for (Producto producto : productos) {
            java.time.LocalDate fecha = java.time.LocalDate.parse(producto.getFechaVencimiento());
            if (fecha.equals(java.time.LocalDate.now())) {
                System.out.println("[¡VENCE HOY!] Nombre: " + producto.getNombre()
                        + " | Cantidad: " + producto.getCantidad()
                        + " | Fecha de vencimiento: " + producto.getFechaVencimiento());
            } else {
                System.out.println("Nombre: " + producto.getNombre()
                        + " | Cantidad: " + producto.getCantidad()
                        + " | Fecha de vencimiento: " + producto.getFechaVencimiento());
            }
        }
    }

    // HU-05: muestra los productos que ya vencieron.
    private static void consultarVencidos(Inventario inventario) {
        ArrayList<Producto> vencidos = inventario.consultarVencidos();

        System.out.println("\n--- PRODUCTOS VENCIDOS - HU-05 ---");

        if (vencidos.isEmpty()) {
            System.out.println("[INFO] No existen productos vencidos en el inventario.");
            return;
        }

        for (Producto producto : vencidos) {
            System.out.println("Nombre: " + producto.getNombre()
                    + " | Cantidad: " + producto.getCantidad()
                    + " | Fecha de vencimiento: " + producto.getFechaVencimiento());
        }
    }

    // HU-03: registra entradas o salidas de existencias.
    private static void registrarMovimiento(Inventario inventario, Scanner scanner,
                                            boolean esEntrada, ArchivoInventario archivoInventario) {
        String tipo = esEntrada ? "ENTRADA" : "SALIDA";
        System.out.println("\n--- REGISTRAR " + tipo + " DE EXISTENCIAS - HU-03 ---");

        System.out.print("Código del producto: ");
        String codigo = scanner.nextLine().trim();

        Producto producto = inventario.buscarProducto(codigo);
        if (producto == null) {
            System.out.println("[RECHAZADA] El código '" + codigo + "' no existe. El inventario no fue modificado.");
            return;
        }

        System.out.print("Cantidad de unidades para la " + tipo.toLowerCase() + ": ");
        final int cantidad;
        try {
            cantidad = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[RECHAZADA] La cantidad debe ser un número entero positivo. El inventario no fue modificado.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("[RECHAZADA] La cantidad debe ser mayor que cero. El inventario no fue modificado.");
            return;
        }

        boolean aceptada = esEntrada
                ? inventario.registrarEntrada(codigo, cantidad)
                : inventario.registrarSalida(codigo, cantidad);

        if (aceptada) {
            System.out.println("[ACEPTADA] " + tipo + " registrada correctamente.");
            System.out.println("Existencias actuales de " + producto.getNombre() + ": " + producto.getCantidad());
            guardarInventario(archivoInventario, inventario);
        } else if (!esEntrada && cantidad > producto.getCantidad()) {
            System.out.println("[RECHAZADA] No hay existencias suficientes. Disponibles: "
                    + producto.getCantidad() + ". El inventario no fue modificado.");
        } else {
            System.out.println("[RECHAZADA] No se pudo registrar la " + tipo.toLowerCase()
                    + ". Verifique la cantidad. El inventario no fue modificado.");
        }
    }

    // HU-01: registra un nuevo producto.
    private static void registrarProducto(Inventario inventario, Scanner scanner,
                                         ArchivoInventario archivoInventario) {
        System.out.println("\n--- REGISTRAR PRODUCTO - HU-01 ---");

        try {
            System.out.print("Código: ");
            String codigo = scanner.nextLine().trim();

            if (codigo.isEmpty()) {
                System.out.println("[ERROR] El código no puede estar vacío.");
                return;
            }
            if (inventario.buscarProducto(codigo) != null) {
                System.out.println("[ERROR] Ya existe un producto con el código " + codigo + ".");
                return;
            }

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine();

            System.out.print("Cantidad (entero igual o mayor que cero): ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Precio (número igual o mayor que cero): ");
            double precio = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));

            System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
            String fechaVencimiento = scanner.nextLine().trim();

            Producto producto = new Producto(
                    codigo, nombre, categoria, cantidad, precio, fechaVencimiento);

            if (inventario.agregarProducto(producto)) {
                System.out.println("\n[ÉXITO] Producto registrado correctamente.");
                System.out.println(producto);
                guardarInventario(archivoInventario, inventario);
            } else {
                System.out.println("\n[ERROR] No se pudo registrar el producto. Verifique que el código no esté repetido.");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR] La cantidad debe ser un entero y el precio debe ser numérico.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }
    // HU-06: persiste el estado actual después de cada cambio aceptado.
    private static void guardarInventario(ArchivoInventario archivoInventario, Inventario inventario) {
        try {
            archivoInventario.guardarInventario(inventario);
            System.out.println("[ÉXITO] Inventario guardado en "
                    + archivoInventario.getRutaArchivo().toAbsolutePath());
        } catch (java.io.IOException e) {
            System.out.println("[ERROR] No se pudo guardar el inventario: " + e.getMessage());
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);


        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE INVENTARIO - FRESKO ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Buscar producto por código");
            System.out.println("3. Salir");
            System.out.println("4. Registrar producto (HU-01)");
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
                        System.out.println("\nSaliendo del módulo de inventario...");
                        break;

                    case 4:
                        registrarProducto(inventario, scanner);
                        break;

                    default:
                        System.out.println("\n[ERROR] Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[ERROR] Ingrese una opción numérica válida.");
                opcion = 0;
            }
        } while (opcion != 3);

        scanner.close();
    }

    //solicita, valida y registra los datos del producto.
    private static void registrarProducto(Inventario inventario, Scanner scanner) {
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

            // Se verifica también dentro de Inventario para evitar códigos duplicados.
            if (inventario.agregarProducto(producto)) {
                System.out.println("\n[ÉXITO] Producto registrado correctamente.");
                System.out.println(producto);
            } else {
                System.out.println("\n[ERROR] No se pudo registrar el producto. Verifique que el código no esté repetido.");
            }

        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR] La cantidad debe ser un entero y el precio debe ser numérico.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }
}

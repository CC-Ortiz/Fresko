import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);

        // Datos de prueba iniciales para comprobar las búsquedas y listados
        inventario.agregarProducto(new Producto("P001", "Manzana Roja", 30, "2026-04-20"));
        inventario.agregarProducto(new Producto("P002", "Banano Criollo", 45, "2026-04-15"));

        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE INVENTARIO - HU-02 ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Buscar producto por código");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    ArrayList<Producto> productos = inventario.listarProductos();
                    // Criterio: Si no hay productos registrados, se muestra un mensaje informativo
                    if (productos.isEmpty()) {
                        System.out.println("\n[INFO] No hay productos registrados en el inventario.");
                    } else {
                        System.out.println("\n--- LISTA DE PRODUCTOS ---");
                        // Recorrido con for-each (Criterio: Mostrar código, nombre, cantidad y fecha)
                        for (Producto p : productos) {
                            System.out.println("- " + p);
                        }
                    }
                    break;

                case 2:
                    System.out.print("\nIngrese el código del producto a buscar: ");
                    String codigoBuscado = scanner.nextLine();
                    
                    Producto encontrado = inventario.buscarProducto(codigoBuscado);
                    if (encontrado != null) {
                        System.out.println("\n[ÉXITO] Producto encontrado:");
                        System.out.println(encontrado);
                    } else {
                        // Criterio: Si el código no existe, se informa sin modificar el inventario
                        System.out.println("\n[INFO] El código '" + codigoBuscado + "' no existe en el inventario.");
                    }
                    break;

                case 3:
                    System.out.println("\nSaliendo del módulo de consulta...");
                    break;

                default:
                    System.out.println("\n[ERROR] Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 3);

        scanner.close();
    }
}.
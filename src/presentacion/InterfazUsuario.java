package presentacion;

import dominio.GestorProductos;
import dominio.Productos;

import java.util.List;
import java.util.Scanner;

public class InterfazUsuario {
    private GestorProductos gestor;
    private Scanner scanner;

    public InterfazUsuario() {
        gestor = new GestorProductos();
        scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú de Gestión de Productos ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Modificar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");


            System.out.print("Selecciona una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> modificarProducto();
                case 4 -> eliminarProducto();
                case 5 -> salir = true;

                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private void agregarProducto() {
        System.out.print("Introduce el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Introduce el precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Introduce la categoría del producto: ");
        String categoria = scanner.nextLine();

        Productos producto = new Productos(nombre, precio, categoria);
        gestor.agregarProducto(producto);
    }

    private void listarProductos() {
        List<Productos> productos = gestor.leerProductos();
        System.out.println("\nLista de productos:");
        for (Productos producto : productos) {
            System.out.println(producto);
        }
    }
    private void modificarProducto() {
        System.out.print("Introduce el nombre del producto a modificar: ");
        String nombreBuscado = scanner.nextLine().trim();
    
        System.out.print("Introduce el nuevo nombre del producto: ");
        String nuevoNombre = scanner.nextLine();
    
        System.out.print("Introduce la nueva categoría del producto: ");
        String nuevaCategoria = scanner.nextLine();
    
        System.out.print("Introduce el nuevo precio del producto: ");
        double nuevoPrecio = scanner.nextDouble();
        scanner.nextLine(); 
    
        Productos productoModificado = new Productos(nuevoNombre, nuevoPrecio, nuevaCategoria);
        gestor.modificarProducto(nombreBuscado, productoModificado);
    }
    
    private void eliminarProducto() {
        System.out.print("Introduce el nombre del producto a eliminar: ");
        String nombreBuscado = scanner.nextLine().trim();
        gestor.eliminarProducto(nombreBuscado);
    }
    
}


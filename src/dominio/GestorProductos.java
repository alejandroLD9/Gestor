package dominio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorProductos {
    private static final String NOMBRE_ARCHIVO = "productos.csv";


    public void agregarProducto(Productos producto) {
        try (FileWriter writer = new FileWriter(NOMBRE_ARCHIVO, true)) {
            writer.write( "Categoria: "+ producto.getCategoria() + "\n" + "Producto: "
             + producto.getNombre() + "\n" + "Precio: "+ producto.getPrecio() + " €\n" + "\n");

            System.out.println("Producto guardado correctamente en el fichero.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    
    public List<Productos> leerProductos() {
        List<Productos> productos = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(NOMBRE_ARCHIVO), "UTF-8")))  {
            String linea;
            String nombre = "";
            double precio = 0;
            String categoria = "";
    
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Categoria: ")) {
                    categoria = linea.substring(11); 
                } else if (linea.startsWith("Producto: ")) {
                    nombre = linea.substring(10); 
                } else if (linea.startsWith("Precio: ")) {
                    precio = Double.parseDouble(linea.substring(8).replace(" €", "")); 
                } else if (linea.isEmpty()) {
                    
                    productos.add(new Productos(nombre, precio, categoria));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    
        return productos;
    }
    public void modificarProducto(String nombreBuscado, Productos productoModificado) {
        List<Productos> productos = leerProductos();
        boolean encontrado = false;
    
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(NOMBRE_ARCHIVO), "UTF-8")) {
            for (Productos producto : productos) {
                if (producto.getNombre().trim().equalsIgnoreCase(nombreBuscado.trim())) {
                    
                    writer.write("Categoria: " + productoModificado.getCategoria() + "\n" 
                               + "Producto: " + productoModificado.getNombre() + "\n" 
                               + "Precio: " + productoModificado.getPrecio() + " \n\n");
                    encontrado = true;
                } else {
                   
                    writer.write("Categoria: " + producto.getCategoria() + "\n" 
                               + "Producto: " + producto.getNombre() + "\n" 
                               + "Precio: " + producto.getPrecio() + " \n\n");
                }
            }
            if (encontrado) {
                System.out.println("Producto modificado correctamente.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Error al modificar el fichero: " + e.getMessage());
        }
    }
    public void eliminarProducto(String nombreBuscado) {
        List<Productos> productos = leerProductos();
        boolean encontrado = false;
    
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(NOMBRE_ARCHIVO), "UTF-8")) {
            for (Productos producto : productos) {
                if (!producto.getNombre().trim().equalsIgnoreCase(nombreBuscado.trim())) {
                    
                    writer.write("Categoria: " + producto.getCategoria() + "\n" 
                               + "Producto: " + producto.getNombre() + "\n" 
                               + "Precio: " + producto.getPrecio() + " \n\n");
                } else {
                    encontrado = true; 
                }
            }
            if (encontrado) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Error al modificar el fichero: " + e.getMessage());
        }
    }
    
    
}


package dominio;

public class Productos {
    private String nombre;
    private double precio;
    private String categoria;

    public Productos(String nombre, double precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return categoria + ": " + nombre + " " + precio + " EUR";
    }
}

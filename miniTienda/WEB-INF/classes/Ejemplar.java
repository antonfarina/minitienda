public class Ejemplar {

    private final String modelo;
    private final String marca;
    private final String color;
    private final Integer precio;
    private final Integer cantidad;
    private final Integer precioTotal;

    public Ejemplar(String modelo, String marca, String color, Integer precio, Integer cantidad, Integer precioTotal) {
        this.modelo = modelo;
        this.marca = marca;
        this.color = color;
        this.precio = precio;
        this.precioTotal = precioTotal;
        this.cantidad = cantidad;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getColor() {
        return color;
    }

    public Integer getPrecio() {
        return precio;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }    
}

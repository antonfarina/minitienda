package general;

import java.io.Serializable;
import java.util.Objects;

public class Ejemplar2 implements Serializable {

    private final String modelo;
    private final String marca;
    private final String color;
    private final Integer precio;
    private Integer cantidad;
    private Integer precioTotal;

    public Ejemplar2(String modelo, String marca, String color, Integer precio, Integer cantidad, Integer precioTotal) {
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

    public void setPrecioTotal(Integer p){
      precioTotal+=p;
    }

    public void setCantidad(Integer c){
      cantidad+=c;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.modelo);
        hash = 79 * hash + Objects.hashCode(this.marca);
        hash = 79 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ejemplar2 other = (Ejemplar2) obj;
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        return Objects.equals(this.color, other.color);
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "modelo=" + modelo + ", marca=" + marca + ", color=" + color + ", precio=" + precio + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal + '}';
    }
}

package general;

import java.io.Serializable;
public class Pedido implements Serializable {
    private Usuario usuario;
    private Integer precio;
    private Integer narticulos;
    private Integer numero;

    public Pedido() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getNarticulos() {
        return narticulos;
    }

    public void setNarticulos(Integer narticulos) {
        this.narticulos = narticulos;
    }

    public Integer getNumeros() {
        return numero;
    }

    public void setNumeros(Integer numero) {
        this.numero = numero;
    }
}

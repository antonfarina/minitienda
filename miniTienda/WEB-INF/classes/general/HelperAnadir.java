package general;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelperAnadir extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //añadimos al carrito el nuevo elemento a traves de la sumaSesion
        // Generamos un objeto sesion
        HttpSession sesion = request.getSession(true);

        String movil = request.getParameter("movil");
        String color = request.getParameter("color");
        Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));

        // Recuperamos informacion del movil
        StringTokenizer t = new StringTokenizer(movil, "|");
        String modelo = t.nextToken();
        String marca = t.nextToken();
        Integer precio = Integer.parseInt(t.nextToken().replace('\u20ac', ' ').trim());
        Integer precioTotal = precio * Integer.parseInt(request.getParameter("cantidad"));

        //creamos un ejemplar
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setModelo(modelo);
        ejemplar.setMarca(marca);
        ejemplar.setColor(color);
        ejemplar.setPrecio(precio);
        ejemplar.setCantidad(cantidad);
        ejemplar.setPrecioTotal(precioTotal);

        //recuperamos el carrito de la compra
        ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
        if (carrito == null) {
            System.out.println("El carrito de compra esta vacio");
            // Inicializamos el atributo carrito
            sesion.setAttribute("carrito", new ArrayList<>());
            carrito = (ArrayList) sesion.getAttribute("carrito");
        }
        //comprobamos si ya se habia adquirido un movil igual para aumentar la cantidad

        if (carrito.contains(ejemplar)) {
            for (int i = 0; i < carrito.size(); i++) {
                System.out.println(carrito.get(i));
                if (carrito.get(i).equals(ejemplar)) {
                    carrito.get(i).setPrecioTotal(carrito.get(i).getPrecioTotal() + ejemplar.getCantidad() * ejemplar.getPrecio());
                    carrito.get(i).setCantidad(carrito.get(i).getCantidad() + ejemplar.getCantidad());
                    break;
                }
            }
        } else {
            //añadimos el ejemplar nuevo al carrito
            carrito.add(ejemplar);
        }
        //lo guardamos en la sesion
        sesion.setAttribute("carrito", carrito);
        //buscamos el atributo de total de compra y si no existe lo creamos
        Integer totalCompra = (Integer) sesion.getAttribute("totalCompra");
        if (totalCompra == null) {
            System.out.println("El precio total no esta inicializado");
            // Inicializamos el atributo totalCompra
            sesion.setAttribute("totalCompra", new Integer(0));
            totalCompra = (Integer) sesion.getAttribute("totalCompra");
        }
        //aumentamos el precio total del pedido
        totalCompra += precioTotal;

        //lo guardamos en la sesion
        sesion.setAttribute("totalCompra", totalCompra);
        gotoPage("/carrito.jsp", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

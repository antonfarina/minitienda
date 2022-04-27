import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet para almacenar CDs seleccionados por el usuario
 */
public class Accion2 extends HttpServlet {

    // Metodo POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        request.setCharacterEncoding("UTF-8");
    }

    // Metodo GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Ejecutamos en funcion de la accion del usuario
        // Si Accion es "añadir al carrito"
        if (request.getParameter("anadir") != null) {
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

            Ejemplar ejemplar = new Ejemplar(modelo, marca, color, precio, cantidad, precioTotal);
            // Recuperamos sumaSesion de la sesion y si no existe, creamos este atributo
            ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
            if (carrito == null) {
                System.out.println("El carrito de compra esta vacio");
                // Inicializamos el atributo carrito
                sesion.setAttribute("carrito", new ArrayList<>());
                carrito = (ArrayList) sesion.getAttribute("carrito");
            }

            if(carrito.contains(ejemplar)){
              for(Ejemplar e:carrito){
                if(e.equals(ejemplar)){
                  e.setPrecioTotal(ejemplar.getCantidad()*ejemplar.getPrecio());
                  e.setCantidad(ejemplar.getCantidad());
                }
              }
            }
            else{
              //añadimos el ejemplar al carrito
              carrito.add(ejemplar);
            }
            
            //lo guardamos en la sesion
            sesion.setAttribute("carrito", carrito);
        } // Si Accion es "ver"...
        else if (request.getParameter("ver") != null) {
            //abrimos el carrito de la compra
            gotoPage("/MiniTienda", request, response);
        }
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

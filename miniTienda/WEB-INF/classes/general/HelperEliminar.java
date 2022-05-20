package general;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelperEliminar extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //obtenemos el indice el elemento a eliminar
      Integer i = Integer.parseInt(request.getParameter("eliminado"));
      // Generamos un objeto sesion
      HttpSession sesion = request.getSession(true);
      ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
      if (carrito == null) {
          System.out.println("El carrito de compra esta vacio");
          // Inicializamos el atributo carrito
          sesion.setAttribute("carrito", new ArrayList<>());
          carrito = (ArrayList) sesion.getAttribute("carrito");
      }

      //buscamos el atributo de total de compra y si no existe lo creamos
      Integer totalCompra = (Integer) sesion.getAttribute("totalCompra");
      if (totalCompra == null) {
          // Inicializamos el atributo totalCompra
          sesion.setAttribute("totalCompra", new Integer(0));
          totalCompra = (Integer) sesion.getAttribute("totalCompra");
      }
      //decrementamos el precio total del pedido
      totalCompra -= carrito.get(i).getPrecioTotal();
      //quitamos el articulo
      carrito.remove(carrito.get(i));
      //lo guardamos en la sesion
      sesion.setAttribute("carrito", carrito);
      //lo guardamos en la sesion
      sesion.setAttribute("totalCompra", totalCompra);
      //abrimos el carrito de la compra
      gotoPage("/carrito.jsp", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

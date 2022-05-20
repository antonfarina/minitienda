package general;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelperPagar extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //eliminamos los atributos de la sesion
      HttpSession sesion = request.getSession(true);
      ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
      carrito.clear();
      sesion.setAttribute("carrito", carrito);
      //buscamos el atributo de total de compra y si no existe lo creamos
      Integer totalCompra = (Integer) sesion.getAttribute("totalCompra");
      totalCompra = 0;
      sesion.setAttribute("totalCompra", totalCompra);
      //volvemos a la pagina principal
      gotoPage("/index.html", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

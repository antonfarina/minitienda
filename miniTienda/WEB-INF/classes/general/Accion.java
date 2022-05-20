package general;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Accion extends HttpServlet {

    // Metodo POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("pagaryvolver") != null) {
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
    }

    // Metodo GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ejecutamos en funcion de la accion del usuario
        // Si Accion es "anadir"
        if (request.getParameter("anadir") != null) {
            gotoPage("/HelperAnadir", request, response);
        }else if(request.getParameter("irapagar") != null){
            gotoPage("/registro.jsp", request, response);
        }else if(request.getParameter("volveralcarrito")!=null){
            gotoPage("/carrito.jsp", request, response);
        }else if(request.getParameter("eliminar")!=null){
            gotoPage("/HelperEliminar", request, response);
        }else if(request.getParameter("registrar")!=null){
            gotoPage("/HelperRegistrar", request, response);
        }
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

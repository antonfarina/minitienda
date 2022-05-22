package general;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Controlador extends HttpServlet {

    // Metodo GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // Ejecutamos en funcion de la Controlador del usuario
        // Si Controlador es "anadir"
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
        }else if (request.getParameter("pagaryvolver") != null) {
            gotoPage("/HelperPagar", request, response);
        }
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

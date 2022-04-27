
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet para almacenar CDs seleccionados por el usuario
 */
public class Accion extends HttpServlet {

    // Metodo POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        request.setCharacterEncoding("UTF-8");
    }

    // Metodo GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Ejecutamos en funcion de la accion del usuario
        // Si Accion es "suma"....
        if (request.getParameter("suma") != null) {
            // SumaNumeros
            gotoPage("/SumaNumeros", request, response);
        } // Si Accion es "multiplica"...
        else if (request.getParameter("multiplica") != null) {
            // MultiplicaNumeros
            gotoPage("/MultiplicaNumeros", request, response);
        }

    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

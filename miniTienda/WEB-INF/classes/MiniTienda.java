import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

public class MiniTienda extends HttpServlet {

    private Integer totalCompra=0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Generamos un objeto sesion
        HttpSession sesion = request.getSession(true);

        // Recuperamos el carrito de la sesion y si no existe, creamos este atributo
        ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
        if (carrito == null) {
            System.out.println("El carrito de compra esta vacio");
            // Inicializamos el atributo carrito
            sesion.setAttribute("carrito", new ArrayList<>());
            carrito = (ArrayList) sesion.getAttribute("carrito");
        }

        // Generamos pagina de salida
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String pagina = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//ES\">\n"
                + "<HTML>\n"
                + "<HEAD><TITLE>Carrito de compra:</TITLE></HEAD>\n"
                + "<BODY BGCOLOR=\"#FDF5E6\">\n"
                + "<center> <H1>Compra </H1>\n"
                + "\n\n"
                + "<table border=\"0\" cellpadding=\"0\" width=\"75%\" bgcolor=\"#FFFFFF\">"
                + "<tr>"
                + "<th>Marca</th>"
                + "<th>Modelo</th>"
                + "<th>Color</th>"
                + "<th>Precio por unidad</th>"
                + "<th>Cantidad</th>"
                + "<th>Precio total</th>"
                + "</tr>";

        for (Ejemplar e : carrito) {
            pagina += "<tr>"
                    + "<td>" + e.getMarca() + "</td>"
                    + "<td>" + e.getModelo() + "</td>"
                    + "<td>" + e.getColor() + "</td>"
                    + "<td>" + e.getPrecio() + "</td>"
                    + "<td>" + e.getCantidad() + "</td>"
                    + "<td>" + e.getPrecioTotal() + "</td>"
                    + "</tr>";
            totalCompra += e.getPrecioTotal();
        }
        pagina += "</table>"
                + "<table>"
                + "<tr>"
                + "<td>TOTAL DE COMPRA</td>"
                + "<td>" + totalCompra + "</td>"
                + "</tr>"
                + "</table>"
                + "<p>"
                + "<a HREF=\"/minitienda/index.html\">Seguir comprando</a>"
                + "</center>"
                + "</BODY></HTML>";

        out.println(pagina);
    }
}

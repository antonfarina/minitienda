import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class Carrito extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Generamos un objeto sesion
        HttpSession sesion = request.getSession(true);

        // Recuperamos el carrito de la sesion y si no existe, creamos este atributo
        ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
        if (carrito == null) {
            // Inicializamos el atributo carrito
            sesion.setAttribute("carrito", new ArrayList<>());
            carrito = (ArrayList) sesion.getAttribute("carrito");
        }
        //buscamos el atributo de total de compra y si no existe lo creamos
        Integer totalCompra = (Integer) sesion.getAttribute("totalCompra");
        if (totalCompra == null) {
            // Inicializamos el atributo carrito
            sesion.setAttribute("totalCompra", new Integer(0));
            totalCompra = (Integer) sesion.getAttribute("totalCompra");
        }

        // Generamos pagina de salida
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String pagina = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//ES\">\n"
                + "<HTML>\n"
                + "<HEAD>"
                + "<TITLE>Carrito de compra:</TITLE>"
                + "<meta charset=\"UTF-8\">"
                + "<link rel=\"stylesheet\" href=\"css/carrito.css\">"
                + "</HEAD>"
                + "<div><H1>Carrito de la compra</H1>"
                + "<form method=\"get\" action=\"Accion\">"
                + "<table>"
                + "<tr>"
                + "<th>Marca</th>"
                + "<th>Modelo</th>"
                + "<th>Color</th>"
                + "<th>Precio por unidad</th>"
                + "<th>Cantidad</th>"
                + "<th>Precio total</th>"
                + "<th></th>"
                + "</tr>";

        for (int i = 0; i < carrito.size(); i++) {
            pagina += "<tr>"
                    + "<td>" + carrito.get(i).getMarca() + "</td>"
                    + "<td>" + carrito.get(i).getModelo() + "</td>"
                    + "<td>" + carrito.get(i).getColor() + "</td>"
                    + "<td>" + carrito.get(i).getPrecio() + "</td>"
                    + "<td>" + carrito.get(i).getCantidad() + "</td>"
                    + "<td>" + carrito.get(i).getPrecioTotal() + "</td>"
                    + "<td>"
                    + "<input type=\"radio\" name=\"eliminado\" value=\"" + i + "\">"
                    + "</td>"
                    + "</tr>";
        }
        pagina += "<tr>"
                + "<td></td>"
                + "<td></td>"
                + "<td></td>"
                + "<td></td>"
                + "<td class=\"total\">TOTAL DE COMPRA</td>"
                + "<td class=\"total\">" + totalCompra + "</td>"
                + "<td>"
                + "<input type=\"submit\" name=\"eliminar\" id=\"btneliminar\" value=\"Eliminar\">"
                + "</form>"
                + "</td>"
                + "</tr>"
                + "</table>"
                + "<p><a HREF=\"/minitienda/index.html\">Seguir comprando</a></p>"
                + "<form method=\"get\" action=\"Accion\">"
                + "<input type=\"submit\" name=\"irapagar\" value=\"Proceder al pago\"></form>"
                + "</div>"
                + "</BODY></HTML>";

        out.println(pagina);
    }
}

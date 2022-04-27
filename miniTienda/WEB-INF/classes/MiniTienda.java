import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

public class MiniTienda extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        //añadimos el ejemplar al carrito
        carrito.add(ejemplar);
        //lo guardamos en la sesion
        sesion.setAttribute("carrito", carrito);

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
                + "<th>"
                + "<td>Marca</td>"
                + "<td>Modelo</td>"
                + "<td>Color</td>"
                + "<td>Precio por unidad</td>"
                + "<td>Cantidad</td>"
                + "<td>Precio total</td>"
                + "</th>";

        for (Ejemplar e : carrito) {
            pagina += "<tr>"
                    + "<td>" + e.getMarca() + "</td>"
                    + "<td>" + e.getModelo() + "</td>"
                    + "<td>" + e.getColor() + "</td>"
                    + "<td>+" + e.getPrecio() + "</td>"
                    + "<td>" + e.getCantidad() + "</td>"
                    + "<td>" + e.getPrecioTotal() + "</td>"
                    + "</tr>";
        }
        pagina += "</table>"
                + "<p>"
                + "<a HREF=\"/MiniTienda/index.html\">Seguir comprando</a>"
                + "</center>"
                + "</BODY></HTML>";

        out.println(pagina);
    }
}

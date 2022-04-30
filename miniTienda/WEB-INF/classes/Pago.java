import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Pago extends HttpServlet {

    private Integer totalCompra = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Generamos un objeto sesion
        HttpSession sesion = request.getSession(true);

        //buscamos el atributo de total de compra y si no existe lo creamos (siempre existira)
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
                + "<HTML lang=\"es\">"
                + "<HEAD>"
                + "<TITLE>Plataforma de pago:</TITLE>"
                + "<meta charset=\"UTF-8\">"
                + "</HEAD>"
                + "<BODY BGCOLOR=\"#FDF5E6\">"
                + "<center> <H1>Total del pedido:</H1>"
                + "<center> <H2>" + totalCompra + " euros</H2>"
                + "<form method=\"get\" action=\"Accion\">"
                + "<input type=\"submit\" name=\"volveralcarrito\" value=\"Volver al carrito\">"
                + "</form>"
                + "<form method=\"get\" action=\"Accion\">"
                + "<input type=\"submit\" name=\"pagaryvolver\" value=\"Pagar y volver a la pagina principal\">"
                + "</form>"
                + "</BODY></HTML>";
        out.println(pagina);
    }
}

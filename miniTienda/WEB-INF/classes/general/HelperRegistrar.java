package general;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelperRegistrar extends HttpServlet {
      private BBDD baseDatos;

      public void init(ServletConfig config) throws ServletException{
        super.init(config);
        baseDatos = new BBDD();
      }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);
        Usuario u = null;
        u = baseDatos.consultarUsuario(request.getParameter("correo"));
        if(u == null){
          u = new Usuario();
          u.setNombre(request.getParameter("nombre"));
          u.setApellido1(request.getParameter("apellido1"));
          u.setApellido2(request.getParameter("apellido2"));
          u.setCorreo(request.getParameter("correo"));
          u.setDireccion(request.getParameter("direccion"));
          u.setTelefono(request.getParameter("telefono"));
          u.setTarjeta(request.getParameter("tarjeta"));
          u.setTipo(request.getParameter("tipo"));
          baseDatos.insertarUsuario(u);
        }
        Pedido p = new Pedido();
        p.setUsuario(u);
        Integer totalCompra = (Integer) sesion.getAttribute("totalCompra");
        p.setPrecio(totalCompra);
        Integer articulos = 0;
        ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
        for(Ejemplar e: carrito){
          articulos += e.getCantidad();
        }
        p.setNarticulos(articulos);
        p.setNumero(baseDatos.insertarPedido(p));
        sesion.setAttribute("pedido", p);
        gotoPage("/pago.jsp", request, response);
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

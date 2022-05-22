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
          //insertamos el usuario en la base de datos
          baseDatos.insertarUsuario(u);
        }else{
          u.setNombre(request.getParameter("nombre"));
          u.setApellido1(request.getParameter("apellido1"));
          u.setApellido2(request.getParameter("apellido2"));
          u.setDireccion(request.getParameter("direccion"));
          u.setTelefono(request.getParameter("telefono"));
          u.setTarjeta(request.getParameter("tarjeta"));
          u.setTipo(request.getParameter("tipo"));
          //modificamos los datos del usuario con los que acaba de rellenar
          baseDatos.modificarUsuario(u);
        }
        //creamos un nuevo pedido
        Pedido p = new Pedido();
        p.setUsuario(u);
        Integer totalCompra = (Integer) sesion.getAttribute("totalCompra");
        //le añadimso el precio del pedido
        p.setPrecio(totalCompra);
        Integer articulos = 0;
        ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
        for(Ejemplar e: carrito){
          articulos += e.getCantidad();
        }
        //añadimos el numero de articulos del pedido
        p.setNarticulos(articulos);
        //insertamos el pedido y le ponemos como id el numero del pedido autoincremental de la base de datos
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

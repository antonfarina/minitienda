import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Accion extends HttpServlet {

    // Metodo POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        request.setCharacterEncoding("UTF-8");
    }

    // Metodo GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ejecutamos en funcion de la accion del usuario
        // Si Accion es "añadir al carrito"
        if (request.getParameter("anadir") != null) {
            //añadimos al carrito el nuevo elemento a traves de la sumaSesion
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
            //comprobamos si ya se habia adquirido un movil igual para aumentar la cantidad
            if(carrito.contains(ejemplar)){
              for(int i=0; i<carrito.size(); i++){
                if(carrito.get(i).equals(ejemplar)){
                  carrito.get(i).setPrecioTotal(ejemplar.getCantidad()*ejemplar.getPrecio());
                  carrito.get(i).setCantidad(ejemplar.getCantidad());
                  break;
                }
              }
            }else{
              //añadimos el ejemplar nuevo al carrito
              carrito.add(ejemplar);
            }
            //lo guardamos en la sesion
            sesion.setAttribute("carrito", carrito);
            //buscamos el atributo de total de compra y si no existe lo creamos
            Integer totalCompra = (Integer) sesion.getAttribute("totalCompra");
            if (totalCompra == null) {
                // Inicializamos el atributo totalCompra
                sesion.setAttribute("totalCompra", new Integer(0));
                totalCompra = (Integer) sesion.getAttribute("totalCompra");
            }
            //aumentamos el precio total del pedido
            totalCompra += ejemplar.getPrecio();

            //lo guardamos en la sesion
            sesion.setAttribute("totalCompra", totalCompra);
            gotoPage("/index.html", request, response);
        } // Si Accion es "ver"...
        else if (request.getParameter("ver") != null) {
            //abrimos el carrito de la compra
            gotoPage("/Carrito", request, response);
        }else if(request.getParameter("pagar") != null){
            gotoPage("/Pago", request, response);
        }else if(request.getParameter("pagaryvolver")!=null){
          //eliminamos los atributos de la sesion
          HttpSession sesion = request.getSession(true);
          if(sesion.getAttribute("carrito")!=null){
            sesion.removeAttribute("carrito");
          }
          if(sesion.getAttribute("totalCompra")!=null){
            sesion.removeAttribute("totalCompra");
          }
          //volvemos a la pagina principal
          gotoPage("/index.html", request, response);
        }else if(request.getParameter("volveralcarrito")!=null){
          //abrimos el carrito de la compra
          gotoPage("/Carrito", request, response);
        }else if(request.getParameter("eliminar")!=null){
          //obtenemos el indice el elemento a eliminar
          Integer i = Integer.parseInt(request.getParameter("eliminado"));
          // Generamos un objeto sesion
          HttpSession sesion = request.getSession(true);
          ArrayList<Ejemplar> carrito = (ArrayList) sesion.getAttribute("carrito");
          if (carrito == null) {
              System.out.println("El carrito de compra esta vacio");
              // Inicializamos el atributo carrito
              sesion.setAttribute("carrito", new ArrayList<>());
              carrito = (ArrayList) sesion.getAttribute("carrito");
          }

          //buscamos el atributo de total de compra y si no existe lo creamos
          Integer totalCompra = (Integer) sesion.getAttribute("totalCompra");
          if (totalCompra == null) {
              // Inicializamos el atributo totalCompra
              sesion.setAttribute("totalCompra", new Integer(0));
              totalCompra = (Integer) sesion.getAttribute("totalCompra");
          }
          //decrementamos el precio total del pedido
          totalCompra -= carrito.get(i).getPrecio();
          //quitamos el articulo
          carrito.remove(i);
          //lo guardamos en la sesion
          sesion.setAttribute("carrito", carrito);
          //lo guardamos en la sesion
          sesion.setAttribute("totalCompra", totalCompra);
          //abrimos el carrito de la compra
          gotoPage("/Carrito", request, response);
        }
    }

    private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Creamos objeto RequestDispatcher
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}

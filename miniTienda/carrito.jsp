<%@page import="java.io.*" %>
  <%@page import="java.util.*" %>
    <%@page session="true" %>
      <%@page import="general.Ejemplar" %>

        <%
    //buscamos el atributo de total de compra y si no existe lo creamos
    Integer totalCompra = (Integer) session.getAttribute("totalCompra");
    ArrayList<Ejemplar> carrito = (ArrayList) session.getAttribute("carrito");
    %>
          <!DOCTYPE HTML>
          <HTML>
            <HEAD>
              <TITLE>Carrito de compra:</TITLE>
              <meta charset="UTF-8">"
                <link rel="stylesheet" href="css/carrito.css"></HEAD>
                <div>
                  <H1>Carrito de la compra</H1>
                  <form method="get" action="Accion">
                    <table>
                      <tr>
                        <th>Marca</th>
                        <th>Modelo</th>
                        <th>Color</th>
                        <th>Precio por unidad</th>
                        <th>Cantidad</th>
                        <th>Precio total</th>
                        <th></th>
                      </tr>
                      <%for (int i = 0; i < carrito.size(); i++) {%>
                        <tr>
                          <td><%=carrito.get(i).getMarca()%></td>
                          <td><%=carrito.get(i).getModelo()%></td>
                          <td><%=carrito.get(i).getColor()%></td>
                          <td><%=carrito.get(i).getPrecio()%></td>
                          <td><%=carrito.get(i).getCantidad()%></td>
                          <td><%=carrito.get(i).getPrecioTotal()%></td>
                          <td>
                            <input type="radio" name="eliminado" value="<%=i%>"></td>
                          </tr>
                        <%} %>

                        <tr>
                          <td></td>
                          <td></td>
                          <td></td>
                          <td></td>
                          <td class="total">TOTAL DE COMPRA</td>
                          <td class="total"><%=totalCompra%></td>
                          <td>
                            <input type="submit" name="eliminar" id="btneliminar" value="Eliminar"></form>
                          </td>
                        </tr>
                      </table>
                      <p>
                        <a HREF="/minitienda/index.html">Seguir comprando</a>
                      </p>
                      <form method="get" action="Accion">
                        <input type="submit" name="irapagar" value="Proceder al pago"></form>
                      </div>
                    </BODY>
                  </HTML>

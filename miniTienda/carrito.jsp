<%@page import="java.io.*" %>
  <%@page import="java.util.*" %>
    <%@page session="true" %>
      <%@page import="general.Ejemplar2" %>

          <!DOCTYPE HTML>
          <HTML>
            <HEAD>
              <TITLE>Carrito de compra:</TITLE>
              <meta charset="UTF-8">"
                <link rel="stylesheet" href="css/carrito.css"></HEAD>
                <body>
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
                        <%!int i=0;%>
                          <c:forEach var="ejemplar" items="${carrito}">
                            <tr>
                              <td>${ejemplar.marca}</td>
                              <td>${ejemplar.modelo}</td>
                              <td>${ejemplar.color}</td>
                              <td>${ejemplar.precio}</td>
                              <td>${ejemplar.cantidad}</td>
                              <td>${ejemplar.precioTotal}</td>
                              <td>
                                <input type="radio" name="eliminado" value="<%=i%>"></td>
                              </tr>
                              <%i++;%>
                              </c:forEach>

                              <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td class="total">TOTAL DE COMPRA</td>
                                <td class="total">${totalCompra}</td>
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

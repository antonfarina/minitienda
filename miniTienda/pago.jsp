<%@page import="java.io.*" %>
<%@page session="true"%>
<%@page pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<HTML lang="es">
  <HEAD>
    <TITLE>Plataforma de pago:</TITLE>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/pago.css">
  </HEAD>
  <BODY>
    <div class="contenedor">
      <H1>DETALLES DEL PEDIDO</H1>
      <h3>Número de pedido: ${pedido.numero}</h3>
      <h3>Nº de artículos: ${pedido.narticulos}</h3>
      <h3>Importe total: ${pedido.precio}€</h3>
      <h3>Nombre: ${pedido.usuario.nombre}</h3>
      <h3>Teléfono: ${pedido.usuario.telefono}</h3>
      <h3>Correo electrónico: ${pedido.usuario.correo}</h3>
      <h3>Dirección: ${pedido.usuario.direccion}</h3>
      <h3>Método de pago: tarjeta de ${pedido.usuario.tipo} acabada en ${fn:substring(pedido.usuario.tarjeta, 11, 15)}</h3>

      <div class="botones">
        <form method="get" action="Controlador">
          <input type="submit" name="volveralcarrito" value="Volver al carrito" id="volver">
        </form>
        <form method="get" action="Controlador">
          <input type="submit" name="pagaryvolver" value="Pagar">
        </form>
      </div>
    </div>
  </BODY>

</HTML>

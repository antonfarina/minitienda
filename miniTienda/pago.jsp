<%@page import="java.io.*" %>
<%@page session="true"%>
<%@page pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>

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
      <h3>Nombre: </h3>
      <h3>Correo: </h3>
      <h3>Teléfono: </h3>
      <h3>Correo electrónico: </h3>
      <h3>Dirección: </h3>
      <h3>Método de pago: </h3>
      <h3>Nº de artículos: </h3>
      <h3>Importe total: € </h3>
      <form method="get" action="Accion">
        <input type="submit" name="volveralcarrito" value="Volver al carrito">
      </form>
      <form method="post" action="Accion">
        <input type="submit" name="pagaryvolver" value="Pagar">
      </form>
    </div>
  </BODY>

</HTML>

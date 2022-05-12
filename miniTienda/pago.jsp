<%@page import="java.io.*" %>
<%@page session="true" %>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<HTML lang="es">
  <HEAD>
    <TITLE>Plataforma de pago:</TITLE>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/pago.css">
  </HEAD>
  <BODY>
    <div class="contenedor">
      <H1>PLATAFORMA DE PAGO</H1>
      <H2>Importe total: ${totalCompra} euros</H2>
      <form method="get" action="Accion">
        <input type="submit" name="volveralcarrito" value="Volver al carrito">
      </form>
      <form method="post" action="Accion">
        <input type="submit" name="pagaryvolver" value="Pagar">
      </form>
    </div>
  </BODY>

</HTML>

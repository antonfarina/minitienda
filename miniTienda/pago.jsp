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
      <h3>Nombre: ${pedido.usuario.nombre}</h3>
      <h3>Teléfono: ${pedido.usuario.telefono}</h3>
      <h3>Correo electrónico: ${pedido.usuario.correo}</h3>
      <h3>Dirección: ${pedido.usuario.direccion}</h3>
      <h3>Método de pago: tarjeta de ${pedido.usuario.tipo} </h3>
      <h3>Número de pedido: ${pedido.numero}</h3>
      <h3>Nº de artículos: ${pedido.narticulos}</h3>
      <h3>Importe total: ${pedido.precio}€ </h3>
      <form method="get" action="Accion">
        <input type="submit" name="volveralcarrito" value="Volver al carrito">
      </form>
      <form method="post" action="Accion">
        <input type="submit" name="pagaryvolver" value="Pagar">
      </form>
    </div>
  </BODY>

</HTML>

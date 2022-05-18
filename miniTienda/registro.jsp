<%@page import="java.io.*" %>
  <%@page session="true" %>
  <%@page pageEncoding="UTF-8"%>
  <%@ page isELIgnored = "false" %>

    <!DOCTYPE HTML>
    <html lang="es">

    <head>
      <title>Registro</title>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="css/registro.css">
    </head>

    <body>
      <div id="cont">
        <H1>DATOS DEL COMPRADOR</H1>
        <form method="get" action="Accion">
          <div id="cliente">
            <label>Nombre:</label>
            <input type="text" name="nombre" pattern="[a-zA-ZáéíóúñÑü]+" required>
            <label>Primer apellido:</label>
            <input type="text" name="apellido1" pattern="[a-zA-ZáéíóúñÑü]+" required>
            <label>Segundo apellido:</label>
            <input type="text" name="apellido2" pattern="[a-zA-ZáéíóúñÑü]+" required>
            <label>Correo electrónico:</label>
            <input type="email" name="correo" required>
            <label>Número de teléfono: </label>
            <input type="text" name="telefono" pattern="[1-9][0-9]{8}" required>
            <label>Dirección: </label>
            <input type="text" name="direccion" required>
            <label>Tarjeta:</label>
            <input type="text" name="tarjeta" pattern="[0-9]{16}" required>
            <label>Tipo de tarjeta:</label>
            <select name="tipo">
              <option value="credito">Crédito</option>
              <option value="debito">Débito</option>
            </select>
          </div>
          <div class="botones">
            <input type="reset" value="Borrar datos">
            <input type="submit" name="registrar" value="Continuar" />
          </div>
        </form>
      </div>
    </body>

    </html>

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
        <form method="get" action="Controlador">
          <div id="cliente">
            <label>Nombre:</label>
            <input type="text" name="nombre" pattern="[a-zA-ZáéíóúñÑü ]+" title="Introduce un nombre válido sin carácteres especiales" required>
            <label>Primer apellido:</label>
            <input type="text" name="apellido1" pattern="[a-zA-ZáéíóúñÑü\- ]+" title="Introduce un apellido válido, puede estar unido por - " required>
            <label>Segundo apellido:</label>
            <input type="text" name="apellido2" pattern="[a-zA-ZáéíóúñÑü\- ]+" title="Introduce un apellido válido, puede estar unido por - " required>
            <label>Correo electrónico:</label>
            <input type="email" name="correo" required>
            <label>Número de teléfono: </label>
            <input type="text" name="telefono" pattern="[1-9][0-9]{8}" title="Introduce un número de teléfono de 9 dígitos" required>
            <label>Dirección: </label>
            <input type="text" name="direccion" required>
            <label>Tarjeta:</label>
            <input type="text" name="tarjeta" pattern="[0-9]{16}" title="Introduce un número de tarjeta de 16 dígitos"required>
            <label>Tipo de tarjeta:</label>
            <select name="tipo">
              <option value="crédito">Crédito</option>
              <option value="débito">Débito</option>
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

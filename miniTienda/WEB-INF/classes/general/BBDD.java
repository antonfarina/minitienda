
import java.sql.*;

public class BBDD {

    private Connection conexion;

    public BBDD() {
        String url = "";
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            System.out.println("Encontrado el driver de PostgreSQL");
            url = "jdbc:postgresql://localhost:5432/minitienda";
            //usuario y contraseña de la base de datos
            String usuario = "postgres";
            String contrasena = "1234";
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexion establecida con " + url + "...");
        } catch (java.lang.ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            System.out.println("PostgreSQL Driver no encontrado ... ");
        } catch (java.sql.SQLException e) {
            System.out.println("Conexion NO establecida con " + url);
        }
    }

    //Comprueba si el usuario ya está en la base de datos
    public boolean consultarUsuario(String correo) {
        boolean encontrado=false;
        //consulta con el usuario buscado por dni
        String consulta = "select correo from usuarios where correo=?";
        //creamos el prepareStatement
        try (PreparedStatement stmUsuario = conexion.prepareStatement(consulta)) {
            //ponemos el dni
            stmUsuario.setString(1, correo);
            //ejecutamos la consulta
            ResultSet rs = stmUsuario.executeQuery();
            while (rs.next()) {
              encontrado=true;
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return encontrado;
    }

    public void insertarUsuario(Usuario u) {
        //Se inserta el usuario introduciendo para ello los campos requeridos
        //consulta
        String consulta = "insert into usuarios(nombre,apellido1,apellido2,correo,telefono,tarjeta,tipo) "
                + "values(?,?,?,?,?,?,?)";
        //creamos el prepareStatement
        try (PreparedStatement stmUsuario = conexion.prepareStatement(consulta)) {
          //instroducimos los datos
            stmUsuario.setString(1, u.getNombre());
            stmUsuario.setString(2, u.getApellido1());
            stmUsuario.setString(3, u.getApellido2());
            stmUsuario.setString(4, u.getCorreo());
            stmUsuario.setString(5, u.getTelefono());
            stmUsuario.setString(6, u.getTarjeta());
            stmUsuario.setString(7, u.getTipo());
            //ejecutamos la insercion
            stmUsuario.executeUpdate();

            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public int insertarPedido(Pedido p){
      int numero=0;
      String consulta = "insert into pedidos(usuario,precio,narticulos) "
              + "values(?,?,?)";
      //creamos el prepareStatement
      try (PreparedStatement stmPedido = conexion.prepareStatement(consulta)) {
        //instroducimos los datos
          stmPedido.setString(1, p.getUsuario().getCorreo());
          stmPedido.setInt(2, p.getPrecio());
          stmPedido.setInt(3, p.getNarticulos());
          //ejecutamos la insercion
          stmPedido.executeUpdate();

          String consulta = "select numero from pedidos where usuario=? order by numero desc";
          //ponemos el dni
          stmUsuario.setString(1, correo);
          //ejecutamos la consulta
          ResultSet rs = stmPedido.executeQuery();
          while (rs.next()) {
            numero=rs.getInt("numero");
          }

          rs.close();

      } catch (SQLException e) {
          System.err.println(e.getMessage());
      }
      return numero;
    }

}
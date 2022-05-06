
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

    public int insertarUsuario(Usuario u) {
      int id=-1;
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

            consulta="select id from usuarios"
                        +"where nombre=?"
                        +"and apellido1=?"
                        +"and apellido2=?"
                        +"and correo=?"
                        +"and telefono=?"
                        +"and tarjeta=?"
                        +"and tipo=?"
                        +"order by id desc";
              stmUsuario.setString(1, u.getNombre());
              stmUsuario.setString(2, u.getApellido1());
              stmUsuario.setString(3, u.getApellido2());
              stmUsuario.setString(4, u.getCorreo());
              stmUsuario.setString(5, u.getTelefono());
              stmUsuario.setString(6, u.getTarjeta());
              stmUsuario.setString(7, u.getTipo());
              //ejecutamos la consulta
              ResultSet rs = stmUsuario.executeQuery();
              while (rs.next()) {
                  id=rs.getInt("id");
              }
              rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    //devuelve un usuario extraido de la base de datos con el dni que se proporciona
    public Usuario consultarUsuario(Integer id) {
        Usuario u = new Usuario();
        //consulta con el usuario buscado por dni
        String consulta = "select * from usuarios where id=?";
        //creamos el prepareStatement
        try (PreparedStatement stmUsuario = conexion.prepareStatement(consulta)) {
            //ponemos el dni
            stmUsuario.setInt(1, id);
            //ejecutamos la consulta
            ResultSet rs = stmUsuario.executeQuery();
            while (rs.next()) {
              u.setId(rs.getInt("id"));
              u.setNombre(rs.getString("nombre"));
              u.setApellido1(rs.getString("apellido1"));
              u.setApellido2(rs.getString("apellido2"));
              u.setCorreo(rs.getString("correo"));
              u.setTelefono(rs.getString("telefono"));
              u.setCorreo(rs.getString("correo"));
              u.setTelefono(rs.getString("telefono"));
              u.setTarjeta(rs.getString("tarjeta"));
              u.setTipo(rs.getString("tipo"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return u;
    }
}

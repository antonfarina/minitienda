package general;

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
    public Usuario consultarUsuario(String correo) {
        Usuario u = null;
        //consulta con el usuario buscado por dni
        String consulta = "select * from usuarios where correo=?";
        //creamos el prepareStatement
        try ( PreparedStatement stmUsuario = conexion.prepareStatement(consulta)) {
            //ponemos el dni
            stmUsuario.setString(1, correo);
            //ejecutamos la consulta
            ResultSet rs = stmUsuario.executeQuery();
            while (rs.next()) {
                u = new Usuario();
                u.setNombre(rs.getString("nombre"));
                u.setApellido1(rs.getString("apellido1"));
                u.setApellido2(rs.getString("apellido2"));
                u.setCorreo(rs.getString("correo"));
                u.setDireccion(rs.getString("direccion"));
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

    public void insertarUsuario(Usuario u) {
        //Se inserta el usuario introduciendo para ello los campos requeridos
        String consulta = "insert into usuarios(nombre, apellido1, apellido2, correo, direccion, telefono, tarjeta, tipo) "
                + "values(?,?,?,?,?,?,?,?)";
        //creamos el prepareStatement
        try ( PreparedStatement stmUsuario = conexion.prepareStatement(consulta)) {
            //instroducimos los datos
            stmUsuario.setString(1, u.getNombre());
            stmUsuario.setString(2, u.getApellido1());
            stmUsuario.setString(3, u.getApellido2());
            stmUsuario.setString(4, u.getCorreo());
            stmUsuario.setString(5, u.getDireccion());
            stmUsuario.setString(6, u.getTelefono());
            stmUsuario.setString(7, u.getTarjeta());
            stmUsuario.setString(8, u.getTipo());
            //ejecutamos la insercion
            stmUsuario.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public int insertarPedido(Pedido p) {
        int numero = 0;
        String consulta = "insert into pedidos(usuario, precio, narticulos) "
                + "values(?,?,?)";
        //creamos el prepareStatement
        try ( PreparedStatement stmPedido = conexion.prepareStatement(consulta)) {
            //instroducimos los datos
            stmPedido.setString(1, p.getUsuario().getCorreo());
            stmPedido.setInt(2, p.getPrecio());
            stmPedido.setInt(3, p.getNarticulos());
            //ejecutamos la insercion
            stmPedido.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        consulta = "select numero from pedidos where usuario=? order by numero desc";
        try ( PreparedStatement stmPedido = conexion.prepareStatement(consulta)) {

            stmPedido.setString(1, p.getUsuario().getCorreo());
            //ejecutamos la consulta
            ResultSet rs = stmPedido.executeQuery();
            if (rs.next()) {
                numero = rs.getInt("numero");
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return numero;
    }
}

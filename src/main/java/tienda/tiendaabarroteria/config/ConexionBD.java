package tienda.tiendaabarroteria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // singleton
    private static ConexionBD instancia;
    Connection conexion;

    private  final String BASEDATOS = "tienda_aborroteria";
    private  final String URL = "jdbc:mysql://localhost:3306/" + BASEDATOS + "?useSSL=false&serverTimezone=UTC";
    private  final String USUARIO = "root";
    private  final String PASSWORD = "";

    private ConexionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión a MySql establecida");
        } catch (Exception e) {
            System.out.println("Error al conectarnos a la BD" + e.getMessage());
        }
    }

    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }

        return instancia;
    }
    
    // El método debe retornar una NUEVA conexión siempre
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

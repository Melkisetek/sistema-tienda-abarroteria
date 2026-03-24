package tienda.tiendaabarroteria.config;

import java.sql.Connection;
import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {
        Connection con = ConexionBD
                .getInstancia()
                .getConnection();
        if (con != null) {
            System.out.println("✅ Conectado a MySQL con éxito: " + con);
        } else {
            System.out.println("❌ Error de conexión");
        }
    }

}

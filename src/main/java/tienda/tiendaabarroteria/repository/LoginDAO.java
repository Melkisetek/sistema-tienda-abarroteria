package tienda.tiendaabarroteria.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tienda.tiendaabarroteria.config.ConexionBD;
import tienda.tiendaabarroteria.domain.LogIn;

public class LoginDAO implements ILoginDAO{

    @Override
    public boolean loginAdmin(LogIn login) {
        final String SQL = "SELECT * FROM Administrador WHERE user=? and password=?";

        try (Connection conn = ConexionBD.getInstancia().getConnection(); 
                PreparedStatement ps = conn.prepareStatement(SQL)) {
            ResultSet rs;
            ps.setString(1, login.getUsuario());
            ps.setString(2, login.getContrasenia());
            rs = ps.executeQuery();
            if (rs.next()) {
                login.setUsuario(rs.getString("user"));
                login.setContrasenia(rs.getString("password"));
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error  al iniciar sesión: " + ex.getMessage());
        }

        return false;
    }
    
    @Override
        public boolean loginEmpleado(LogIn login) {
        final String SQL = "SELECT * FROM employee WHERE user=? and password=?";

        try (Connection conn = ConexionBD.getInstancia().getConnection(); 
                PreparedStatement ps = conn.prepareStatement(SQL)) {
            ResultSet rs;
            ps.setString(1, login.getUsuario());
            ps.setString(2, login.getContrasenia());
            rs = ps.executeQuery();
            if (rs.next()) {
                login.setUsuario(rs.getString("user"));
                login.setContrasenia(rs.getString("password"));
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error  al iniciar sesión: " + ex.getMessage());
        }

        return false;
    }

    //public static void main(String[] args) {
//        System.out.println("**** PRUEBAS ****\n");
//         LoginDAO login = new LoginDAO();
//         
//        // Admin
//       LogIn loginAdmin = new LogIn("Admin", "admin");
//       var encontrado = login.loginAdmin(loginAdmin);
//       if(encontrado){
//           System.out.println("Bienvendido: "+loginAdmin.getUsuario()+": "+loginAdmin);
//       }else{
//           System.out.println("Error no econtrado: "+encontrado);
//       }
    //}
}

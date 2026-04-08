package tienda.tiendaabarroteria.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.tiendaabarroteria.config.ConexionBD;
import tienda.tiendaabarroteria.domain.Persona;

public class SellerDAO implements ISellerDAO {

    @Override
    public List<Persona> listarEmpleados() {
        List<Persona> empleados = new ArrayList<>();
        ResultSet rs;
        final String SQL = "SELECT * FROM employee ORDER BY ID";

        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setId(rs.getInt("ID"));
                persona.setUsuario(rs.getString("user"));
                persona.setContrasenia(rs.getString("password"));
                persona.setGenero(rs.getString("genero"));

                empleados.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("Error listar empleados registrados: " + e.getMessage());
        }

        return empleados;
    }

    @Override
    public boolean agregarEmpleado(Persona persona) {
        final String SQL_INSERT = "INSERT INTO employee(user, password, genero) VALUES(?, ?, ?)";

        // Al declarar la conexión aquí, Java la cierra automáticamente al terminar
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {
            ps.setString(1, persona.getUsuario());
            ps.setString(2, persona.getContrasenia());
            ps.setString(3, persona.getGenero());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar un Empleado: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarEmpleado(Persona persona) {
        final String SQL_UPDATE = "UPDATE employee SET user=?, password=?, genero=?  WHERE ID = ?";
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE)) {
            psUpdate.setString(1, persona.getUsuario());
            psUpdate.setString(2, persona.getContrasenia());
            psUpdate.setString(3, persona.getGenero());
            psUpdate.setInt(4, persona.getId());
            psUpdate.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al modificar Empleado: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean buscarIdEmpleado(Persona persona) {
        final String SQL_SELECT = "SELECT * FROM employee WHERE ID = ?";

        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT)) {
            ResultSet rs;
            ps.setInt(1, persona.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                persona.setUsuario(rs.getString("user"));
                persona.setContrasenia(rs.getString("password"));
                persona.setGenero(rs.getString("genero"));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al recuperar empleado por id: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarEmpleado(Persona persona) {
        final String SQL_DELETE = "DELETE FROM employee WHERE ID = ?";
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, persona.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar Empleado: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("**** PRUEBAS UNITARIAS ****");
        SellerDAO sellerDAO = new SellerDAO();

        // Listar empleados
//        var listarEmpleados = sellerDAO.listarEmpleados();
//        listarEmpleados.forEach(System.out::println);
        // Agregar nuevoEmpleado
//        Persona nuevoEmpleado = new Persona("Ananita", "20TR", "Femenino");
//        boolean insertado = sellerDAO.agregarEmpleado(nuevoEmpleado);
//        if(insertado){
//            System.out.println("Nuevo empleado: "+nuevoEmpleado);
//        }else{
//            System.out.println("Empleado no agregado: "+insertado);
//        }
        // Modificar empleado
//        Persona modificarEmpleado = new Persona(3, "Ana Pérez", "20pt", "Femenino");
//        boolean modificado = sellerDAO.modificarEmpleado(modificarEmpleado);
//        if (modificado) {
//            System.out.println("Empleado modificado: "+modificarEmpleado);
//        }else{
//            System.out.println("Error al modificar empleado existente: "+modificado);
//        }
//           //Buscar por ID
//          Persona buscarId = new Persona();
//          buscarId.setId(3);
//          var encontrado = sellerDAO.buscarIdEmpleado(buscarId);
//          if (encontrado) {
//              System.out.println("Empleado: "+buscarId);
//        }else{
//              System.out.println("ID no encontrado: "+encontrado);
//         }
        // Eliminar empleado
//        Persona eliminarEmpleado = new Persona();
//        eliminarEmpleado.setId(8);
//        boolean eliminado = sellerDAO.eliminarEmpleado(eliminarEmpleado);
//        if (eliminado) {
//            System.out.println("Empleado eliminado: " + eliminarEmpleado);
//        }else{
//            System.out.println("No eliminado");
//        }
    }

}

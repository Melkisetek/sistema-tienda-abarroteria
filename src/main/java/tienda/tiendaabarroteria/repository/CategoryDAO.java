package tienda.tiendaabarroteria.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.tiendaabarroteria.config.ConexionBD;
import tienda.tiendaabarroteria.domain.*;

public class CategoryDAO implements ICategoryDAO {

    @Override
    public List<Categoria> listarTabla() {
        List<Categoria> categorias = new ArrayList<>();
        ResultSet rs;

        final String SQL = "SELECT * FROM categoria";
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCatergoria(rs.getLong("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }
        } catch (Exception e) {
            System.out.println("Error al listar Categorías: " + e.getMessage());
        }

        return categorias;
    }

    @Override
    public boolean insertarCategoria(tienda.tiendaabarroteria.domain.Categoria categoria) {
        final String SQL_INSERT = "INSERT INTO categoria(nombre, descripcion) VALUES(?, ?)";
        // 1.  Insertar datos en tabla categoria
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT)) {

            psInsert.setString(1, categoria.getNombre());
            psInsert.setString(2, categoria.getDescripcion());
            psInsert.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error insertar datos: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarCategoria(Categoria catModificar) {
        final String SQL_UPDATE = "UPDATE categoria SET nombre=?, descripcion=?  WHERE id = ?";
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE)) {
            psUpdate.setString(1, catModificar.getNombre());
            psUpdate.setString(2, catModificar.getDescripcion());
            psUpdate.setLong(3, catModificar.getIdCatergoria());
            psUpdate.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al modificar categoría: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean buscarIdCategoria(Categoria buscarCategoria) {
        final String SQL_SELECT = "SELECT * FROM categoria WHERE id = ?";

        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT)) {
            ResultSet rs;
            ps.setLong(1, buscarCategoria.getIdCatergoria());
            rs = ps.executeQuery();
            if (rs.next()) {
                buscarCategoria.setNombre(rs.getString("nombre"));
                buscarCategoria.setDescripcion(rs.getString("descripcion"));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al recuperar categoría por id: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarCategoria(Categoria categoria) {
        final String SQL_DELETE = "DELETE FROM categoria WHERE id = ?";
        try(Connection conn = ConexionBD.getInstancia().getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setLong(1, categoria.getIdCatergoria());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar categoría: "+ex.getMessage());
        }
       return false;
    }
    
    

    public static void main(String[] args) {
        System.out.println("********* PRUEBAS UNITARIAS ********");
        CategoryDAO categoriaDAO = new CategoryDAO();

        // Listar categorias
//        var listarCategorias = categoria.listarTabla();
//        listarCategorias.forEach(System.out::println);
        //Gestionar Categoria
//        Categoria insertarCategoria = new Categoria("Embutido", "chorizo, moronga, salchica");
//        var categoriaAgregado = categoria.insertarCategoria( insertarCategoria);
//        if (categoriaAgregado) {
//            System.out.println("Categoria Insertado: "+insertarCategoria);
//        }else{
//            System.out.println("No se pudo realizar la operación!");
//        }
        // Modificar Categoria
//        Categoria modificarCategoria = new Categoria(7, "BB", "Modificado desde java");
//        var modificado = categoria.modificarCategoria(modificarCategoria);
//        if (modificado) {
//            System.out.println("Se ha modificado: "+modificarCategoria);
//        }else{
//            System.out.println("Error en modificar categoría");
//        }

           // BuscarPorId
//          Categoria buscarCategoria = new Categoria(6);
//          var encontrado = categoriaDAO.buscarIdCategoria(buscarCategoria);
//          if (encontrado) {
//              System.out.println("Mostrar categoría: "+buscarCategoria);
//        } else{
//              System.out.println("No se encontro el id: "+buscarCategoria);
//          }
        
            // Eliminar categoria por id
//            Categoria eliminarCategoria = new Categoria(8);
//            boolean eliminado = categoriaDAO.eliminarCategoria(eliminarCategoria);
//            if(eliminado){
//                System.out.println("Categoría eliminado: "+eliminarCategoria);
//            }else{
//                System.out.println("No se pudo eliminar:  "+eliminarCategoria);
//            }
    }
}

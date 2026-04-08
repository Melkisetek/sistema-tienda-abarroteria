package tienda.tiendaabarroteria.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.tiendaabarroteria.config.ConexionBD;
import tienda.tiendaabarroteria.domain.Categoria;
import tienda.tiendaabarroteria.domain.SaleProducto;

public class SalesManagementDAO implements ISalesManagement {

    @Override
    public boolean ActualizarStockObtenerPrecio(SaleProducto idProducto, SaleProducto productoCant) {
        final String SQL_SELECT = "SELECT precio FROM producto WHERE IDproducto = ?";
        final String SQL_UPDATE = "UPDATE producto SET stock = stock - ? WHERE IDproducto = ? AND stock >= ?";

        try (Connection conn = ConexionBD.getInstancia().getConnection()) {
            // 1. Obtener el precio actual
            try (PreparedStatement psSelect = conn.prepareStatement(SQL_SELECT)) {
                psSelect.setInt(1, idProducto.getIdSale());
                ResultSet rs = psSelect.executeQuery();
                if (rs.next()) {
                    idProducto.setPrecio(rs.getDouble("precio"));
                }
            }
            // 2. ejecutar la actualización del stock
            try (PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE)) {
                psUpdate.setInt(1, productoCant.getStock());
                psUpdate.setInt(2, idProducto.getIdSale());
                psUpdate.setInt(3, productoCant.getStock()); // Verificación: stock >= cantidad
                int filas = psUpdate.executeUpdate();
                
                // Si filasAfectadas es 0, significa que el stock era insuficiente 
                // o el ID cambió en ese instante.
                return filas > 0; // Retorna true si realmente se actualizó algo
            }

        } catch (SQLException e) {
            System.out.println("Error en la respuesta BD: " + e.getMessage());
        }

        return false;
    }
    
        @Override
    public List<Categoria> obtenerCategorias() {
         List<Categoria> categorias = new ArrayList<>();
        final String SQL = "SELECT nombre FROM categoria ORDER BY nombre";
        ResultSet rs;

        try (Connection conn = ConexionBD.getInstancia().getConnection(); 
                PreparedStatement ps = conn.prepareStatement(SQL)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener Categorías: "+ex.getMessage());
        }

        return categorias;
    }

    public static void main(String[] args) {
        System.out.println("**** PRUEBAS UNITARIAS ****\n");
        ISalesManagement salesManagement = new SalesManagementDAO();
//
//        // --- Iniciando prueba de actualización ---
//        SaleProducto pId = new SaleProducto();
//        pId.setIdSale(15);
//        SaleProducto pCant = new SaleProducto();
//        pCant.setStock(5); // Cantidad a restar del stock actual
//        boolean encontrado = salesManagement.ActualizarStockObtenerPrecio(pId, pCant);
//        if (encontrado) {
//            System.out.println("¡Éxito! Stock actualizado correctamente.");
//            System.out.println("Precio obtenido de la BD: Q" + pId.getPrecio());
//            System.out.println("Stock actualizado: "+pCant.getStock());
//            System.out.println("Total de la operación: Q" + (pId.getPrecio() * pCant.getStock()));
//        }else {
//        System.out.println("No se pudo realizar la operación. Revisa el ID o la conexión. "+encontrado);
//    }

//        // Obtener categorías
//        var listarCategoria = salesManagement.obtenerCategorias();
//        listarCategoria.forEach(System.out::println);
        
   }


}

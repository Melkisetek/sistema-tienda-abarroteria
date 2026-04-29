package tienda.tiendaabarroteria.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tienda.tiendaabarroteria.config.ConexionBD;
import tienda.tiendaabarroteria.domain.DetalleVenta;
import tienda.tiendaabarroteria.domain.Venta;

public class ReportDAO implements IReportDAO {

    @Override
    public List<DetalleVenta> consultarVentasDelDia() {
        List<DetalleVenta> lista = new ArrayList<>();
        final String SQL_ = """
                           SELECT 
                               v.IDventa AS "ID Venta", 
                               dv.producto AS "nombre",
                               dv.cantidad AS "Cant.", 
                               dv.subtotal AS "Subtotal"
                           FROM venta v
                           INNER JOIN detalle_venta dv ON v.IDventa = dv.venta_id
                           WHERE DATE(v.fecha) = CURDATE();""";
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DetalleVenta item = new DetalleVenta();
                item.setVentaId(rs.getInt("ID Venta"));
                item.setProducto(rs.getString("nombre"));
                item.setCantidad(rs.getInt("Cant."));
                item.setSubTotal(rs.getDouble("Subtotal"));
                lista.add(item);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar ventas: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean registrarVenta(Venta venta) {
        final String SQL_VENTA = """
                        INSERT INTO venta (total, cajero) VALUES (?, ?) """;
        final String SQL_DETALLE = """
                                   INSERT INTO detalle_venta (venta_id, producto,cantidad, precio_unitario, subtotal) VALUES (?, ?, ?,?,?)""";
        Connection conexion = null;

        try {
            conexion = ConexionBD.getInstancia().getConnection();
            // 1. Desactivamos el autocommit para manejar la transacción manualmente
            conexion.setAutoCommit(false);

            // 2. Insertar la venta principal
            PreparedStatement psVenta = conexion.prepareStatement(SQL_VENTA, Statement.RETURN_GENERATED_KEYS);
            psVenta.setDouble(1, venta.getTotal());
            psVenta.setString(2, venta.getCajero());

            int filasAfectadas = psVenta.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("Error al insertar la venta principal.");
            }

            // 3. Obtener el ID generado para la venta
            int idGenerado = 0;
            try (ResultSet rs = psVenta.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }

            // 4. Insertar los detalles usando el ID obtenido
            PreparedStatement psDetalle = conexion.prepareStatement(SQL_DETALLE);
            for (DetalleVenta detalle : venta.getDetalles()) {
                psDetalle.setInt(1, idGenerado); // el ID  de la tabla venta
                psDetalle.setString(2, detalle.getProducto());
                psDetalle.setInt(3, detalle.getCantidad());
                psDetalle.setDouble(4, detalle.getPrecioUnitario());
                psDetalle.setDouble(5, detalle.getSubTotal());
                psDetalle.addBatch(); // Usamos Batch para mayor eficiencia
            }
            psDetalle.executeBatch();

            // 5. Si todo salió bien, confirmamos los cambios en la BD
            conexion.commit();
            return true;

        } catch (SQLException e) {
            // 6. Si algo falló, deshacemos todo (Rollback)
            if (conexion != null) {
                try {
                    conexion.rollback();
                    System.err.println("Venta cancelado. Error:  " + e.getMessage());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // Cerrar recursos y devolver el autocommit a true
            try {
                if (conexion != null) {
                    conexion.setAutoCommit(true);
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("*** Pruebas Unitarias ***");
        ReportDAO reportDAO = new ReportDAO();

//        // Lista detalle de la venta 
        var listaObtenido = reportDAO.consultarVentasDelDia();
        listaObtenido.forEach(System.out::println);
//        for (Object lista: listaObtenido) {
//            System.out.println(lista);
//        }
        // Nuevo detalle de la venta
//        DetalleVenta detalleVenta = new DetalleVenta(4, 6, 3, 9.00, 28.00);
//        var agregadoVenta = reportDAO.detalleVentaNuevo(detalleVenta);
//        if(agregadoVenta){
//            System.out.println("Se ha agregado nuevo detalle Venta: "+detalleVenta);
//        }else{
//            System.out.println("Error no se agrego nada: "+agregadoVenta);
//        }
    }

}

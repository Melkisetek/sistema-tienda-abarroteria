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
import tienda.tiendaabarroteria.domain.Producto;
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

// método retorne el objeto Venta (o null si no hay datos
    @Override
    public Venta obtenerTotalVendidoDia() {
        final String SQL_VENTA_DIA = """
                                   SELECT 
                                         SUM(dv.subtotal) AS "total_dia"
                                     FROM venta v
                                      INNER JOIN 
                                         detalle_venta dv ON  v.IDventa= dv.venta_id
                                     WHERE 
                                        DATE(v.fecha) = CURDATE(); """;
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_VENTA_DIA)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Venta ventaTotal = new Venta();
                ventaTotal.setTotal(rs.getDouble("total_dia"));

                return ventaTotal; // retornar objeto completo
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el totalVendido: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Venta obtenerTotalVendidoMes() {
        final String SQL_VENTA_MES = """
                                     SELECT 
                                         SUM(dv.subtotal) AS "total_mes"
                                     FROM venta v
                                     	INNER JOIN detalle_venta dv ON v.IDventa = dv.venta_id
                                     WHERE 
                                         v.fecha >= LAST_DAY(CURDATE() - INTERVAL 1 MONTH) + INTERVAL 1 DAY
                                         AND v.fecha < LAST_DAY(CURDATE()) + INTERVAL 1 DAY;""";
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_VENTA_MES)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Venta ventaTotalMes = new Venta();
                ventaTotalMes.setTotal(rs.getDouble("total_mes"));

                return ventaTotalMes; // retornar objeto completo
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el totalVendidoMes: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Venta obtenerTotalVendidoAnio() {
        final String SQL_VENTA_ANIO = """
                                      SELECT 
                                          SUM(dv.subtotal) AS "total_anio"
                                      FROM venta v
                                        INNER JOIN detalle_venta dv ON v.IDventa = dv.venta_id
                                      WHERE 
                                          YEAR(v.fecha) = YEAR(CURDATE());""";
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_VENTA_ANIO)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Venta ventaTotalAnio = new Venta();
                ventaTotalAnio.setTotal(rs.getDouble("total_anio"));

                return ventaTotalAnio; // retornar objeto completo
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el totalVendidoAño: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Producto> obtenerReportePocoStock() {
        final String SQL_STOCK_MINIMO = """
                                        SELECT 
                                        	IDproducto,
                                            nombre AS "Nombre Producto",
                                            stock AS "existencia"
                                        FROM producto
                                        WHERE stock <= stock_minimo
                                            ORDER BY stock ASC;""";
        List<Producto> listarStockMinimo = new ArrayList<>();

        try (Connection conn = ConexionBD.getInstancia().getConnection(); 
                PreparedStatement ps = conn.prepareStatement(SQL_STOCK_MINIMO); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                Producto item = new Producto();
                item.setId(rs.getInt("IDproducto"));
                item.setNombre(rs.getString("Nombre Producto"));
                item.setStock(rs.getInt("existencia"));
                listarStockMinimo.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Error a listar Stock Mínimo: "+e.getMessage());
        }
        return listarStockMinimo;
    }

    public static void main(String[] args) {
        System.out.println("*** Pruebas Unitarias ***");
        ReportDAO reportDAO = new ReportDAO();

//        // Lista detalle de la venta 
//        var listaObtenido = reportDAO.consultarVentasDelDia();
//        listaObtenido.forEach(System.out::println);
//        for (Object lista: listaObtenido) {
//            System.out.println(lista);
//        }
        // Registrar nueva venta
        // Obtener total vendido
//        Venta ventaTotal = reportDAO.obtenerTotalVendidoDia();
//        if(ventaTotal != null){
//            System.out.println("El total vendido hoy es: "+ventaTotal.getTotal());
//        }else{
//            System.out.println("No hay ventas registradas hoy.");
//        }
        // Obtener total vendido mes
//        Venta totalMes = reportDAO.obtenerTotalVendidoMes();
//        if (totalMes != null) {
//            System.out.println("El total vendido este mes: " + totalMes.getTotal());
//        } else {
//            System.out.println("No hay ventas registradas este mes.");
//        }
        // Obtener total vendido año
//        Venta totalAnio = reportDAO.obtenerTotalVendidoAnio();
//        if (totalAnio != null) {
//            System.out.println("El total vendido este año: " + totalAnio.getTotal());
//        } else {
//            System.out.println("No hay ventas registradas este año");
//        }
        // Listar lista stock minimo
        var listadoObtendido = reportDAO.obtenerReportePocoStock();
        listadoObtendido.forEach(System.out::println);
    }

}

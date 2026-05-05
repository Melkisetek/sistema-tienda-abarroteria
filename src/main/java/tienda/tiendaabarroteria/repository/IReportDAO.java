package tienda.tiendaabarroteria.repository;

import java.util.List;
import tienda.tiendaabarroteria.domain.DetalleVenta;
import tienda.tiendaabarroteria.domain.Producto;
import tienda.tiendaabarroteria.domain.Venta;

public interface IReportDAO {
    List<DetalleVenta> consultarVentasDelDia();
    boolean registrarVenta(Venta venta);
    public Venta obtenerTotalVendidoDia();
    public Venta obtenerTotalVendidoMes();
    public Venta obtenerTotalVendidoAnio();
    public List<Producto> obtenerReportePocoStock();
    
}

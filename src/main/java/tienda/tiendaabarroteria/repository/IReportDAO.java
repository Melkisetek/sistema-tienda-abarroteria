package tienda.tiendaabarroteria.repository;

import java.util.List;
import tienda.tiendaabarroteria.domain.DetalleVenta;
import tienda.tiendaabarroteria.domain.Venta;

public interface IReportDAO {
    List<DetalleVenta> consultarVentasDelDia();
    boolean registrarVenta(Venta venta);
}

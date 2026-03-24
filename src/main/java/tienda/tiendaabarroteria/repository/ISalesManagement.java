package tienda.tiendaabarroteria.repository;
import tienda.tiendaabarroteria.domain.SaleProducto;

public interface ISalesManagement {
    boolean  ActualizarStockObtenerPrecio(SaleProducto idProducto, SaleProducto cantidadComprada);
}

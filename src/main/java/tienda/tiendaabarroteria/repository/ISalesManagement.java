package tienda.tiendaabarroteria.repository;
import java.util.List;
import tienda.tiendaabarroteria.domain.Categoria;
import tienda.tiendaabarroteria.domain.SaleProducto;

public interface ISalesManagement {
    boolean  ActualizarStockObtenerPrecio(SaleProducto idProducto, SaleProducto cantidadComprada);
    List<Categoria> obtenerCategorias();
}

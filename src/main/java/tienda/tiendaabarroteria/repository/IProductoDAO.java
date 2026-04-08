package tienda.tiendaabarroteria.repository;

import java.util.List;
import tienda.tiendaabarroteria.domain.Categoria;
import tienda.tiendaabarroteria.domain.Producto;

public interface IProductoDAO {
    List<Producto> listarProductos();
    boolean buscarProducto(Producto producto);
    boolean agregarProducto(Producto producto);
    boolean modificarProducto(Producto producto);
    boolean eliminarProducto(Producto producto);
    List<Producto>  filtrarProducto(Producto producto);
    List<Categoria> obtenerCategorias();
}

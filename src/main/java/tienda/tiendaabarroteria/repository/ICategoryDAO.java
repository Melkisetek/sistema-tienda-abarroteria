package tienda.tiendaabarroteria.repository;

import java.util.List;
import tienda.tiendaabarroteria.domain.Categoria;
import tienda.tiendaabarroteria.domain.Producto;

public interface ICategoryDAO {
    boolean insertarCategoria(Categoria categoria);
    boolean modificarCategoria(Categoria categoria);
    boolean buscarIdCategoria(Categoria categoria);
    boolean eliminarCategoria(Categoria categoria);
    List<Categoria> listarTabla();
}

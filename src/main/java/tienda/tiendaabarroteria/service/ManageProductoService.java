package tienda.tiendaabarroteria.service;

import java.util.List;
import tienda.tiendaabarroteria.domain.Producto;
import tienda.tiendaabarroteria.repository.IProductoDAO;
import tienda.tiendaabarroteria.repository.ProductoDAO;

public class ManageProductoService {

    private IProductoDAO dao = new ProductoDAO();

    public List<Producto> obtenerProductosParaTabla() {
        // Aquí podrías filtrar, aplicar descuentos o reglas de negocio
        return dao.listarProductos();
    }

    // agregar producto
    public void agregarProductoNuevo(Producto producto) throws Exception {
//        //  REGLAS DE NEGOCIO    
        if (producto.getPrecio() <= 0) {
            throw new Exception("El precio no puede ser menor a cero");
        }
        if (producto.getStock() < 0) {
            throw new Exception("El stock no puede ser negativo.");
        }
        if (producto.getNombre().matches("\\d+")) {
            throw new Exception("El nombre no puede ser un número.");
        }

        // Si pasa las reglas, llamamos al DAO
        this.dao.agregarProducto(producto);
    }

    // Modificar producto
    public boolean modificarProducto(Producto producto) throws Exception {
//        //  REGLAS DE NEGOCIO    
        if (producto.getPrecio() <= 0) {
            throw new Exception("El precio no puede ser menor a cero");
        }
        if (producto.getStock() < 0) {
            throw new Exception("El stock no puede ser negativo.");
        }
        if (producto.getNombre().matches("\\d+")) {
            throw new Exception("El nombre no puede ser un número.");
        }

        // Si pasa las reglas, llamamos al DAO
        boolean modificado = this.dao.modificarProducto(producto);
        if (modificado) {
            return true;

        } else {
            System.out.println("Error al modificar Producto");
        }
        return false;
    }

    public void buscarProductoID(Producto producto) throws Exception {
        if (producto.getId() != -1) {
            this.dao.buscarProducto(producto);
        } else {
            throw new Exception(" Error al buscar en tabla");
        }
    }

    public void eliminarProductoID(Producto producto) throws Exception {
        if (producto.getId() != -1) {
            this.dao.eliminarProducto(producto);
        } else {
            throw new Exception("Producto no encontrado");
        }
    }
}

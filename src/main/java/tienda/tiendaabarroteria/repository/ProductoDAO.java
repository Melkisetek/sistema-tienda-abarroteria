package tienda.tiendaabarroteria.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.tiendaabarroteria.config.ConexionBD;
import tienda.tiendaabarroteria.domain.Categoria;
import tienda.tiendaabarroteria.domain.Producto;

public class ProductoDAO implements IProductoDAO {

    @Override
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        ResultSet rs;

        String sql = "SELECT * FROM producto ORDER BY IDProducto";

        try (Connection conn = ConexionBD.getInstancia().getConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("IDProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setStockMinimo(rs.getInt("stock_minimo"));
                productos.add(producto);
            }
        } catch (Exception e) {
            System.out.println("Error al listar Productos: " + e.getMessage());
        }

        return productos;
    }

    @Override
    public boolean buscarProducto(Producto producto) {
        String sql = "SELECT * FROM producto WHERE IDProducto = ?";
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs;
            ps.setInt(1, producto.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setStockMinimo(rs.getInt("stock_minimo"));
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al recuperar cliente por id: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO producto(nombre, precio, stock, categoria, stock_minimo) VALUES(?, ?, ?, ?, ?)";

        // Al declarar la conexión aquí, Java la cierra automáticamente al terminar
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getCategoria());
            ps.setInt(5, producto.getStockMinimo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre=?, precio=?, stock=?, categoria=?, stock_minimo=? "
                + "WHERE IDProducto = ?";
        // Al declarar la conexión aquí, Java la cierra automáticamente al terminar
        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getCategoria());
            ps.setInt(5, producto.getStockMinimo());
            ps.setInt(6, producto.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar producto: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminarProducto(Producto producto) {
        final String SQL = "DELETE FROM producto WHERE IDProducto = ?";

        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, producto.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Producto> filtrarProducto(Producto categoriaProd) {
        List<Producto> productos = new ArrayList<>();
        ResultSet rs;
        final String SQL = "SELECT * FROM producto WHERE categoria = ?";

        try (Connection conn = ConexionBD.getInstancia().getConnection(); PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, categoriaProd.getCategoria());
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("IDProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoria(rs.getString("categoria"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar Categoría: " + e.getMessage());
        }

        return productos;
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
       System.out.println("****** PRUEBAS UNITARIAS ********\n");
        IProductoDAO productoDAO = new ProductoDAO();
        // listarProductos
//        var listarProductos = productoDAO.listarProductos();
//        listarProductos.forEach(System.out::println);
        // Agregar un producto
//        Producto nuevoProducto = new Producto("Salchichas", 13.75, 33, "Carné");
//        var producto = productoDAO.agregarProducto(nuevoProducto);
//        if(producto){
//            System.out.println("Producto agregado: "+ nuevoProducto);
//        }else{
//            System.out.println("No se agrego producto: "+ nuevoProducto);
//        }
        // Modificar Producto
//        Producto modificarProducto= new Producto(9,"Salchichas" , 5.00, 90, "Carné");
//        var modificado = productoDAO.modificarProducto(modificarProducto);
//        if(modificado){
//            System.out.println("Producto Modificado:  "+modificarProducto);
//        }else{
//            System.out.println("No se pudo modificar producto: "+modificarProducto);
//        }
        // Buscar Producto por id
//        Producto buscarProducto = new Producto(39);
//        var encontrado = productoDAO.buscarProducto(buscarProducto);
//        if (encontrado) {
//            System.out.println("Producto encontrado: " + buscarProducto);
//        } else {
//            System.out.println("No se encontro el ID");
//        }
        // Eliminar un producto
//            Producto eliminarProducto = new Producto(3);
//            var eliminado = productoDAO.eliminarProducto(eliminarProducto);
//            if(eliminado){
//                System.out.println("Producto eliminado: "+eliminarProducto);
//            }else{
//                System.out.println("No elimindo: "+eliminarProducto);
//            }

        // Filtrar Producto por categoría
//        Producto filtrarCategoria = new Producto("Bebida");
//        var encontrado = productoDAO.filtrarProducto(filtrarCategoria);
//        encontrado.forEach(System.out::println);
          // Obtener Listar Categorías
          var listarCategoria = productoDAO.obtenerCategorias();
          listarCategoria.forEach(System.out::println);
    }
}

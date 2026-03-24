package tienda.tiendaabarroteria.service;

import java.util.List;
import javax.swing.JOptionPane;
import tienda.tiendaabarroteria.domain.Producto;
import tienda.tiendaabarroteria.domain.SaleProducto;
import tienda.tiendaabarroteria.repository.IProductoDAO;
import tienda.tiendaabarroteria.repository.ProductoDAO;
import tienda.tiendaabarroteria.repository.SalesManagementDAO;

public class SalesManagementService {

    private final IProductoDAO dao;
    private final SalesManagementDAO salesDAO;

    public SalesManagementService() {
        // Inyectamos la dependencia del DAO
        this.dao = new ProductoDAO();
        this.salesDAO = new SalesManagementDAO();
    }

    public List<Producto> obtenerProductosParaTabla() {
        // Aquí podrías filtrar, aplicar descuentos o reglas de negocio
        return dao.listarProductos();
    }

    public void buscarProductoID(Producto producto) throws Exception {
        if (producto.getId() != -1) {
            this.dao.buscarProducto(producto);
        } else {
            throw new Exception(" Error al buscar en tabla");
        }
    }

    public Double procesarVenta(int idProducto, int cantidadAVender) throws Exception {   
        // --- INICIO DE VALIDACIONES DE NEGOCIO ---
        SalesManagementService prodExistente = new SalesManagementService();
        
        Producto productoVenta = new Producto(idProducto);
       prodExistente.buscarProductoID(productoVenta);
       
       // 2. Validar que la cantidad ingresada sea mayor a cero
        if (cantidadAVender <= 0) {
            throw new Exception("La cantidad debe ser mayor a 0");
        }
        if (productoVenta ==null) {
             throw new Exception("El producto con ID "+idProducto+" no existe");
        }
        
        if (productoVenta.getStock()<=0) {
            throw new Exception("Producto agotado (Stock en 0)");
        }
        
        if (cantidadAVender > productoVenta.getStock()) {
            throw new Exception("No hay suficiente stock. Disponible");
        }

        SaleProducto idPro = new SaleProducto();
        idPro.setIdSale(idProducto);
        SaleProducto productoCant = new SaleProducto();
        productoCant.setStock(cantidadAVender);

        //  3. Validamos disponibilidad (Lógica de Negocio)
        boolean exito = salesDAO.ActualizarStockObtenerPrecio(idPro, productoCant);
        if (exito) {
            double precio = idPro.getPrecio();
            double total = precio * cantidadAVender;

            return total;
        } else {
            throw new Exception("ERROR: No se pudo procesar la venta. Verifique el ID o la disponibilidad de stock.");
        }

    }
//
//    public static void main(String[] args) throws Exception {
//        // Simulamos la interacción desde una interfaz (como tus proyectos en Java Swing)
//        SalesManagementService service = new SalesManagementService();
//
//        System.out.println("--- SISTEMA DE VENTAS ---");
//
//        // Intentamos vender 5 unidades del producto 15
//         Number resultado = service.procesarVenta(1, 1);
//          System.out.println(resultado);
//    }

}

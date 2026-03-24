package tienda.tiendaabarroteria.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import tienda.tiendaabarroteria.domain.Producto;
import tienda.tiendaabarroteria.service.SalesManagementService;
import tienda.tiendaabarroteria.view.SalesManagement;

public class SalesManagementController {

    private SalesManagement vista;
    private SalesManagementService salesService;
    private Producto producto;

    public SalesManagementController(SalesManagement vista) {
        this.vista = vista;
        this.salesService = new SalesManagementService();
        this.producto = new Producto();
        cargaTabla();
        iniciarEventos();
    }

    private int contador = 0;

    private void iniciarEventos() {
        //añadir
        this.vista.getBtnAnadir().addActionListener(e -> agregarMultipleProducto());
        // print
        this.vista.getBtnImprimir().addActionListener(l -> {
            try {
                this.vista.getTxtArea().print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        });

        //refrescar
        this.vista.getBtnRefrescar().addActionListener(r -> cargaTabla());

        // limpiar
        // Botón para limpiar
        this.vista.getBtnLimpiar().addActionListener(e -> {
            this.vista.getTxtArea().setText("");
            contador = 0;
        });

        this.vista.getJtablaProductos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = vista.getJtablaProductos().getSelectedRow();
                if (filaSeleccionada != -1) {
                    try {
                        cargarDatosEnFormulario(filaSeleccionada);
                    } catch (Exception ex) {
                        Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
    }

    private void cargaTabla() {
        DefaultTableModel modelo = (DefaultTableModel) this.vista.getJtablaProductos().getModel();
        modelo.setRowCount(0); // Limpiar tabla

        List<Producto> listaProductos = this.salesService.obtenerProductosParaTabla();

        for (Producto p : listaProductos) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                "Q" + p.getPrecio(),
                p.getStock(),
                p.getCategoria()
            });
        }
    }

    private void cargarDatosEnFormulario(int filaSeleccionada) throws Exception {
        // Obtener datos de la tabla
        var modelo = vista.getJtablaProductos().getModel();

        // Crear objetos con los datos
        int idProducto = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
        this.producto.setId(idProducto);
        this.salesService.buscarProductoID(this.producto);

        // Pasar objeto a los métodos
        mostrarDatos(this.producto);
    }

    private void mostrarDatos(Producto producto) {
        this.vista.getId().setText(String.valueOf(producto.getId()));
        this.vista.getTextNombre().setText(producto.getNombre());

    }

    private void agregarMultipleProducto() {
        String cantidadText = this.vista.getTextCantidad().getText().trim();
        String nombreText = this.vista.getTextNombre().getText().trim();
        String idTxt = this.vista.getId().getText().trim();

        if (cantidadText.isEmpty() || nombreText.isEmpty() || idTxt.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Error: Los campos no pueden estar vacíos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int cantidadProduct = Integer.parseInt(cantidadText);
            int id = Integer.parseInt(this.vista.getId().getText());

            try {
                // Si llega aquí, todo está OK. Procedemos a procesar la venta y actualizar vista.
                double totalVenta = salesService.procesarVenta(id, cantidadProduct);
                contador++;
                String detalle = contador + "              " + this.producto.getNombre().trim()
                        + "                          Q" + this.producto.getPrecio() + "                     "
                        + this.vista.getTextCantidad().getText().trim() + "                   Q"
                        + totalVenta + "\n";

                if (contador == 1) {
                    this.vista.getTxtArea().setText("==================DETALLE PRODUCTOS==================\n"
                            + "NUM        PRODUCTO                     PRECIO          CANTIDAD        TOTAL\n" + detalle);

                } else {
                    this.vista.getTxtArea().append(detalle);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                // Logger para ver errores 
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            cargaTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Error: El ID y la Cantidad deben ser números válidos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

}

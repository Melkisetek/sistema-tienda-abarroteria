package tienda.tiendaabarroteria.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tienda.tiendaabarroteria.domain.Categoria;
import tienda.tiendaabarroteria.domain.Producto;
import tienda.tiendaabarroteria.service.ManageProductoService;
import tienda.tiendaabarroteria.view.ProductManagement;

public class ProductoController {

    private final ProductManagement vista;
    private final ManageProductoService productoService;
    private Long idSeleccionado = null; // Fuente de verdad: null = Guardar, con ID = Actualizar

    public ProductoController(ProductManagement view) {
        this.vista = view;
        this.productoService = new ManageProductoService();
        cargarTabla();
        iniciarEventos();
        ejecutarObtenerCategorias();
    }

    private void iniciarEventos() {
        // 1. Escuchar los botones principales (btnGuardar actúa como Guardar/Actualizar)
        this.vista.getBtnGuardar().addActionListener(e -> procesarGuardarOActualizar());
        this.vista.getBtnEliminar().addActionListener(e -> ejecutarBtnEliminar());
        this.vista.getBtnLimpiar().addActionListener(l -> limpiarFormulario());

        // Presionar enter
        this.vista.getNombre().addActionListener(e -> procesarGuardarOActualizar());
        this.vista.getCantidad().addActionListener(e -> procesarGuardarOActualizar());
        this.vista.getPrecio().addActionListener(e -> procesarGuardarOActualizar());
        this.vista.getStockMinimo().addActionListener(e -> procesarGuardarOActualizar());

        // 2. Escuchar la selección en la JTable para cargar el registro automáticamente
        this.vista.getjTable1().getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && this.vista.getjTable1().getSelectedRow() != -1) {
                int filaSeleccionada = this.vista.getjTable1().getSelectedRow();
                try {
                    Object idObj = this.vista.getjTable1().getValueAt(filaSeleccionada, 0);
                    if (idObj != null) {
                        Long id = Long.valueOf(idObj.toString());
                        seleccionarProductoParaEditar(id);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(vista, "Error al seleccionar el registro: " + ex.getMessage());
                }
            }
        });
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.getjTable1().getModel();
        modelo.setRowCount(0); // Limpiar tabla

        List<Producto> listaProductos = productoService.obtenerProductosParaTabla();

        for (Producto p : listaProductos) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                "Q" + p.getPrecio(),
                p.getStock(),
                p.getCategoria(),
                p.getStockMinimo()
            });
        }
    }

    /**
     * Método unificado (Patrón Upsert): Decide si crea un producto nuevo o
     * actualiza basándose estrictamente en si idSeleccionado tiene valor o es
     * null.
     */
    private void procesarGuardarOActualizar() {
        // Validaciones básicas de campos vacíos en la UI
        if (this.vista.getNombre().getText().trim().isEmpty()) {
            this.vista.getNombre().requestFocusInWindow();
            mostrarMensaje("Proporcione un nombre.");
            return;
        }
        if (this.vista.getCantidad().getText().trim().isEmpty()) {
            this.vista.getCantidad().requestFocusInWindow();
            mostrarMensaje("Proporcione cantidad disponible.");
            return;
        }
        if (this.vista.getPrecio().getText().trim().isEmpty()) {
            this.vista.getPrecio().requestFocusInWindow();
            mostrarMensaje("Proporcione el precio.");
            return;
        }
        if (this.vista.getStockMinimo().getText().trim().isEmpty()) {
            this.vista.getStockMinimo().requestFocusInWindow();
            mostrarMensaje("Proporcione cantidad mínima.");
            return;
        }

        try {
            String nombre = vista.getNombre().getText().trim();
            String categoria = (vista.getCategoria().getSelectedItem() != null)
                    ? vista.getCategoria().getSelectedItem().toString() : "";
            int stock = Integer.parseInt(vista.getCantidad().getText().trim());
            double precio = Double.parseDouble(vista.getPrecio().getText().trim());
            int stockMinimo = Integer.parseInt(vista.getStockMinimo().getText().trim());

            if (this.idSeleccionado == null) {
                // --- NUEVO REGISTRO (INSERT) ---
                Producto nuevoProducto = new Producto(nombre, precio, stock, categoria, stockMinimo);
                this.productoService.agregarProductoNuevo(nuevoProducto);
                JOptionPane.showMessageDialog(vista, "Producto agregado con éxito");
            } else {
                // --- ACTUALIZACIÓN (UPDATE) ---
                Producto productoModificado = new Producto(this.idSeleccionado.intValue(), nombre, precio, stock, categoria, stockMinimo);
                this.productoService.modificarProducto(productoModificado);
                JOptionPane.showMessageDialog(vista, "Producto actualizado con éxito");
            }

            limpiarFormulario();
            cargarTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Precio, Stock y Stock mínimo deben ser números válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void seleccionarProductoParaEditar(Long id) {
        try {
            Producto productoTemp = new Producto();
            productoTemp.setId(id.intValue());

            // Consulta fresca a la base de datos a través del Service
            this.productoService.buscarProductoID(productoTemp);

            // Cargar datos obtenidos en los JTextField de la vista
            this.vista.getNombre().setText(productoTemp.getNombre());
            this.vista.getPrecio().setText(String.valueOf(productoTemp.getPrecio()));
            this.vista.getCantidad().setText(String.valueOf(productoTemp.getStock()));
            this.vista.getStockMinimo().setText(String.valueOf(productoTemp.getStockMinimo()));
            this.vista.getCategoria().setSelectedItem(productoTemp.getCategoria());

            // Guardar el ID en la variable de control del controlador
            this.idSeleccionado = id;

            // Cambiar el texto del botón visualmente a "Actualizar"
            this.vista.getBtnGuardar().setText("Actualizar");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ejecutarBtnEliminar() {
        int filaSeleccionada = vista.getjTable1().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea eliminar este registro?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                DefaultTableModel modelo = (DefaultTableModel) vista.getjTable1().getModel();
                int idEliminar = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
                Producto productoEliminar = new Producto(idEliminar);

                this.productoService.eliminarProductoID(productoEliminar);
                JOptionPane.showMessageDialog(vista, "Producto eliminado con éxito.");
                cargarTabla();
                limpiarFormulario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public final void ejecutarObtenerCategorias() {
        JComboBox<String> combo = this.vista.getCategoria();
        combo.removeAllItems();

        List<Categoria> obtenerCategorias = productoService.obtenerCategorias();
        for (Categoria cat : obtenerCategorias) {
            combo.addItem(cat.getNombre());
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this.vista, mensaje, "Atención", JOptionPane.WARNING_MESSAGE);
    }

    private void limpiarFormulario() {
        this.vista.getNombre().setText("");
        this.vista.getCantidad().setText("");
        this.vista.getPrecio().setText("");
        this.vista.getStockMinimo().setText("");
        this.idSeleccionado = null;                 // Reseteamos el ID a null para volver al modo Guardar
        this.vista.getBtnGuardar().setText("Guardar"); // Restauramos el texto original del botón
        this.vista.getjTable1().getSelectionModel().clearSelection(); // Limpiamos la selección de la tabla
    }
}

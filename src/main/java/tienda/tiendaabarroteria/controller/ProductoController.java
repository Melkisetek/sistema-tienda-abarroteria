package tienda.tiendaabarroteria.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    private ProductManagement vista;
    private ManageProductoService productoService;
    private Producto producto;

    public ProductoController(ProductManagement view) {
        this.vista = view;
        this.productoService = new ManageProductoService();
        this.producto = new Producto();
        cargarTabla();
        iniciarEventos();
        ejecutarObtenerCategorias();
    }

    private void iniciarEventos() {
        // Escuchar el botón
        this.vista.getBtnAgregar().addActionListener(e -> ejecutarGuardado());
        this.vista.getBtnEditar().addActionListener(e -> ejecutarModificarDatos());
        this.vista.getBtnEliminar().addActionListener(e -> ejecutarBtnEliminar());
        this.vista.getBtnLimpiar().addActionListener(l -> limpiarFormulario());
        // Presionar Enter
        this.vista.getPrecio().addActionListener(e -> ejecutarGuardado());
        this.vista.getCantidad().addActionListener(e -> ejecutarGuardado());
        this.vista.getNombre().addActionListener(e -> ejecutarGuardado());

        this.vista.getjTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = vista.getjTable1().getSelectedRow();
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

    private void ejecutarGuardado() {
        if (this.vista.getNombre().getText().equals("")) {
            this.vista.getNombre().requestFocusInWindow();
            mostrarMensaje("Proporcione un nombre.");
            return;
        }
        if (this.vista.getCantidad().getText().equals("")) {
            this.vista.getCantidad().requestFocusInWindow();
            mostrarMensaje("Proporcione cantidad disponible.");
            return;
        }
        if (this.vista.getPrecio().getText().equals("")) {
            this.vista.getPrecio().requestFocusInWindow();
            mostrarMensaje("Proporcione el precio.");
            return;
        }
        if (this.vista.getStockMinimo().getText().equals("")) {
            this.vista.getStockMinimo().requestFocusInWindow();
            mostrarMensaje("Proporcione cantidad mínimo.");
            return;
        }

        try {
            // 1. Extraer datos de la vista y parsear
            String nombre = vista.getNombre().getText().trim();
            String categoria = (vista.getCategoria().getSelectedItem() != null)
                    ? vista.getCategoria().getSelectedItem().toString() : " ";
            int stock = Integer.parseInt(vista.getCantidad().getText().trim());
            double precio = Double.parseDouble(vista.getPrecio().getText().trim());
            int stockMinimo = Integer.parseInt(vista.getStockMinimo().getText().trim());

            // Aquí usas los valores
            //  2. Crear el objeto Modelo
            Producto nuevoProducto = new Producto(nombre, precio, stock, categoria, stockMinimo);
            // 3. Delegar la lógica al Service
            productoService.agregarProductoNuevo(nuevoProducto);

            // 4. Actualizar la interfaz tras éxito
            JOptionPane.showMessageDialog(vista, "Producto agregado con éxito");
            vista.limpiarCampos();
            cargarTabla(); // El método que ya tienes para refrescar la tabla

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Precio, Stock y Stock mínimo deben ser números válidos.");
            // Mostrar mensaje de error en la interfaz
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
            // Logger para ver errores 
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarDatosEnFormulario(int filaSeleccionada) throws Exception {
        // Obtener datos de la tabla
        var modelo = vista.getjTable1().getModel();

        // Crear objetos con los datos
        this.producto.setId(Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString()));
        this.productoService.buscarProductoID(this.producto);

        // Pasar objeto a los métodos
        mostrarDatos(this.producto);
    }

    private void mostrarDatos(Producto producto) {
        this.vista.getNombre().setText(producto.getNombre());
        this.vista.getPrecio().setText(String.valueOf(producto.getPrecio()));
        this.vista.getCantidad().setText(String.valueOf(producto.getStock()));
        this.vista.getCategoria().setSelectedItem(producto.getCategoria());
        this.vista.getStockMinimo().setText(String.valueOf(producto.getStockMinimo()));
    }

    private void ejecutarModificarDatos() {
        int filaSeleccionada = vista.getjTable1().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            DefaultTableModel modelo = (DefaultTableModel) vista.getjTable1().getModel();

            // Crear objetos con los datos
            this.producto.setId(Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString()));
            this.producto.setNombre(this.vista.getNombre().getText().trim());
            this.producto.setPrecio(Double.parseDouble(this.vista.getPrecio().getText().trim()));
            this.producto.setStock(Integer.parseInt(this.vista.getCantidad().getText().trim()));
            this.producto.setCategoria(this.vista.getCategoria().getSelectedItem().toString());
            this.producto.setStockMinimo(Integer.parseInt(this.vista.getStockMinimo().getText().trim()));

            Producto producto1 = new Producto(this.producto.getId(), this.producto.getNombre(),
                    this.producto.getPrecio(), this.producto.getStock(), this.producto.getCategoria(),
                    this.producto.getStockMinimo());

            // 3. Delegar la lógica al Service
            this.productoService.modificarProducto(producto1);
            // 4. Actualizar la interfaz tras éxito
            vista.limpiarCampos();
            cargarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Precio y Stock deben ser números válidos.");
            // Mostrar mensaje de error en la interfaz
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());

        }
    }

    private void ejecutarBtnEliminar() {
        // Seleccionar fila de la tabla
        int filaSeleccionada = vista.getjTable1().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea eliminar este registro?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 1. Obtener el ID del registro (suponiendo que está en la columna 0)
                DefaultTableModel modelo = (DefaultTableModel) vista.getjTable1().getModel();
                int idEliminar = (int) modelo.getValueAt(filaSeleccionada, 0);
                Producto palabraEliminar = new Producto(idEliminar);

                //2. Lógica de persistencia (Llamada al DAO o Servicio)
                this.productoService.eliminarProductoID(palabraEliminar);
                cargarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage());
            }

        }
    }

    public final void ejecutarObtenerCategorias() {
        JComboBox combo = this.vista.getCategoria(); // Obtenemos el combo
        combo.removeAllItems(); // Limpiamos elementos previos

        // Traemos la lista de la base de datos
        List<Categoria> obtenerCategorias = productoService.obtenerCategorias();

        // Llenamos el combo solo con el nombre (el String)
        for (Categoria cat : obtenerCategorias) {
            combo.addItem(cat.getNombre());
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this.vista ,mensaje,"Atención",JOptionPane.ERROR_MESSAGE);
    }

    private void limpiarFormulario() {
        this.vista.getNombre().setText("");
        this.vista.getCantidad().setText("");
        this.vista.getPrecio().setText("");
        this.vista.getStockMinimo().setText("");
        // Limpiamos el id seleccionado
        this.producto.setId(null);
        // Deseleccionar el registro en la tabla
        this.vista.getjTable1().getSelectionModel().clearSelection();
    }

}

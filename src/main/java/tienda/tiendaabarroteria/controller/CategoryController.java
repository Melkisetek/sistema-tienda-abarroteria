package tienda.tiendaabarroteria.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tienda.tiendaabarroteria.domain.Categoria;
import tienda.tiendaabarroteria.service.CategoryService;
import tienda.tiendaabarroteria.view.Category;

public class CategoryController {

    private final Category vista;
    private final CategoryService categoryService;
    private Long idSeleccionado = null;

    public CategoryController(Category vista) {
        this.vista = vista;
        this.categoryService = new CategoryService();
        cargarTabla();
        iniciarEventos();
    }

    private void iniciarEventos() {
        // 1. Escuchar los botones principales
        this.vista.getBtnAgregar().addActionListener(e -> ejecutarGuardadoOActualizar());
        this.vista.getBtnEliminar().addActionListener(e -> ejecutarEliminar());
        this.vista.getBtnLimpiar().addActionListener(i -> limpiarCampos());
        
        // Presionar enter
        this.vista.getTextCategoria().addActionListener(e -> ejecutarGuardadoOActualizar());

        // 2. Escuchar la selección en la JTable para cargar el registro automáticamente
        this.vista.getTable().getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && this.vista.getTable().getSelectedRow() != -1) {
                int filaSeleccionada = this.vista.getTable().getSelectedRow();
                try {
                    Object idObj = this.vista.getTable().getValueAt(filaSeleccionada, 0);
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
        DefaultTableModel modelo = (DefaultTableModel) vista.getTable().getModel();
        modelo.setRowCount(0); // Limpiar tabla

        List<Categoria> listaProductos = categoryService.obtenerTablaCategoria();

        for (Categoria p : listaProductos) {
            modelo.addRow(new Object[]{
                p.getIdCatergoria(),
                p.getNombre(),
                p.getDescripcion()
            });
        }
    }

    private void ejecutarGuardadoOActualizar() {
        // 1. Validaciones básicas de la UI
        if (this.vista.getTextCategoria().getText().trim().isEmpty()) {
            this.vista.getTextCategoria().requestFocusInWindow();
            this.mostrarMensaje("Proporcione una categoría.");
            return;
        }

        if (this.vista.getTxtArea().getText().trim().isEmpty()) {
            this.vista.getTxtArea().requestFocusInWindow();
            this.mostrarMensaje("Proporcione descripción");
            return;
        }

        try {
            String categoriaText = this.vista.getTextCategoria().getText().trim();
            String areaText = this.vista.getTxtArea().getText().trim();
            if (this.idSeleccionado == null) {
                // -- NUEVO REGISTRO (INSERT)
                // 2. Crear el objeto categoria
                Categoria nuevoCategoria = new Categoria(categoriaText, areaText);
                // 3. Delegar la lógica al servicio
                this.categoryService.agregarCategoria(nuevoCategoria);
                JOptionPane.showMessageDialog(vista, "Agregado con éxito");
            } else {
                // ACTUALIZACIÓN (UPDATE)
                Categoria categoriaModificar = new Categoria(this.idSeleccionado, categoriaText, areaText);
                // 4. Delegar la lógica al Service
                this.categoryService.modificarCategoria(categoriaModificar);
                JOptionPane.showMessageDialog(vista, "Actualizado con éxito");
            }

            // 4. Actualizar la interfaz tras éxito
            this.limpiarCampos();
            cargarTabla();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            // Logger para ver errores 
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void seleccionarProductoParaEditar(Long id) {
        try {
            Categoria categoria = new Categoria();
            categoria.setIdCatergoria(id);

            // Consulta fresca a la base de datos a través del Service
            this.categoryService.buscarCategoriaId(categoria);

            // Cargar datos obtenidos en los JTextField de la vista
            this.vista.getTextID().setText(String.valueOf(categoria.getIdCatergoria()));
            this.vista.getTextCategoria().setText(categoria.getNombre());
            this.vista.getTxtArea().setText(categoria.getDescripcion());

            // Guardar el ID en la variable de control del controlador
            this.idSeleccionado = id;

            // Cambiar el texto del botón visualmente a "Actualizar"
            this.vista.getBtnAgregar().setText("Actualizar");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ejecutarEliminar() {
        // Seleccionar fila de la tabla
        int filaSeleccionada = vista.getTable().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea eliminar este registro?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 1. Obtener el ID del registro (suponiendo que está en la columna 0)
                DefaultTableModel modelo = (DefaultTableModel) vista.getTable().getModel();
                Long idEliminar = (Long) modelo.getValueAt(filaSeleccionada, 0);
                Categoria eliminarCategoria = new Categoria(idEliminar);

                //2. Lógica de persistencia (Llamada al DAO o Servicio)
                this.categoryService.eliminarCategoria(eliminarCategoria);
                this.limpiarCampos();
                cargarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage());
            }
        }

    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this.vista, mensaje, "Atención", JOptionPane.WARNING_MESSAGE);
    }

    private void limpiarCampos() {
        this.vista.getTextID().setText("");
        this.vista.getTextCategoria().setText("");
        this.vista.getTxtArea().setText("");
        this.idSeleccionado = null;
        this.vista.getBtnAgregar().setText("Guardar");
        this.vista.getTable().getSelectionModel().clearSelection();
    }
}

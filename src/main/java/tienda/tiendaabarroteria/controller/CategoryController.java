package tienda.tiendaabarroteria.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private final Categoria categoria;
    // 1. Agregar referencia al controlador de productos
    private final ProductoController productoController;

    // 2. Modificar el constructor para recibirlo
    public CategoryController(Category vista, ProductoController productoController) {
        this.vista = vista;
        this.productoController = productoController; // Asignar referencia

        this.categoryService = new CategoryService();
        this.categoria = new Categoria();
        cargarTabla();
        iniciarEventos();
    }

    private void iniciarEventos() {
        this.vista.getBtnAgregar().addActionListener(e -> ejecutarGuardado());
        this.vista.getBtnEditar().addActionListener(e -> ejecutarModificar());
        this.vista.getBtnEliminar().addActionListener(e -> ejecutarEliminar());
        // Seleccionar valores en tabla
        this.vista.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarDatoEnFormulario();
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

    private void ejecutarGuardado() {
        // 1. Obtener datos de la vista
        String categoriaText = this.vista.getTextCategoria().getText().trim();
        String areaText = this.vista.getTxtArea().getText().trim();
        if (categoriaText.isEmpty() || areaText.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Error: Los campos no pueden estar vacíos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 2. Crear el objeto categoria
            Categoria nuevoCategoria = new Categoria(categoriaText, areaText);
            // 3. Delegar la lógica al servicio
            this.categoryService.agregarCategoria(nuevoCategoria);
            // 4. Actualizar la interfaz tras éxito
            this.vista.limpiarCampo();
            cargarTabla();
            //  REFRESCAR EL COMBO DEL OTRO CONTROLADOR
            if (this.productoController != null) {
                this.productoController.ejecutarObtenerCategorias();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            // Logger para ver errores 
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarDatoEnFormulario() {
        // 1. Obtener datos de la tabla
        int filaSeleccionada = vista.getTable().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) this.vista.getTable().getModel();

        if (filaSeleccionada != -1) {
            try {
                // 2. Crear objetos con los datos
                this.categoria.setIdCatergoria(Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString()));
                this.categoryService.buscarCategoriaId(categoria);

                // 3. Pasar objeto al método
                mostrarDatos(this.categoria);
            } catch (Exception ex) {
                Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void mostrarDatos(Categoria categoria) {
        this.vista.getTextID().setText(String.valueOf(categoria.getIdCatergoria()));
        this.vista.getTextCategoria().setText(categoria.getNombre());
        this.vista.getTxtArea().setText(categoria.getDescripcion());
    }

    private void ejecutarModificar() {
        // 1. Obtener datos de la tabla
        int filaSeleccionada = vista.getTable().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) vista.getTable().getModel();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 2. Crear objetos con los datos
            this.categoria.setIdCatergoria(Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString()));
            this.categoria.setNombre(this.vista.getTextCategoria().getText().trim());
            this.categoria.setDescripcion(this.vista.getTxtArea().getText().trim());

            Categoria categoriaMod = new Categoria(this.categoria.getIdCatergoria(), this.categoria.getNombre(),
                    this.categoria.getDescripcion());

            // 3. Delegar la lógica al Service
            this.categoryService.modificarCategoria(categoriaMod);
            // 4. Actualizar la interfaz tras éxito
            this.vista.limpiarCampo();
            cargarTabla();

            //  REFRESCAR EL COMBO DEL OTRO CONTROLADOR
            if (this.productoController != null) {
                this.productoController.ejecutarObtenerCategorias();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            // Logger para ver errores 
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
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
                int idEliminar = (int) modelo.getValueAt(filaSeleccionada, 0);
                Categoria eliminarCategoria = new Categoria(idEliminar);

                //2. Lógica de persistencia (Llamada al DAO o Servicio)
                this.categoryService.eliminarCategoria(eliminarCategoria);
                this.vista.limpiarCampo();
                cargarTabla();
                //  REFRESCAR EL COMBO DEL OTRO CONTROLADOR
                if (this.productoController != null) {
                    this.productoController.ejecutarObtenerCategorias();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage());
            }
        }

    }
}

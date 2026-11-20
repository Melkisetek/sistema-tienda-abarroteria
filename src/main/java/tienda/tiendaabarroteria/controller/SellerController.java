package tienda.tiendaabarroteria.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tienda.tiendaabarroteria.domain.Persona;
import tienda.tiendaabarroteria.service.SellerService;
import tienda.tiendaabarroteria.view.Seller;

public class SellerController {

    private final SellerService sellerService;
    private final Seller vista;
    private Integer idSeleccionado = null;

    public SellerController(Seller vista) {
        this.vista = vista;
        this.sellerService = new SellerService();
        iniciarEventos();
        cargarTabla();
    }

    private void iniciarEventos() {
        // 1. Escuchar los botones principales
        this.vista.getBtnAgregar().addActionListener(a -> this.ejecutarGuardadoOActualizar());
        this.vista.getBtnEliminar().addActionListener(e -> this.ejecutarEliminado());
        this.vista.getBtnLimpiar().addActionListener(l -> this.limpiarCampo());
        
        // Presionar enter
        this.vista.getNombre().addActionListener(e -> ejecutarGuardadoOActualizar());
        this.vista.getContrasenia().addActionListener(e -> ejecutarGuardadoOActualizar());

        // 2. Escuchar la selección en la JTable para cargar el registro automáticamente
        this.vista.getjTable().getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && this.vista.getjTable().getSelectedRow() != -1) {
                int filaSeleccionada = this.vista.getjTable().getSelectedRow();
                try {
                    Object idObj = this.vista.getjTable().getValueAt(filaSeleccionada, 0);
                    if (idObj != null) {
                        Integer id = Integer.valueOf(idObj.toString());
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
        DefaultTableModel modelo = (DefaultTableModel) vista.getjTable().getModel();
        modelo.setRowCount(0); // Limpiar tabla

        List<Persona> listaEmpleado = sellerService.listarListaEmpleados();

        for (Persona empleado : listaEmpleado) {
            modelo.addRow(new Object[]{
                empleado.getId(),
                empleado.getUsuario(),
                empleado.getContrasenia(),
                empleado.getGenero()
            });
        }
    }

    private void ejecutarGuardadoOActualizar() {
        // 1. Validaciones básicas de la UI
        if (this.vista.getNombre().getText().trim().isEmpty()) {
            this.vista.getNombre().requestFocusInWindow();
            this.mostrarMensaje("Proporcione nombre.");
            return;
        }

        if (this.vista.getContrasenia().getText().trim().isEmpty()) {
            this.vista.getContrasenia().requestFocusInWindow();
            this.mostrarMensaje("Proporcione contraseña.");
            return;
        }

        if (this.vista.getSexo().getSelectedItem().equals("")) {
            this.mostrarMensaje("Género incorrecto");
            return;
        }

        try {
            String nombre = this.vista.getNombre().getText().trim();
            String contrasenia = this.vista.getContrasenia().getText().trim();
            String genero = this.vista.getSexo().getSelectedItem().toString();

            if (this.idSeleccionado == null) {
                // -- NUEVO REGISTRO (INSERT) --
                // 2. Crear objeto Persona
                Persona nuevoCategoria = new Persona(nombre, contrasenia, genero);
                // 3. Delegar la lógica al servicio
                this.sellerService.agregarEmpleado(nuevoCategoria);
            } else {
                // -- ACTUALIZACIÓN (UPDATE)
                Persona modificarEmpleado = new Persona(this.idSeleccionado, nombre, contrasenia, genero);

                // 4. Delegar la lógica al Service
                this.sellerService.modificarEmpleado(modificarEmpleado);

            }

            // 5. Actualizar la interfaz tras éxito
            this.limpiarCampo();
            cargarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            // Logger para ver errores 
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void seleccionarProductoParaEditar(Integer id) {
        try {
            Persona personaSeleccionar = new Persona();
            personaSeleccionar.setId(id);

            // Consulta fresca a la base de datos a través del Service
            this.sellerService.buscarCategoriaId(personaSeleccionar);

            // Cargar datos obtenidos en los JTextField de la vista
            this.vista.getNombre().setText(personaSeleccionar.getUsuario());
            this.vista.getContrasenia().setText(personaSeleccionar.getContrasenia());
            this.vista.getSexo().setSelectedItem(personaSeleccionar.getGenero());

            // Guardar el ID en la variable de control del controlador
            this.idSeleccionado = id;

            // Cambiar el texto del botón visualmente a "Actualizar"
            this.vista.getBtnAgregar().setText("Actualizar");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ejecutarEliminado() {
        // Seleccionar fila de la tabla
        int filaSeleccionada = vista.getjTable().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de que desea eliminar este registro?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 1. Obtener el ID del registro (suponiendo que está en la columna 0)
                DefaultTableModel modelo = (DefaultTableModel) vista.getjTable().getModel();
                int idEliminar = (int) modelo.getValueAt(filaSeleccionada, 0);
                Persona eliminarEmpleado = new Persona();
                eliminarEmpleado.setId(idEliminar);

                //2. Lógica de persistencia (Llamada al DAO o Servicio)
                this.sellerService.eliminarEmpleado(eliminarEmpleado);
                this.limpiarCampo();
                cargarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage());
            }
        }
    }

    private void limpiarCampo() {
        this.vista.getNombre().setText("");
        this.vista.getContrasenia().setText("");
        this.idSeleccionado = null;
        this.vista.getBtnAgregar().setText("Guardar");
        this.vista.getjTable().getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this.vista, mensaje, "Atención", JOptionPane.WARNING_MESSAGE);
    }
}
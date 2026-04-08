package tienda.tiendaabarroteria.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tienda.tiendaabarroteria.domain.Persona;
import tienda.tiendaabarroteria.service.SellerService;
import tienda.tiendaabarroteria.view.Seller;

public class SellerController {

    private SellerService sellerService;
    private Seller vista;
    private Persona persona;

    public SellerController(Seller vista) {
        this.vista = vista;
        this.sellerService = new SellerService();
        this.persona = new Persona();
        iniciarEventos();
        cargarTabla();
    }

    private void iniciarEventos() {
        this.vista.getBtnAgregar().addActionListener(a -> ejecutarGuardado());
        this.vista.getBtnEditar().addActionListener(e -> ejecutarModificado());
        this.vista.getBtnEliminar().addActionListener(e -> ejecutarEliminado());
        // Seleccionar valores en tabla
        this.vista.getjTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarDatoEnFormulario();
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

    private void ejecutarGuardado() {
        // 1. Obtener datos de la vista
        String usuarioTxt = this.vista.getNombre().getText().trim();
        String contraseniaTxt = this.vista.getContrasenia().getText().trim();
        String genero = this.vista.getSexo().getSelectedItem().toString();
        if (usuarioTxt.isEmpty() || contraseniaTxt.isEmpty() || genero.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Nombre y Contraseña no pueden estar vacíos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 2. Crear el objeto categoria
            Persona nuevoCategoria = new Persona(usuarioTxt, contraseniaTxt, genero);
            // 3. Delegar la lógica al servicio
            this.sellerService.agregarEmpleado(nuevoCategoria);
            // 4. Actualizar la interfaz tras éxito
            this.vista.limpiarCampo();
            cargarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            // Logger para ver errores 
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarDatoEnFormulario() {
        // 1. Obtener datos de la tabla
        int filaSeleccionada = this.vista.getjTable().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) this.vista.getjTable().getModel();

        if (filaSeleccionada != -1) {
            try {
                // 2. Crear objetos con los datos
                this.persona.setId(Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString()));
                this.sellerService.buscarCategoriaId(this.persona);

                // 3. Pasar objeto al método
                mostrarDatos(this.persona);
            } catch (Exception ex) {
                Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void mostrarDatos(Persona persona) {
        this.vista.getNombre().setText(persona.getUsuario());
        this.vista.getContrasenia().setText(persona.getContrasenia());

        if ("MASCULINO".equals(this.vista.getSexo().getSelectedItem()) || "FEMENINO".equals(this.vista.getSexo().getSelectedItem())) {
            this.vista.getSexo().setSelectedItem(persona.getGenero());
        } else {
            System.out.println("Error en mostrar genero: " + persona);
        }
    }

    private void ejecutarModificado() {
        // 1. Obtener datos de la tabla
        int filaSeleccionada = vista.getjTable().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) vista.getjTable().getModel();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // 2. Crear objetos con los datos
            this.persona.setId(Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString()));
            this.persona.setUsuario(this.vista.getNombre().getText().trim());
            this.persona.setContrasenia(this.vista.getContrasenia().getText().trim());
            this.persona.setGenero(this.vista.getSexo().getSelectedItem().toString());

            Persona modificarEmpleado = new Persona(this.persona.getId(), this.persona.getUsuario(),
                    this.persona.getContrasenia(), this.persona.getGenero());

            // 3. Delegar la lógica al Service
            this.sellerService.modificarEmpleado(modificarEmpleado);
            // 4. Actualizar la interfaz tras éxito
            this.vista.limpiarCampo();
            cargarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.vista, ex.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            // Logger para ver errores 
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
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
                this.vista.limpiarCampo();
                cargarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage());
            }
        }
    }
}

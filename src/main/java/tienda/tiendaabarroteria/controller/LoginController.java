package tienda.tiendaabarroteria.controller;

import javax.swing.JOptionPane;
import tienda.tiendaabarroteria.domain.LogIn;
import tienda.tiendaabarroteria.service.LoginService;
import tienda.tiendaabarroteria.service.SesionActual;
import tienda.tiendaabarroteria.view.Login;
import tienda.tiendaabarroteria.view.ProductManagement;
import tienda.tiendaabarroteria.view.SalesManagement;

public class LoginController {

    private Login vista;
    private LoginService loginService;

    public LoginController(Login vista) {
        this.vista = vista;
        loginService = new LoginService();
        iniciarEventos();
    }

    private void iniciarEventos() {
        this.vista.getBtnEntrar().addActionListener(e -> ejecutarEntrar());
    }

    private void ejecutarEntrar() {
        String nombre = this.vista.getTxtUsuario().getText().trim();
        String contrasenia = new String(this.vista.getPassUser().getPassword());
        if (nombre.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Campo Vácio");
            return;
        }
        try {
            switch (this.vista.getCategoriaRol().getSelectedItem().toString()) {
                case "ADMINISTRADOR" -> {
                    LogIn iniciarSesion = new LogIn(nombre, contrasenia);
                    this.loginService.iniciarSesion(iniciarSesion);
                    // obtener nombre cajero
                    SesionActual.nombreCajero = nombre;
                    System.out.println("Nombre cajero: " + SesionActual.nombreCajero);
                    // llamar
                    this.vista.lookAndFeelSystem();
                    ProductManagement gestionarProducto = new ProductManagement();
                    gestionarProducto.setVisible(true);
                    this.vista.dispose(); // Cerramos el login
                }
                case "EMPLEADO" -> {
                    LogIn iniciarEmpleado = new LogIn(nombre, contrasenia);
                    this.loginService.iniciarSesionEmpleado(iniciarEmpleado);
                    SesionActual.nombreCajero = nombre;
                    System.out.println("Nombre cajero: " + SesionActual.nombreCajero);
                    // llamar componente
                    this.vista.lookAndFeelSystem();
                    SalesManagement puntoDeFacturacion = new SalesManagement();
                    puntoDeFacturacion.setVisible(true);
                    this.vista.dispose();
                }
                default ->
                    System.out.println("Error no se inicio sesión");
            }

        } catch (Exception ex) {
            this.vista.limpiarCampos();
            JOptionPane.showMessageDialog(this.vista, ex.getMessage(), "Sesión", JOptionPane.WARNING_MESSAGE);
        }
    }

}

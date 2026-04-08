package tienda.tiendaabarroteria.controller;

import javax.swing.JOptionPane;
import tienda.tiendaabarroteria.domain.LogIn;
import tienda.tiendaabarroteria.service.LoginService;
import tienda.tiendaabarroteria.view.Login;
import tienda.tiendaabarroteria.view.ProductManagement;
import tienda.tiendaabarroteria.view.SalesManagement;

public class LoginController {

    private Login vista;
    private LogIn login;
    private LoginService loginService;

    public LoginController(Login vista) {
        this.vista = vista;
        this.login = new LogIn();
        loginService = new LoginService();
        iniciarEventos();
    }

    private void iniciarEventos() {
        this.vista.getBtnEntrar().addActionListener(e -> ejecutarEntrar());
    }

    private void ejecutarEntrar() {
        String nombre = this.vista.getTxtUsuario().getText().trim();
        String contrasenia = this.vista.getPassUser().getText().trim();
        if (nombre.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Campo Vácio");
            return;
        }
        try {
            if (this.vista.getCategoriaRol().getSelectedItem().toString().equals("ADMINISTRADOR")) {
                LogIn iniciarSesion = new LogIn(nombre, contrasenia);
                this.loginService.iniciarSesion(iniciarSesion);
                // llamar
                this.vista.lookAndFeelSystem();
                ProductManagement gestionarProductos = ProductManagement.getInstance();
                gestionarProductos.setVisible(true);
                this.vista.dispose(); // Cerramos el login
            } else if (this.vista.getCategoriaRol().getSelectedItem().toString().equals("EMPLEADO")) {
                LogIn iniciarEmpleado = new LogIn(nombre, contrasenia);
                this.loginService.iniciarSesionEmpleado(iniciarEmpleado);
                // llamar componente
                this.vista.lookAndFeelSystem();
                SalesManagement puntoDeFacturacion = new SalesManagement();
                puntoDeFacturacion.setVisible(true);
                this.vista.dispose();
            } else {
                System.out.println("Error no se inicio sesión");
            }

        } catch (Exception ex) {
            this.vista.limpiarCampos();
            JOptionPane.showMessageDialog(this.vista,ex.getMessage(),"Sesión", JOptionPane.WARNING_MESSAGE);
        }
    }

}

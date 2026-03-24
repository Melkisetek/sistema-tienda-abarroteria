package tienda.tiendaabarroteria.service;
import tienda.tiendaabarroteria.domain.LogIn;
import tienda.tiendaabarroteria.repository.ILoginDAO;
import tienda.tiendaabarroteria.repository.LoginDAO;

public class LoginService {

    private ILoginDAO dao = new LoginDAO();

    public boolean iniciarSesion(LogIn login) throws Exception {
        // 1. Delegamos la consulta al DAO
        boolean esValido = this.dao.loginAdmin(login);
        if (esValido) {
            return true;
        } else {
            // Opcional: Lanzar una excepción específica si quieres manejarla en un catch global
            throw new Exception("Credenciales inválidas.");
        }
    }

    public boolean iniciarSesionEmpleado(LogIn login) throws Exception {
        boolean esValido = this.dao.loginEmpleado(login);
        if (esValido) {
            return true;
        } else {
            throw new Exception("Credenciales inválidas.");
        }
    }

//    public static void main(String[] args) {
//        LoginService loginService = new LoginService();
//        LogIn login = new LogIn("Admin", "admin");
//        try {
//            loginService.IniciarSesion(login);
//        } catch (Exception ex) {
//            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}

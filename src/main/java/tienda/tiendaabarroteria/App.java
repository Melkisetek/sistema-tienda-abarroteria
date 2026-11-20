package tienda.tiendaabarroteria;

import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.UIManager;
import tienda.tiendaabarroteria.controller.ProductoController;
import tienda.tiendaabarroteria.view.Category;
import tienda.tiendaabarroteria.view.Login;
import tienda.tiendaabarroteria.view.ProductManagement;
import tienda.tiendaabarroteria.view.Report;
import tienda.tiendaabarroteria.view.SalesManagement;
import tienda.tiendaabarroteria.view.Seller;

/**
 *
 * @author melquisedec
 */
public class App {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
        
    }
}

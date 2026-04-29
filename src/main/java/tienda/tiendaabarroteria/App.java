package tienda.tiendaabarroteria;

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
        /* A veces funcion el rol con false otras ¿no?*/
        java.awt.EventQueue.invokeLater(() -> {
            // Crea el diálogo con el JFrame principal como padre (o null si no hay)
            Login dialog = new Login(); // true = modal
            dialog.setVisible(true); 
       });

// Pruebas
//        java.awt.EventQueue.invokeLater(() -> {
//            SalesManagement report= new SalesManagement();
//            report.setVisible(true);
//        });

    }
}

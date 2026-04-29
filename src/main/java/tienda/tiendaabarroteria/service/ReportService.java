
package tienda.tiendaabarroteria.service;

import java.util.ArrayList;
import java.util.List;
import tienda.tiendaabarroteria.domain.DetalleVenta;
import tienda.tiendaabarroteria.repository.IReportDAO;
import tienda.tiendaabarroteria.repository.ReportDAO;

public class ReportService {
    private IReportDAO reportDAO = new ReportDAO();
    
    public List<DetalleVenta> consultarVentasDelDia(){
        // 1. Llamar DAO
        List<DetalleVenta> listaVentasDia = reportDAO.consultarVentasDelDia();
        // 2. Regla: Válidar si la lista viene nula
        if (listaVentasDia == null) {
            return new ArrayList<>();
        }
        
        return listaVentasDia;
    }
}

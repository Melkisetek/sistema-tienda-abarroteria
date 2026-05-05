package tienda.tiendaabarroteria.service;

import java.util.ArrayList;
import java.util.List;
import tienda.tiendaabarroteria.domain.DetalleVenta;
import tienda.tiendaabarroteria.domain.Producto;
import tienda.tiendaabarroteria.domain.Venta;
import tienda.tiendaabarroteria.repository.IReportDAO;
import tienda.tiendaabarroteria.repository.ReportDAO;

public class ReportService {

    private IReportDAO reportDAO = new ReportDAO();

    public List<DetalleVenta> consultarVentasDelDia() {
        // 1. Llamar DAO
        List<DetalleVenta> listaVentasDia = reportDAO.consultarVentasDelDia();
        // 2. Regla: Válidar si la lista viene nula
        if (listaVentasDia == null) {
            return new ArrayList<>();
        }

        return listaVentasDia;
    }

    public Venta consultarTotalVendidoDia() {
        Venta obtenerVentaDia = reportDAO.obtenerTotalVendidoDia();

        if (obtenerVentaDia == null) {
            System.out.println("No hay ventas registradas hoy.");
            return null;
        }
        return obtenerVentaDia;
    }

    public Venta consultarTotalVentasDelMes() {
        Venta obtnerVentaMes = reportDAO.obtenerTotalVendidoMes();

        if (obtnerVentaMes == null) {
            System.out.println("No hay venta registrada de este mes.");
            return null;
        }

        return obtnerVentaMes;
    }

    public Venta consultarTotalVentasDelAnio() {
        Venta obtenerVentaAnio = reportDAO.obtenerTotalVendidoAnio();

        if (obtenerVentaAnio == null) {
            System.out.println("No hay venta registrada de este año.");
            return null;
        }

        return obtenerVentaAnio;
    }

    public List<Producto> consultarReportePocoStock() {
        // 1. Llamar DAO
        List<Producto> listarStockMinimo = reportDAO.obtenerReportePocoStock();
        // 2. Regla: Válidar si la lista viene nula
        if (listarStockMinimo == null) {
            return new ArrayList<>();
        }

        return listarStockMinimo;
    }
}

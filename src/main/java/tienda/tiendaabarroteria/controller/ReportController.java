package tienda.tiendaabarroteria.controller;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextArea;
import tienda.tiendaabarroteria.domain.DetalleVenta;
import tienda.tiendaabarroteria.domain.Producto;
import tienda.tiendaabarroteria.domain.Venta;

import tienda.tiendaabarroteria.service.ReportService;
import tienda.tiendaabarroteria.view.Report;

public class ReportController {

    private Report vistaReport;
    private ReportService reportService;
    private final SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
    // Formateador para moneda local (ej. $, Q, €, según tu configuración regional)
    private final NumberFormat formateadorMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault());

    public ReportController(Report vista) {
        this.vistaReport = vista;
        this.reportService = new ReportService();
        iniciarEventos();
    }

    private void iniciarEventos() {
        this.vistaReport.getBtnVentaDeDia().addActionListener(l -> mostrarReporteVentasDia());
        this.vistaReport.getBtnTotalVendido().addActionListener(l -> mostrarReporteTotalVendido());
        this.vistaReport.getBtnLimpiar().addActionListener(l -> limpiarArea());
        this.vistaReport.getBtnPocoStock().addActionListener(l -> MostrarproductoStockMinimo());
    }

    private void mostrarReporteVentasDia() {
        this.vistaReport.getTxtInfo().setText("REPORTE DETALLADO DE VENTAS: " + formateadorFecha.format(new Date()));
        JTextArea areaDeTextoReporte = (JTextArea) this.vistaReport.getTxtArea();
         this.vistaReport.areaTextoReporte();
        List<DetalleVenta> listas = this.reportService.consultarVentasDelDia();

        StringBuilder sb = new StringBuilder();
        // Encabezado de la "tabla " manual
        sb.append(String.format("%-10s | %-25s | %-8s | %-12s\n", "ID Venta", "Producto", "Cant.", "Subtotal"));
        sb.append("--------------------------------------------------------------------------------------------\n");
        // Datos de la tabla
        for (DetalleVenta lista : listas) {
            sb.append(String.format("%-10s | %-25s | %-8d | %-12s\n", lista.getVentaId(), lista.getProducto(), lista.getCantidad(),
                    formateadorMoneda.format(lista.getSubTotal())));
        }

        sb.append("-----------------------------------------------------------------------------------------\n");
        // Stream para contar los IDs únicos
        long totalTransacciones = listas.stream()
                .map(DetalleVenta::getVentaId)
                .distinct()
                .count();

        sb.append("Total de transacciones: ").append(totalTransacciones);
        areaDeTextoReporte.setText(sb.toString());
    }

    private void mostrarReporteTotalVendido() {
        this.vistaReport.getTxtInfo().setText("RESUMEN DE TOTAL VENDIDO");
        JTextArea areaDeTextoReporte = (JTextArea) this.vistaReport.getTxtArea();
        this.vistaReport.areaTextoReporte();
        Venta totalDia = this.reportService.consultarTotalVendidoDia();
        Venta totalMes = this.reportService.consultarTotalVentasDelMes();
        Venta totalAnio = this.reportService.consultarTotalVentasDelAnio();

        String reporte = "\n"
                + "  Resumen Financiero al " + formateadorFecha.format(new Date()) + "\n\n"
                + "  ===========================================\n\n"
                + "  Total Vendido HOY:          " + formateadorMoneda.format(totalDia.getTotal()) + "\n\n"
                + "  Total Vendido ESTE MES:     " + formateadorMoneda.format(totalMes.getTotal()) + "\n\n"
                + "  Total Vendido ESTE AÑO:     " + formateadorMoneda.format(totalAnio.getTotal()) + "\n\n"
                + "  ===========================================\n";
        areaDeTextoReporte.setText(reporte);
    }

    private void MostrarproductoStockMinimo() {
        this.vistaReport.getTxtInfo().setText("ALERTA DE INVENTARIO: BAJO STOCK ");
        JTextArea areaDeTextoReporte = (JTextArea) this.vistaReport.getTxtArea();
        this.vistaReport.areaFondoRojo();
        List<Producto> listarStockMinimo = this.reportService.consultarReportePocoStock();
        int umbralMinimo = 10;

        StringBuilder sb = new StringBuilder();
        sb.append("Productos con existencia inferior a " + umbralMinimo + " unidades:\n\n");

        // Encabezado de la "tabla" manual
        sb.append(String.format("%-15s | %-30s | %-12s | %-10s\n", "Código", "Nombre Producto", "Proveedor", "Existencia"));
        sb.append("--------------------------------------------------------------------------------\n");
        for(Producto lista: listarStockMinimo){
             sb.append(String.format("%-15s | %-30s | %-12s | %-10d\n", lista.getId(), lista.getNombre(), "\t", lista.getStock()));
        }
        
        sb.append("--------------------------------------------------------------------------------\n");
        sb.append("Nota: Se recomienda realizar pedido de estos artículos a la brevedad.");

        areaDeTextoReporte.setText(sb.toString());
    }

    private void limpiarArea() {
        this.vistaReport.getTxtArea().setText("");
    }
}

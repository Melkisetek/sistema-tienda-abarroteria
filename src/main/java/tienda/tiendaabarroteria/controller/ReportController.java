package tienda.tiendaabarroteria.controller;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JTextArea;
import tienda.tiendaabarroteria.domain.DetalleVenta;

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
        this.vistaReport.getBtnLimpiar().addActionListener(l -> limpiarArea());
        this.vistaReport.getBtnTotalVendido().addActionListener(l -> MostrartotalVendido());
        this.vistaReport.getBtnPocoStock().addActionListener(l -> MostrarproductoStockMinimo());
    }

    private void mostrarReporteVentasDia() {
        this.vistaReport.getTxtInfo().setText("REPORTE DETALLADO DE VENTAS: " + formateadorFecha.format(new Date()));
        JTextArea areaDeTextoReporte = (JTextArea) this.vistaReport.getTxtArea();
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

    private void MostrartotalVendido() {
    }

    private void MostrarproductoStockMinimo() {
    }

    private void limpiarArea() {
        this.vistaReport.getTxtArea().setText("");
    }
}

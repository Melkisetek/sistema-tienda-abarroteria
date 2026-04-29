package tienda.tiendaabarroteria.domain;

import java.util.List;

public class Venta {
    private int id;
    private String fecha;
    private double total;
    private String cajero;
    private List<DetalleVenta> detalles; // <-- Clave: La venta "conoce" sus detalles
    
    // Constructor, Getters y Setters
    public Venta(){}

    public Venta(double total, String cajero) {
        this.total = total;
        this.cajero = cajero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Venta{" + "fecha=" + fecha + ", total=" + total + ", cajero=" + cajero + ", detalles=" + detalles + '}';
    }
}

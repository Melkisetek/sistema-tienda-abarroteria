package tienda.tiendaabarroteria.domain;

import java.util.Objects;

public class DetalleVenta {
    private int id;
    private int ventaId;
    private String producto;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;
    
    public DetalleVenta(){}

    public DetalleVenta(int id, int ventaId, String producto, int cantidad, double precioUnitario, double subTotal) {
        this.id = id;
        this.ventaId = ventaId;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }

    public DetalleVenta(int ventaId, String producto, int cantidad, double precioUnitario, double subTotal) {
        this.ventaId = ventaId;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }
    
    public DetalleVenta(String producto, int cantidad,double precioUnitario, double subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "id=" + id + ", ventaId=" + ventaId + ", producto=" +producto + 
                ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subTotal=" + subTotal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.ventaId;
        hash = 79 * hash + Objects.hashCode(this.producto);
        hash = 79 * hash + this.cantidad;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.precioUnitario) ^ (Double.doubleToLongBits(this.precioUnitario) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.subTotal) ^ (Double.doubleToLongBits(this.subTotal) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalleVenta other = (DetalleVenta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ventaId != other.ventaId) {
            return false;
        }
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (Double.doubleToLongBits(this.precioUnitario) != Double.doubleToLongBits(other.precioUnitario)) {
            return false;
        }
        if (Double.doubleToLongBits(this.subTotal) != Double.doubleToLongBits(other.subTotal)) {
            return false;
        }
        return Objects.equals(this.producto, other.producto);
    }

   
    
    
    
}

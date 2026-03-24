package tienda.tiendaabarroteria.domain;

public class SaleProducto {
    // SalesManagment
    private int idSale;
    private double precio;
    private int stock;

    public SaleProducto() {
    }

    
    public SaleProducto(int idSale, double precio, int stock) {
        this.idSale = idSale;
        this.precio = precio;
        this.stock = stock;
    }
    
    

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "SaleProducto{" + "idSale=" + idSale + ", precio=" + precio + ", stock=" + stock + '}';
    }
    
     
    
    
}

package tienda.tiendaabarroteria.domain;

import java.util.Objects;

public class Producto {
    private  int IDproducto;
    private String nombre;
    private double precio;
    private  int stock;
    private String categoria;
    private int stockMinimo;
    
    public Producto(){}
    
    // capturar IDproducto
    public Producto(int id) {
        this.IDproducto = id;
    }
    
    // stock minimo
    public Producto(int id, int stockMinimo){
        this.IDproducto = id;
        this. stockMinimo = stockMinimo;
    }
    
    // filtrar categoria
    public Producto(String categoria){
        this.categoria = categoria;
    }

    // insertar valor
    public Producto(String nombre, double precio, int stock, String categoria, int stockMinimo) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.stockMinimo = stockMinimo;
    }
    
    
    
    // Listar registro
    public Producto(int id, String nombre, double precio, int stock, String categoria, int stockMinimo) {
        this.IDproducto = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.stockMinimo = stockMinimo;
    }
    

    public int getId() {
        return IDproducto;
    }

    public void setId(int id) {
        this.IDproducto = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + IDproducto + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", categoria=" + categoria + ", stockMinimo=" + stockMinimo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.IDproducto;
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 61 * hash + this.stock;
        hash = 61 * hash + Objects.hashCode(this.categoria);
        hash = 61 * hash + this.stockMinimo;
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
        final Producto other = (Producto) obj;
        if (this.IDproducto != other.IDproducto) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.stock != other.stock) {
            return false;
        }
        if (this.stockMinimo != other.stockMinimo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.categoria, other.categoria);
    }

  
    
    
}

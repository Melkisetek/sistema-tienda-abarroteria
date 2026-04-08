package tienda.tiendaabarroteria.domain;

public class Categoria {
    private int idCategoria;
    private String nombreCategoria;
    private String descripcion;

    public Categoria() {
    }
    
      public Categoria(int idCategoria) {
          this.idCategoria = idCategoria;
      }
      
    public Categoria(int idCatergoria, String nombre, String descripcion) {
        this.idCategoria = idCatergoria;
        this.nombreCategoria = nombre;
        this.descripcion = descripcion;
    }

    public Categoria(String nombre, String descripcion) {
        this.nombreCategoria = nombre;
        this.descripcion = descripcion;
    }

    public int getIdCatergoria() {
        return idCategoria;
    }

    public void setIdCatergoria(int idCatergoria) {
        this.idCategoria = idCatergoria;
    }

    public String getNombre() {
        return nombreCategoria;
    }

    public void setNombre(String nombre) {
        this.nombreCategoria = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    


    @Override
    public String toString() {
        return "Categoria{" + "idCatergoria=" + idCategoria + ", nombre=" + nombreCategoria + ", descripcion=" + descripcion + '}';
    }
    
    
}

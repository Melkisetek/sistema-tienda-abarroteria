package tienda.tiendaabarroteria.domain;
public class Persona extends LogIn{
    private String genero;

    public Persona() {
    }

    public Persona(String usuario, String contrasenia, String genero) {
        super(usuario, contrasenia);
        this.genero = genero;
    }
    
    

    
    public Persona( int id, String usuario, String contrasenia, String genero) {
        super(id, usuario, contrasenia);
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return super.toString()+" Seller{" + "genero=" + genero + '}';
    }
    
    
    
     
}

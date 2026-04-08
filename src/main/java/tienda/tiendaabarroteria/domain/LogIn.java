package tienda.tiendaabarroteria.domain;

import java.util.Objects;

public class LogIn {
    private int id;
    private String usuario;
    private String contrasenia;

    public LogIn() {
    }

    public LogIn(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public LogIn(int id, String usuario, String contrasenia) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Login{" + "id=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.usuario);
        hash = 23 * hash + Objects.hashCode(this.contrasenia);
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
        final LogIn other = (LogIn) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return Objects.equals(this.contrasenia, other.contrasenia);
    }
    
    
}

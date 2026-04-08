package tienda.tiendaabarroteria.repository;

import java.util.List;
import tienda.tiendaabarroteria.domain.Persona;

public interface ISellerDAO {
     List<Persona>listarEmpleados();
    boolean agregarEmpleado(Persona persona);
    boolean modificarEmpleado(Persona persona);
     boolean buscarIdEmpleado(Persona persona);
    boolean eliminarEmpleado(Persona persona);
}

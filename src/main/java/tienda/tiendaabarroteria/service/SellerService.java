package tienda.tiendaabarroteria.service;

import java.util.ArrayList;
import java.util.List;
import tienda.tiendaabarroteria.domain.Persona;
import tienda.tiendaabarroteria.repository.SellerDAO;

public class SellerService {

    private SellerDAO sellerDAO = new SellerDAO();

    public List<Persona> listarListaEmpleados() {
        // 1. Llamar al dao
        List<Persona> listarEmpleados = sellerDAO.listarEmpleados();
        // 2. Regla: Validar si la lista viene nula
        if (listarEmpleados == null) {
            return new ArrayList<>();
        }

        return listarEmpleados;
    }

    public void agregarEmpleado(Persona nuevoEmpleado) throws Exception {
        // 1. Aplicar reglas de negocio
        if (nuevoEmpleado.getUsuario().matches("\\d+") || nuevoEmpleado.getUsuario().length() < 2) {
            throw new Exception("Nombre Inválido: " + nuevoEmpleado.getUsuario());
        }

        String contrasenia = nuevoEmpleado.getContrasenia();
        int longitud = contrasenia != null ? contrasenia.length() : 0;
        if (longitud < 3 || longitud > 100) {
            throw new Exception(
                    "La contraseña debe tener entre 3 y 100 caracteres. "
                    + "Longitud actual: " + longitud
            );
        }

        // 2. Sí éxito, llamar al DAO
        this.sellerDAO.agregarEmpleado(nuevoEmpleado);
    }

    public boolean modificarEmpleado(Persona modificado) throws Exception {
        if (modificado.getId() <= 0) {
            throw new Exception("Id inválido");
        }
        if (modificado.getUsuario().matches("\\d+") || modificado.getUsuario().length() < 2) {
            throw new Exception("Nuevo nombre Inválido");
        }
        String contrasenia = modificado.getContrasenia();
        int longitud = contrasenia != null ? contrasenia.length() : 0;
        if (longitud < 3 || longitud > 100) {
            throw new Exception(
                    "La contraseña debe tener entre 3 y 100 caracteres. "
                    + "Longitud actual: " + longitud
            );
        }

        return sellerDAO.modificarEmpleado(modificado);

    }

    public void buscarCategoriaId(Persona IdPersona) throws Exception {
        if (IdPersona.getId() != -1) {
            this.sellerDAO.buscarIdEmpleado(IdPersona);
        } else {
            throw new Exception(" Error al buscar en tabla");
        }
    }

    public boolean eliminarEmpleado(Persona persona) throws Exception {
        if (persona == null || persona.getId() <= 0) {
            throw new Exception("Categoría inválida o ID no válido");
        }

        return this.sellerDAO.eliminarEmpleado(persona);
    }
}

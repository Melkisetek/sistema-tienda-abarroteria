package tienda.tiendaabarroteria.service;

import java.util.List;
import tienda.tiendaabarroteria.domain.Categoria;
import tienda.tiendaabarroteria.repository.CategoryDAO;
import tienda.tiendaabarroteria.repository.ICategoryDAO;

public class CategoryService {
    
    private ICategoryDAO dao = new CategoryDAO();
    
    public List<tienda.tiendaabarroteria.domain.Categoria> obtenerTablaCategoria() {
        return dao.listarTabla();
    }
    
    public void agregarCategoria(Categoria categoria) throws Exception {
        // 1. Aplicar reglas de negocio
        if (categoria.getNombre().matches("\\d+") || categoria.getNombre().length() < 2) {
            throw new Exception("Nombre Inválido: " + categoria.getNombre());
        }
        if (categoria.getDescripcion().length() < 6 || categoria.getDescripcion().length() > 100
                || categoria.getDescripcion().matches("\\d+")) {
            throw new Exception("La descripción debe tener entre 6 y 100 caracteres");
        }

        // 2. Sí éxito, llamar al DAO
        this.dao.insertarCategoria(categoria);
    }
    
    public void modificarCategoria(Categoria categoria) throws Exception {
        // 1. Aplicar reglas de negocio
        if (categoria.getIdCatergoria() < 1) {
            throw new Exception("El ID de la categoría debe ser un número positivo válido");
        }
        if (categoria.getNombre().matches("\\d+") || categoria.getNombre().length() < 2) {
            throw new Exception("Nombre Inválido: " + categoria.getNombre());
        }
        if (categoria.getDescripcion().length() < 6 || categoria.getDescripcion().length() > 100
                || categoria.getDescripcion().matches("\\d+")) {
            throw new Exception("La descripción debe tener entre 6 y 100 caracteres");
        }

        // 2. Sí éxito, llamar DAO
        this.dao.modificarCategoria(categoria);
    }
    
    public void buscarCategoriaId(Categoria categoria) throws Exception {
        if (categoria.getIdCatergoria() != -1) {
            this.dao.buscarIdCategoria(categoria);
        } else {
            throw new Exception(" Error al buscar en tabla");
        }
    }
    
    public boolean eliminarCategoria(Categoria categoria) throws Exception {
        if (categoria== null || categoria.getIdCatergoria() <= 0) {
            throw  new Exception("Categoría inválida o ID no válido");
        }
        
        return this.dao.eliminarCategoria(categoria);
    }
    
}

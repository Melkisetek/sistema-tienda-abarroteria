/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tienda.tiendaabarroteria.repository;

import tienda.tiendaabarroteria.domain.LogIn;

/**
 *
 * @author melquisedec
 */
public interface ILoginDAO {

    boolean loginAdmin(LogIn login);

    boolean loginEmpleado(LogIn login);
}

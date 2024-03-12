/**
 * @file: RecursoService.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:08
 */

package com.equipo02.hotel.services;

import com.equipo02.hotel.domain.entity.Proveedor;
import com.equipo02.hotel.domain.entity.Recurso;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz RecursoService.
 * Esta interfaz define los métodos para interactuar con los recursos y proveedores en el sistema.
 */
public interface RecursoService {

    /**
     * Método para listar todos los recursos.
     * Este método devuelve una lista de todos los recursos registrados en el sistema.
     *
     * @return Una lista de recursos.
     */
    List<Recurso> listar();

    /**
     * Método para obtener un recurso por su ID.
     * Este método devuelve un recurso basado en su ID.
     *
     * @param id El ID del recurso.
     * @return Un recurso si se encuentra, o un Optional vacío si no se encuentra el recurso.
     */
    Optional<Recurso> porId(Long id);

    /**
     * Método para obtener un recurso junto con sus proveedores por su ID.
     * Este método devuelve un recurso junto con sus proveedores basado en su ID.
     *
     * @param id El ID del recurso.
     * @return Un recurso si se encuentra, o un Optional vacío si no se encuentra el recurso.
     */
    Optional<Recurso> porIdConProveedores(Long id);

    /**
     * Método para guardar un recurso.
     * Este método guarda un recurso en el sistema.
     *
     * @param recurso El recurso a guardar.
     * @return El recurso guardado.
     */
    Recurso guardar(Recurso recurso);

    /**
     * Método para eliminar un recurso por su ID.
     * Este método elimina un recurso basado en su ID.
     *
     * @param id El ID del recurso a eliminar.
     */
    void eliminar(Long id);

    /**
     * Método para eliminar un proveedor de recursos por su ID.
     * Este método elimina un proveedor de recursos basado en su ID.
     *
     * @param id El ID del proveedor de recursos a eliminar.
     */
    void eliminarRecursoProveedorPorId(Long id);

    /**
     * Método para asignar un proveedor a un recurso.
     * Este método asigna un proveedor a un recurso basado en su ID.
     *
     * @param proveedor El proveedor a asignar.
     * @param idRecurso El ID del recurso al que se asignará el proveedor.
     * @return El proveedor asignado si la asignación fue exitosa, o un Optional vacío si la asignación no fue exitosa.
     */
    Optional<Proveedor> asignarProveedor(Proveedor proveedor, Long idRecurso);

    /**
     * Método para crear un proveedor para un recurso.
     * Este método crea un proveedor para un recurso basado en su ID.
     *
     * @param proveedor El proveedor a crear.
     * @param idRecurso El ID del recurso al que se creará el proveedor.
     * @return El proveedor creado si la creación fue exitosa, o un Optional vacío si la creación no fue exitosa.
     */
    Optional<Proveedor> crearProveedor(Proveedor proveedor, Long idRecurso);

    /**
     * Método para eliminar un proveedor de un recurso.
     * Este método elimina un proveedor de un recurso basado en su ID.
     *
     * @param proveedor El proveedor a eliminar.
     * @param idRecurso El ID del recurso del que se eliminará el proveedor.
     * @return El proveedor eliminado si la eliminación fue exitosa, o un Optional vacío si la eliminación no fue exitosa.
     */
    Optional<Proveedor> eliminarProveedor(Proveedor proveedor, Long idRecurso);
}
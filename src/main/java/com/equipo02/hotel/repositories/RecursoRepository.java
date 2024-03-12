/**
 * @file: RecursoProveedor.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:07
 */

package com.equipo02.hotel.repositories;

import com.equipo02.hotel.domain.entity.Recurso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz RecursoRepository.
 * Esta interfaz define los métodos para interactuar con la tabla de recursos en la base de datos.
 */
public interface RecursoRepository extends CrudRepository<Recurso, Long> {

    /**
     * Método para eliminar un proveedor de recursos por su ID.
     * Este método elimina un proveedor de recursos basado en su ID.
     *
     * @param id El ID del proveedor de recursos.
     */
    @Modifying
    @Query("delete from RecursoProveedor rp where rp.idProveedor=?1")
    void eliminarRecursoProveedorPorId(Long id);
}
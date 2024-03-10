package com.equipo02.hotel.repositories;

import com.equipo02.hotel.domain.entity.Recurso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @file: RecursoProveedor.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:07
 */
public interface RecursoRepository extends CrudRepository<Recurso, Long> {
    @Modifying
    @Query("delete from RecursoProveedor rp where rp.idProveedor=?1")
    void eliminarRecursoProveedorPorId(Long id);
}

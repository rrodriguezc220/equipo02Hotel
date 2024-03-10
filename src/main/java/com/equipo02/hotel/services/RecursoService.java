package com.equipo02.hotel.services;

import com.equipo02.hotel.domain.entity.Proveedor;
import com.equipo02.hotel.domain.entity.Recurso;

import java.util.List;
import java.util.Optional;

/**
 * @file: RecursoService.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:08
 */
public interface RecursoService {
    List<Recurso> listar();
    Optional<Recurso> porId(Long id);
    Optional<Recurso> porIdConProveedores(Long id);
    Recurso guardar(Recurso recurso);
    void eliminar(Long id);

    void eliminarRecursoProveedorPorId(Long id);

    Optional<Proveedor> asignarProveedor(Proveedor proveedor, Long idRecurso);
    Optional<Proveedor> crearProveedor(Proveedor proveedor, Long idRecurso);
    Optional<Proveedor> eliminarProveedor(Proveedor proveedor, Long idRecurso);
}

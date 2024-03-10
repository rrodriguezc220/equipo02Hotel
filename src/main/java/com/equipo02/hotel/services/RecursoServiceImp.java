package com.equipo02.hotel.services;

import com.equipo02.hotel.clients.ProveedorClienteRest;
import com.equipo02.hotel.domain.entity.Proveedor;
import com.equipo02.hotel.domain.entity.Recurso;
import com.equipo02.hotel.domain.entity.RecursoProveedor;
import com.equipo02.hotel.repositories.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @file: RecursoServiceImp.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:08
 */

@Service
public class RecursoServiceImp implements RecursoService{

    @Autowired
    private RecursoRepository repository;

    @Autowired
    private ProveedorClienteRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Recurso> listar() {
        return (List<Recurso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Recurso> porId(Long id) {
        return repository.findById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Recurso> porIdConProveedores(Long id) {
        Optional<Recurso> o = repository.findById(id);
        if (o.isPresent()) {
            Recurso recurso = o.get();
            if (!recurso.getRecursoProveedores().isEmpty()) {
                List<Long> ids = recurso.getRecursoProveedores().stream().map(cu -> cu.getIdProveedor())
                        .collect(Collectors.toList());
                List<Proveedor> proveedores = client.obtenerProveedoresPorRecurso(ids);
                recurso.setProveedores(proveedores);
            }
            return Optional.of(recurso);
        }
        return Optional.empty();
    }

    @Override
    public Recurso guardar(Recurso recurso) {
        return repository.save(recurso);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void eliminarRecursoProveedorPorId(Long id) {
        repository.eliminarRecursoProveedorPorId(id);

    }

    @Override
    @Transactional
    public Optional<Proveedor> asignarProveedor(Proveedor proveedor, Long idRecurso) {
        Optional<Recurso> o = repository.findById(idRecurso);
        if (o.isPresent()) {
            Proveedor proveedorMsvc = client.detalle(proveedor.getIdProveedor());

            Recurso recurso = o.get();
            RecursoProveedor recursoProveedor = new RecursoProveedor();
            recursoProveedor.setIdProveedor(proveedorMsvc.getIdProveedor());

            recurso.addRecursoProveedor(recursoProveedor);
            repository.save(recurso);
            return Optional.of(proveedorMsvc);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Proveedor> crearProveedor(Proveedor proveedor, Long idRecurso) {
        Optional<Recurso> o = repository.findById(idRecurso);
        if (o.isPresent()) {
            Proveedor proveedorNuevoMsvc = client.crear(proveedor);

            Recurso recurso = o.get();
            RecursoProveedor recursoProveedor = new RecursoProveedor();
            recursoProveedor.setIdProveedor(proveedorNuevoMsvc.getIdProveedor());

            recurso.addRecursoProveedor(recursoProveedor);
            repository.save(recurso);
            return Optional.of(proveedorNuevoMsvc);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Proveedor> eliminarProveedor(Proveedor proveedor, Long idRecurso) {
        Optional<Recurso> o = repository.findById(idRecurso);
        if (o.isPresent()) {
            Proveedor proveedorMsvc = client.detalle(proveedor.getIdProveedor());

            Recurso recurso = o.get();
            RecursoProveedor recursoProveedor = new RecursoProveedor();
            recursoProveedor.setIdProveedor(proveedorMsvc.getIdProveedor());

            recurso.removeRecursoProveedor(recursoProveedor);
            repository.save(recurso);
            return Optional.of(proveedorMsvc);
        }

        return Optional.empty();
    }


}

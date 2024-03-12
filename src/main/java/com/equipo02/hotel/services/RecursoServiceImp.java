/**
 * @file: RecursoServiceImp.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 13:08
 */

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
 * Implementación de la interfaz RecursoService.
 * Esta clase implementa los métodos definidos en la interfaz RecursoService para interactuar con los recursos y proveedores en el sistema.
 */
@Service
public class RecursoServiceImp implements RecursoService{

    @Autowired
    private RecursoRepository repository;

    @Autowired
    private ProveedorClienteRest client;

    /**
     * Método para listar todos los recursos.
     * Este método devuelve una lista de todos los recursos registrados en el sistema.
     *
     * @return Una lista de recursos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Recurso> listar() {
        return (List<Recurso>) repository.findAll();
    }

    /**
     * Método para obtener un recurso por su ID.
     * Este método devuelve un recurso basado en su ID.
     *
     * @param id El ID del recurso.
     * @return Un recurso si se encuentra, o un Optional vacío si no se encuentra el recurso.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Recurso> porId(Long id) {
        return repository.findById(id);
    }

    /**
     * Método para obtener un recurso junto con sus proveedores por su ID.
     * Este método devuelve un recurso junto con sus proveedores basado en su ID.
     *
     * @param id El ID del recurso.
     * @return Un recurso si se encuentra, o un Optional vacío si no se encuentra el recurso.
     */
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

    /**
     * Método para guardar un recurso.
     * Este método guarda un recurso en el sistema.
     *
     * @param recurso El recurso a guardar.
     * @return El recurso guardado.
     */
    @Override
    public Recurso guardar(Recurso recurso) {
        return repository.save(recurso);
    }

    /**
     * Método para eliminar un recurso por su ID.
     * Este método elimina un recurso basado en su ID.
     *
     * @param id El ID del recurso a eliminar.
     */
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    /**
     * Método para eliminar un proveedor de recursos por su ID.
     * Este método elimina un proveedor de recursos basado en su ID.
     *
     * @param id El ID del proveedor de recursos a eliminar.
     */
    @Override
    public void eliminarRecursoProveedorPorId(Long id) {
        repository.eliminarRecursoProveedorPorId(id);

    }

    /**
     * Método para asignar un proveedor a un recurso.
     * Este método asigna un proveedor a un recurso basado en su ID.
     *
     * @param proveedor El proveedor a asignar.
     * @param idRecurso El ID del recurso al que se asignará el proveedor.
     * @return El proveedor asignado si la asignación fue exitosa, o un Optional vacío si la asignación no fue exitosa.
     */
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

    /**
     * Método para crear un proveedor para un recurso.
     * Este método crea un proveedor para un recurso basado en su ID.
     *
     * @param proveedor El proveedor a crear.
     * @param idRecurso El ID del recurso al que se creará el proveedor.
     * @return El proveedor creado si la creación fue exitosa, o un Optional vacío si la creación no fue exitosa.
     */
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

    /**
     * Método para eliminar un proveedor de un recurso.
     * Este método elimina un proveedor de un recurso basado en su ID.
     *
     * @param proveedor El proveedor a eliminar.
     * @param idRecurso El ID del recurso del que se eliminará el proveedor.
     * @return El proveedor eliminado si la eliminación fue exitosa, o un Optional vacío si la eliminación no fue exitosa.
     */
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
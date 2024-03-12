/**
 * @file: RecursoController.java
 * @author: (c)2024 MARCO
 * @created: 10/3/2024 14:53
 */

package com.equipo02.hotel.controllers;

import com.equipo02.hotel.domain.entity.Proveedor;
import com.equipo02.hotel.domain.entity.Recurso;
import com.equipo02.hotel.services.RecursoService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controlador de Recursos.
 * Este controlador maneja las solicitudes para obtener, crear, editar y eliminar recursos, así como asignar, crear y eliminar proveedores de recursos.
 */
@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService service;

    /**
     * Método para listar todos los recursos.
     * Este método devuelve una lista de todos los recursos registrados en el sistema.
     *
     * @return Una respuesta ResponseEntity con la lista de recursos.
     */
    @GetMapping
    public ResponseEntity<List<Recurso>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Método para obtener el detalle de un recurso por su ID.
     * Este método devuelve un recurso basado en su ID.
     *
     * @param id El ID del recurso.
     * @return Una respuesta ResponseEntity con el recurso solicitado si se encuentra, o una respuesta ResponseEntity con un error si no se encuentra el recurso.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Recurso> o = service.porIdConProveedores(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método para crear un recurso.
     * Este método crea un recurso basado en la información proporcionada.
     *
     * @param recurso El recurso a crear.
     * @param result El objeto BindingResult con los errores de validación.
     * @return Una respuesta ResponseEntity con el recurso creado si la creación fue exitosa, o una respuesta ResponseEntity con un error si la creación no fue exitosa.
     */
    @PostMapping()
    public ResponseEntity<?> crear(@Valid @RequestBody Recurso recurso, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Recurso recursoDb = service.guardar(recurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(recursoDb);
    }

    /**
     * Método para editar un recurso.
     * Este método edita un recurso existente basado en la información proporcionada.
     *
     * @param recurso El recurso con la información actualizada.
     * @param result El objeto BindingResult con los errores de validación.
     * @param id El ID del recurso a editar.
     * @return Una respuesta ResponseEntity con el recurso editado si la edición fue exitosa, o una respuesta ResponseEntity con un error si la edición no fue exitosa.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Recurso recurso, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Optional<Recurso> o = service.porId(id);
        if (o.isPresent()) {
            Recurso recursoDb = o.get();
            recursoDb.setNombre(recurso.getNombre());
            recursoDb.setDescripcion(recurso.getDescripcion());
            recursoDb.setPrecio(recurso.getPrecio());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(recursoDb));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método para eliminar un recurso.
     * Este método elimina un recurso basado en su ID.
     *
     * @param id El ID del recurso a eliminar.
     * @return Una respuesta ResponseEntity sin contenido si la eliminación fue exitosa, o una respuesta ResponseEntity con un error si no se encontró el recurso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Recurso> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(o.get().getIdRecurso());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método para asignar un proveedor a un recurso.
     * Este método asigna un proveedor a un recurso basado en su ID.
     *
     * @param proveedor El proveedor a asignar.
     * @param idRecurso El ID del recurso al que se asignará el proveedor.
     * @return Una respuesta ResponseEntity con el proveedor asignado si la asignación fue exitosa, o una respuesta ResponseEntity con un error si la asignación no fue exitosa.
     */
    @PutMapping("/asignar-proveedor/{idRecurso}")
    public ResponseEntity<?> asignarProveedor(@RequestBody Proveedor proveedor, @PathVariable Long idRecurso) {
        Optional<Proveedor> o;
        try {
            o = service.asignarProveedor(proveedor, idRecurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el proveedor por " +
                            "el id o error en la comunicacion: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método para crear un proveedor.
     * Este método crea un proveedor para un recurso basado en su ID.
     *
     * @param proveedor El proveedor a crear.
     * @param idRecurso El ID del recurso al que se creará el proveedor.
     * @return Una respuesta ResponseEntity con el proveedor creado si la creación fue exitosa, o una respuesta ResponseEntity con un error si la creación no fue exitosa.
     */
    @PostMapping("/crear-proveedor/{idRecurso}")
    public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor, @PathVariable Long idRecurso) {
        Optional<Proveedor> o;
        try {
            o = service.crearProveedor(proveedor, idRecurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No se pudo crear el proveedor " +
                            "o error en la comunicacion: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método para eliminar un proveedor.
     * Este método elimina un proveedor de un recurso basado en su ID.
     *
     * @param proveedor El proveedor a eliminar.
     * @param idRecurso El ID del recurso del que se eliminará el proveedor.
     * @return Una respuesta ResponseEntity con el proveedor eliminado si la eliminación fue exitosa, o una respuesta ResponseEntity con un error si la eliminación no fue exitosa.
     */
    @DeleteMapping("/eliminar-proveedor/{idRecurso}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Proveedor proveedor, @PathVariable Long idRecurso) {
        Optional<Proveedor> o;
        try {
            o = service.eliminarProveedor(proveedor, idRecurso);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el proveedor por " +
                            "el id o error en la comunicacion: " + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Método para eliminar un proveedor de recursos por su ID.
     * Este método elimina un proveedor de recursos basado en su ID.
     *
     * @param id El ID del proveedor de recursos.
     * @return Una respuesta ResponseEntity sin contenido si la eliminación fue exitosa, o una respuesta ResponseEntity con un error si no se encontró el proveedor de recursos.
     */
    @DeleteMapping("/eliminar-recurso-proveedor/{id}")
    public ResponseEntity<?> eliminarRecursoProveedorPorId(@PathVariable Long id){
        service.eliminarRecursoProveedorPorId(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método para validar los errores de binding result.
     * Este método devuelve un mapa con los errores de binding result.
     *
     * @param result El objeto BindingResult con los errores.
     * @return Una respuesta ResponseEntity con el mapa de errores.
     */
    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}